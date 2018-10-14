package com.photoprint.network.api.model.order;

import com.google.gson.annotations.SerializedName;

/**
 * @author Grigoriy Pryamov.
 */
public class Order {

    @SerializedName("order_id")
    private long orderId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
