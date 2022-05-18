package com.codegym.model.dto.order;

import java.util.Date;

public interface OrderByQueryDto {
    Long getId ();
    Date getCreate_Date();
    double getDiscount_Amount();
    String getRestaurant_Note();
    double getService_Fee();
    double getShipping_Fee();
    String getShipping_Note();
    double getTotal_Fee();

}
