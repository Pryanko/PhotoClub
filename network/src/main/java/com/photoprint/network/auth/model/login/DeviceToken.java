package com.photoprint.network.auth.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @autor user on 17.01.2018.
 */

public class DeviceToken {
    /**
     * Имя пользователя присвоенное сервером
     */
    @SerializedName("username")
    @Expose
    private String username;
    /**
     * Токен пользователя присвоенное сервером
     */
    @SerializedName("token")
    @Expose
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "DeviceToken{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
