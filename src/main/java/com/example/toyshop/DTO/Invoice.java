package com.example.toyshop.DTO;

import com.example.toyshop.entity.Location;

import java.sql.Date;
import java.util.List;

public class Invoice  {
    public List<InvoiceElement> invoiceList;
    public Location location;
    public Date date;
    public long id;
    public int sum;
}
