package com.photoprint.photoclub.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.photoprint.photoclub.room.entity.base.EntityWithId;

/**
 * Пользователь приложения
 *
 * @author Grigoriy Pryamov.
 */
@Entity(tableName = "User")
public class UserEntity implements EntityWithId<Long> {
    /**
     * Id пользователя
     */
    @PrimaryKey(autoGenerate = true)
    private long id;
    /**
     * Логин пользователя
     */
    private String username;
    /**
     * Email пользователя
     */
    private String email;
    /**
     * Статус пользователя
     */
    private int status;
    /**
     * Дата регистрации пользователя
     */
    private long dateCreated;
    /**
     * Дата обновления данных о пользователе
     */
    private long dateUpdated;
    /**
     * ФИО пользователя
     */
    private String name;
    /**
     * Аватарка пользователя (предварительно не используется)
     */
    private String imageUser;
    /**
     * Адрес пользователя (дефолтный)
     */
    private String address;
    /**
     * Номер телефона пользователя
     */
    private String mobileNumber;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", name='" + name + '\'' +
                ", imageUser='" + imageUser + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
