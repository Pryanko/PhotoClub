package com.photoprint.network.api.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Базовая обёртка над ответом сервера (предусмотренно сервером) для еденичного ответа
 *
 * @author Grigoriy Pryamov.
 */
public class SingleData<Answer> {

    @SerializedName("data")
    @Expose
    private Answer data;

    public Answer getData() {
        return data;
    }

    public void setData(Answer data) {
        this.data = data;
    }
}
