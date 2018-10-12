package com.photoprint.network.api.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Базовая обёртка над ответом сервера (предусмотренно сервером) для списков
 *
 * @author Grigoriy Pryamov.
 */
public class Data<Answer> {

    @SerializedName("data")
    @Expose
    private List<Answer> data;

    public List<Answer> getData() {
        return data;
    }

    public void setData(List<Answer> data) {
        this.data = data;
    }
}
