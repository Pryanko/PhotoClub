package com.photoprint.network.api.model.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Libgo on 24.03.2018.
 */

public class Service {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("multiply")
    @Expose
    private Integer multiply;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image480")
    @Expose
    private String image480;
    @SerializedName("image480_width")
    @Expose
    private Integer image480Width;
    @SerializedName("image480_height")
    @Expose
    private Integer image480Height;
    @SerializedName("image1024")
    @Expose
    private String image1024;
    @SerializedName("image1024_width")
    @Expose
    private Integer image1024Width;
    @SerializedName("image1024_height")
    @Expose
    private Integer image1024Height;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("category_name")
    @Expose
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMultiply() {
        return multiply;
    }

    public void setMultiply(Integer multiply) {
        this.multiply = multiply;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage480() {
        return image480;
    }

    public void setImage480(String image480) {
        this.image480 = image480;
    }

    public Integer getImage480Width() {
        return image480Width;
    }

    public void setImage480Width(Integer image480Width) {
        this.image480Width = image480Width;
    }

    public Integer getImage480Height() {
        return image480Height;
    }

    public void setImage480Height(Integer image480Height) {
        this.image480Height = image480Height;
    }

    public String getImage1024() {
        return image1024;
    }

    public void setImage1024(String image1024) {
        this.image1024 = image1024;
    }

    public Integer getImage1024Width() {
        return image1024Width;
    }

    public void setImage1024Width(Integer image1024Width) {
        this.image1024Width = image1024Width;
    }

    public Integer getImage1024Height() {
        return image1024Height;
    }

    public void setImage1024Height(Integer image1024Height) {
        this.image1024Height = image1024Height;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", multiply=" + multiply +
                ", price='" + price + '\'' +
                ", image480='" + image480 + '\'' +
                ", image480Width=" + image480Width +
                ", image480Height=" + image480Height +
                ", image1024='" + image1024 + '\'' +
                ", image1024Width=" + image1024Width +
                ", image1024Height=" + image1024Height +
                ", sort=" + sort +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
