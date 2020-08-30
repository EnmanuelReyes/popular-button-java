package com.fragmento.popularbutton.model;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: enmanuelreyes
 * Date: 8/29/20
 * Time: 11:17 PM
 */
public interface Item {
    int getQuantity();
    BigDecimal getUnitAmount();
    BigDecimal getTotalAmount();
    String getDescription();
    String getOtherInfo();


}
