package com.example.toyshop.services;

import com.example.toyshop.DTO.ChangedProductProjection;
import com.example.toyshop.DTO.SumReport;
import com.example.toyshop.repository.ChangedProductRepository;
import com.example.toyshop.repository.SoldInvoiceInfoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReportService {

    private ChangedProductRepository changedProductRepository;

    private SoldInvoiceInfoRepository soldInvoiceInfoRepository;

    public ReportService(ChangedProductRepository changedProductRepository, SoldInvoiceInfoRepository soldInvoiceInfoRepository) {
        this.changedProductRepository = changedProductRepository;
        this.soldInvoiceInfoRepository = soldInvoiceInfoRepository;
    }

    /**
     *A method executes a sql query, which returns list of all sold products, grouped by loction
     * @return list of projections
     */
    public List<ChangedProductProjection> getSoldProductOrderByProductLocation(){
        return changedProductRepository.getSoldProductOrderByProductLocation();
    }

    /**
     *A method executes a sql query, which returns list of all removed products in time between two dates
     * @param minimumDate first date, the lower boundary
     * @param maximumDate second date, the higher boundary
     * @return list of projections
     */
    public List<ChangedProductProjection> getRemovedProductsOrderByDate(Date minimumDate, Date maximumDate){
        return changedProductRepository.getRemovedProductsOrderByDate(minimumDate, maximumDate);
    }

    /**
     *A method that executes sql query, which returns list of total gain of every location
     * @return list of projections
     */
    public List<SumReport> getGeneralSumInfoByShops(){
        return soldInvoiceInfoRepository.getGeneralSumInfoByShops();
    }

    /**
     *A method that executes sql query, which returns mean value of purchases in every location
     *@return list of projections
     */
    public List<SumReport> getMidSumInfoByShops(){
        return soldInvoiceInfoRepository.getMidSumInfoByShops();
    }
}
