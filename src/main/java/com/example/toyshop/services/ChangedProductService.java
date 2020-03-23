package com.example.toyshop.services;

import com.example.toyshop.DTO.Invoice;
import com.example.toyshop.DTO.InvoiceElement;
import com.example.toyshop.DTO.SumReport;
import com.example.toyshop.entity.AvailableProduct;
import com.example.toyshop.entity.ChangeStatus;
import com.example.toyshop.entity.ChangedProduct;
import com.example.toyshop.entity.Product;
import com.example.toyshop.exceptions.IdNotFoundException;
import com.example.toyshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChangedProductService {

    public static final String MESSAGE = "Product was not found for this id :: ";

    private final ChangedProductRepository changedProductRepository;

    private final AvailableProductRepository availableProductRepository;

    private final LocationRepository locationRepository;

    private final ProductRepository productRepository;

    private final SoldInvoiceInfoRepository soldInvoiceInfoRepository;


    @Autowired
    public ChangedProductService(
            @NonNull ChangedProductRepository changedProductRepository,
            @NonNull AvailableProductRepository availableProductRepository,
            @NonNull LocationRepository locationRepository,
            @NonNull ProductRepository productRepository,
            @NonNull SoldInvoiceInfoRepository soldInvoiceInfoRepository) {
        this.changedProductRepository = changedProductRepository;
        this.availableProductRepository = availableProductRepository;
        this.locationRepository = locationRepository;
        this.productRepository = productRepository;
        this.soldInvoiceInfoRepository = soldInvoiceInfoRepository;

    }

    /**
     * Returns list of all changed products from database table
     *
     * @return list of all changed products
     */
    public List<ChangedProduct> getAllChangedProducts() {
        return (List<ChangedProduct>) changedProductRepository.findAll();
    }

    /**
     * Get a product by Id
     *
     * @param changedProductId a long value of id needed to get
     * @return ChangedProduct object with needed id if present
     */
    public ChangedProduct getChangedProductById(Long changedProductId) {
        return changedProductRepository.findById(changedProductId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + changedProductId));
    }

    /**
     * Adds a ChangedProduct object to changedProducts table
     *
     * @param changedProduct an example of object
     * @return saved product to further put it to response entity
     */
    public ChangedProduct addChangedProduct(ChangedProduct changedProduct) {
        return changedProductRepository.save(changedProduct);
    }

    /**
     * updates existing changedProduct using long id to identify product and changes it characteristics with values from changed product Details
     *
     * @param changedProductId      an id of product that needs to be changed
     * @param changedProductDetails an object of ChangedProduct type with new values
     * @return saved changed product
     */
    public ChangedProduct updateChangedProduct(Long changedProductId, ChangedProduct changedProductDetails) {
        ChangedProduct changedProduct = changedProductRepository.findById(changedProductId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + changedProductId));
        changedProduct.setDate(changedProductDetails.getDate());
        changedProduct.setProductLocation(changedProductDetails.getProductLocation());
        changedProduct.setQuantity(changedProductDetails.getQuantity());
        changedProduct.setProductId(changedProductDetails.getProductId());
        changedProduct.setInvoiceId(changedProductDetails.getInvoiceId());
        changedProduct.setStatus(changedProductDetails.getStatus());
        return changedProductRepository.save(changedProduct);

    }

    /**
     * Deletes  changedProduct using given Id from changedProducts table and returns a hash map response
     *
     * @param changedProductId long value of id of product that needs to be deleted
     * @return hash map with responses like : "deleted, true"
     */
    public Map<String, Boolean> deleteChangedProduct(Long changedProductId) {
        ChangedProduct changedProduct = changedProductRepository.findById(changedProductId)
                .orElseThrow(() -> new IdNotFoundException(MESSAGE + changedProductId));
        changedProductRepository.delete(changedProduct);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * Adds products from invoice to changedProducts table and sets them type "NOT_REMOVED"
     * This products will NOT change the values of available products until invoice is confirmed
     *
     * @param invoice is Invoice object with products that will be removed
     * @return a hash map with id of product and the result of it's removing , whether it is was successful(true) or not(false)
     */
    public Map<Long, Boolean> removeProductInvoice(Invoice invoice) {
        Map<Long, Boolean> response = new HashMap<>();
        for (InvoiceElement element : invoice.invoiceList) {
            AvailableProduct availableProduct = availableProductRepository.findByProductAndProductLocation(element.getProduct(), invoice.location)
                    .orElseThrow(() -> new IdNotFoundException(MESSAGE + element.getProduct().getId()));
            if (availableProduct == null || availableProduct.getQuantity() < element.getQuantity()) {
                response.put(element.getProduct().getId(), false);
                continue;
            }
            ChangedProduct changedProduct = new ChangedProduct();
            changedProduct.setDate(invoice.date);
            changedProduct.setProductLocation(invoice.location);
            changedProduct.setQuantity(element.getQuantity());
            changedProduct.setProductId(element.getProduct());
            changedProduct.setStatus(ChangeStatus.NOT_REMOVED);
            changedProduct.setInvoiceId(invoice.id);
            changedProductRepository.save(changedProduct);
            response.put(element.getProduct().getId(), true);
        }
        return response;
    }

    /**
     * This method allows delete products that was marked for removing, thus they won't be removed
     * @param productList List that comes from frontend, whinch contains id that should unmarked for removing
     * @param invoiceID indicates products that was listed in inovice for removing
     * @return map with responses where true means that product were deleted
     */
    public Map<Long, Boolean> cancelRemove(List<Product> productList, long invoiceID){
        Map<Long, Boolean> response = new HashMap<>();
        for (Product product:productList) {
           ChangedProduct changedProduct = changedProductRepository.findByProductIdAndInvoiceId(product, invoiceID);
           changedProductRepository.delete(changedProduct);
           response.put(product.getId(), true);
        }
        return response;
    }

    /**
     * Confirm the remove of products using id from invoice they were in
     * This operation will set status of products to "REMOVED" and will make changes in available products table
     *
     * @param invoiceID long variable of id from where products were got
     * @return boolean value true if operation was successful
     */

    public boolean confirmRemove(long invoiceID) {
        List<AvailableProduct> availableList = new ArrayList<>();
        List<ChangedProduct> changedProducts = changedProductRepository.findAllByInvoiceId(invoiceID);
        changedProducts.forEach(changedProduct -> {
            AvailableProduct availableProduct = availableProductRepository.findByProductAndProductLocation(changedProduct.getProductId(), changedProduct.getProductLocation())
                    .orElseThrow(() -> new IdNotFoundException(MESSAGE + changedProduct.getId()));
            availableProduct.setQuantity(availableProduct.getQuantity() - changedProduct.getQuantity());
            availableList.add(availableProduct);
        });
        for (AvailableProduct availableProduct : availableList) {
            availableProductRepository.save(availableProduct);
        }
        for (ChangedProduct changedProduct : changedProducts) {
            changedProduct.setStatus(ChangeStatus.REMOVED);
            changedProductRepository.save(changedProduct);
        }
        return true;
    }

    /**
     * A method adds(shipment) products or product from invoice  to available products table
     *
     * @param invoice Invoice type object which contains information about products to be added
     * @return a hash map with id of product and the result of it's adding
     */
    public Map<Long, Boolean> addProductInvoice(Invoice invoice) {
        Map<Long, Boolean> response = new HashMap<>();
        for (InvoiceElement element : invoice.invoiceList) {
            locationRepository.findById(invoice.location.getId())
                    .orElseThrow(() -> new IdNotFoundException(MESSAGE + invoice.location.getId()));
            Product product = productRepository.findById(element.getProduct().getId())
                    .orElse(null);
            if(product == null){
                response.put(element.getProduct().getId(), false);
                continue;
            }
            ChangedProduct changedProduct = new ChangedProduct();
            changedProduct.setDate(invoice.date);
            changedProduct.setProductLocation(invoice.location);
            changedProduct.setQuantity(element.getQuantity());
            changedProduct.setProductId(element.getProduct());
            changedProduct.setStatus(ChangeStatus.ADDED);
            changedProductRepository.save(changedProduct);
            AvailableProduct availableProduct = availableProductRepository.findByProductAndProductLocation(element.getProduct(), invoice.location)
                    .orElse(null);
            if (availableProduct == null) {
                availableProduct = new AvailableProduct();
                availableProduct.setProductLocation(invoice.location);
                availableProduct.setProduct(element.getProduct());
            }
            availableProduct.setQuantity(availableProduct.getQuantity() + element.getQuantity());
            availableProductRepository.save(availableProduct);
            response.put(element.getProduct().getId(), true);
        }
        return response;
    }

    /**
     * Adds products from invoice to changedProducts table and sets them type "SOLD"
     * This products will change the values of available products table
     *
     * @param invoice is Invoice object with products that will be sold
     * @return a hash map with id of product and the result of it's selling , whether it is was successful(true) or not(false)
     */
    public Map<Long, Boolean> addProductSoldInvoice(Invoice invoice) {
        Map<Long, Boolean> response = new HashMap<>();
        for (InvoiceElement element : invoice.invoiceList) {
            ChangedProduct changedProduct = new ChangedProduct();
            changedProduct.setDate(invoice.date);
            changedProduct.setProductLocation(invoice.location);
            changedProduct.setQuantity(element.getQuantity());
            changedProduct.setProductId(element.getProduct());
            changedProduct.setStatus(ChangeStatus.SOLD);
            AvailableProduct availableProduct = availableProductRepository.findByProductAndProductLocation(element.getProduct(), invoice.location)
                    .orElseThrow(() -> new IdNotFoundException(MESSAGE + element.getProduct().getId()));
            if (availableProduct == null || availableProduct.getQuantity() < element.getQuantity()) {
                response.put(element.getProduct().getId(), false);
                continue;
            }
            availableProduct.setQuantity(availableProduct.getQuantity() - element.getQuantity());
            changedProductRepository.save(changedProduct);
            availableProductRepository.save(availableProduct);
            response.put(element.getProduct().getId(), true);
        }
        return response;
    }
}
