package com.photoprint.network.api.model.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Libgo on 24.03.2018.
 */

public class DataService {

    @SerializedName("data")
    @Expose
    private List<Service> data = null;

    public List<Service> getData() {
        return data;
    }

    public void setData(List<Service> data) {
        this.data = data;
    }


}
