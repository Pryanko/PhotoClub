package com.photoprint.network.auth.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor user on 17.01.2018.
 */

public class DataToken {
    /**
     * Данные присвоенные сервером к пользователю
     */
    @SerializedName("data")
    @Expose
    private DeviceToken deviceToken;

    public DeviceToken getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(DeviceToken deviceToken) {
        this.deviceToken = deviceToken;
    }

}
