package com.photoprint.network.api.model.manual;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @autor Grigoriy Pryamov.
 */

public class Manual {

    @SerializedName("data")
    @Expose
    private List<Guide> data;

    public List<Guide> getData() {
        return data;
    }

    public void setData(List<Guide> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Manual{" +
                "data=" + data +
                '}';
    }
}
