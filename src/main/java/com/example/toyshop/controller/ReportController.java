package com.example.toyshop.controller;

import com.example.toyshop.DTO.ChangedProductProjection;
import com.example.toyshop.DTO.SumReport;
import com.example.toyshop.entity.ChangedProduct;
import com.example.toyshop.entity.Product;
import com.example.toyshop.services.ReportService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController

@RequestMapping("/api/v3")

@Api(value = "Report Management System")


public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/sold-by-shops")
    public List<ChangedProductProjection> getSoldByShops() {
        return reportService.getSoldProductOrderByProductLocation();
    }

    @GetMapping("/removed-by-time")
    public List<ChangedProductProjection> getRemovedByTime(Date minimum, Date maximum) {
        return reportService.getRemovedProductsOrderByDate(minimum, maximum);
    }

    @GetMapping("/general-sum")
    public List<SumReport> getGeneralSum() {
        return reportService.getGeneralSumInfoByShops();
    }

    @GetMapping("/mean-sum")
    public List<SumReport> getMeanSum() {
        return reportService.getMidSumInfoByShops();
    }
}
