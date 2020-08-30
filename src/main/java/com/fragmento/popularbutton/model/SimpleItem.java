package com.fragmento.popularbutton.model;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: enmanuelreyes
 * Date: 8/29/20
 * Time: 11:20 PM
 */
public class SimpleItem implements Item {
    private int quantity;
    private BigDecimal unitAmount;
    private BigDecimal totalAmount;
    private String description;
    private String otherInfo;

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public BigDecimal getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(BigDecimal unitAmount) {
        this.unitAmount = unitAmount;
    }

    @Override
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
