package com.example.toyshop.DTO;

import java.sql.Date;

public interface ChangedProductProjection {

    String getLocationName();

    Integer getQuantity();

    Date getDate();

    String getProductName();

    Integer getCost();
}
