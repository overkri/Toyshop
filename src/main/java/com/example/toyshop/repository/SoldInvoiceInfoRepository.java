package com.example.toyshop.repository;

import com.example.toyshop.DTO.SumReport;
import com.example.toyshop.entity.SoldInvoiceInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SoldInvoiceInfoRepository extends PagingAndSortingRepository<SoldInvoiceInfo, Long> {

    @Query(
            value = "SELECT SUM(sold_invoice_info.sum) AS sum, locations.location_name AS locationName FROM sold_invoice_info LEFT JOIN locations ON sold_invoice_info.product_location_id = locations.id GROUP BY locations.location_name",
            nativeQuery = true)

    List<SumReport> getGeneralSumInfoByShops();

    @Query(
            value = "SELECT AVg(sold_invoice_info.sum) AS sum, locations.location_name FROM sold_invoice_info LEFT JOIN locations ON sold_invoice_info.product_location_id = locations.id GROUP BY locations.location_name",
            nativeQuery = true)

    List<SumReport> getMidSumInfoByShops();
}
