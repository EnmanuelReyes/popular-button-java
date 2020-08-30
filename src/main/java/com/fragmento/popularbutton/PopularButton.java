package com.fragmento.popularbutton;

import com.fragmento.popularbutton.model.Item;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * User: enmanuelreyes
 * Date: 8/29/20
 * Time: 11:16 PM
 */
public final class PopularButton {

    private static PopularButton INSTANCE = null;

    private final Function<Collection<Item>, BigDecimal> getTotal = (items -> items.stream()
            .map(Item::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add));


    public PopularButton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PopularButton();
        }
        return INSTANCE;
    }
    public String getPayload(PopularButtonConfig config, String transactionId, String currencyCode, Collection<Item> items) {
        return this.encrypt(config, this.createMpini(config, transactionId, currencyCode, items).toString());
    }

    private StringBuilder createMpini(PopularButtonConfig config, String transactionId, String currencyCode, Collection<Item> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("<MPINI>");
        sb.append("<CNV>").append(config.getMerchantId()).append("</CNV>");
        sb.append("<REC>").append("BCO-POP").append("</REC>");
        sb.append("<TRX>").append(transactionId).append("</TRX>");
        sb.append("<TOT>").append(getTotal.apply(items)).append("</TOT>");
        sb.append("<MDA>").append(currencyCode).append("</MDA>");
        sb.append("<NROPGO>").append(items.size()).append("</NROPGO>");
        sb.append(this.getCart(items));
        sb.append("</MPINI>");
        return sb;

    }

    private StringBuilder getCart(Collection<Item> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("<CARRO>");
        for (Item item : items) {
            sb.append("<DET>");
            sb.append("<NRO>").append(item.getQuantity()).append("</NRO>");
            sb.append("<PRE>").append(item.getUnitAmount()).append("</PRE>");
            sb.append("<MTO>").append(item.getTotalAmount()).append("</MTO>");
            sb.append("<GLS>").append(item.getDescription()).append("</GLS>");
            sb.append("<ADI>").append(item.getOtherInfo()).append("</ADI>");
            sb.append("</DET>");
        }
        sb.append("</CARRO>");
        return sb;
    }


    private String encrypt(PopularButtonConfig config, String mpini) {
        String key = config.getKey();
        String signature = Signature.encrypt(mpini, key, false);
        String encoded = Signature.encrypt("<enveloment>" + mpini + "<firma>" + signature + "</firma></enveloment>", key, true);
        return encoded.trim();
    }
}
