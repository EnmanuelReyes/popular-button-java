package com.fragmento.popularbutton;

/**
 * Created by IntelliJ IDEA.
 * User: enmanuelreyes
 * Date: 8/29/20
 * Time: 11:25 PM
 */
public class PopularButtonConfig {
    private final String MERCHANT_ID;
    private final String KEY;

    public PopularButtonConfig(String MERCHANT_ID, String KEY) {
        this.KEY = KEY;
        this.MERCHANT_ID = MERCHANT_ID;
    }

    public String getMerchantId() {
        return MERCHANT_ID;
    }

    public String getKey() {
        return KEY;
    }
}
