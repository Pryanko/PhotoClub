package com.photoprint.network.api.model.category;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemCategory {
    /**
     * Id категории
     */
    @SerializedName("id")
    @Expose
    private int id;
    /**
     * Наименование категории
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * Описание категории
     */
    @SerializedName("description")
    @Expose
    private String description;
    /**
     * Тип категории
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * image категории для экранов с маленьким разрешением
     */
    @SerializedName("image480")
    @Expose
    private String image480;
    /**
     * image категории для экранов с большим разрешением
     */
    @SerializedName("image1024")
    @Expose
    private String image1024;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage480() {
        return image480;
    }

    public void setImage480(String image480) {
        this.image480 = image480;
    }

    public String getImage1024() {
        return image1024;
    }

    public void setImage1024(String image1024) {
        this.image1024 = image1024;
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", image480='" + image480 + '\'' +
                ", image1024='" + image1024 + '\'' +
                '}';
    }
}