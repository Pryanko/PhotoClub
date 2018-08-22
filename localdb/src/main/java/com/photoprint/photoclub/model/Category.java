package com.photoprint.photoclub.model;

import android.arch.persistence.room.PrimaryKey;

import com.photoprint.photoclub.model.base.ModelWithId;

/**
 * @author Grigoriy Pryamov.
 */
public class Category implements ModelWithId<Long> {

    /**
     * Id категории
     */
    @PrimaryKey
    private long id;
    /**
     * Наименование категории
     */
    private String name;
    /**
     * Описание категориии
     */
    private String description;
    /**
     * Тип категории
     */
    private String type;
    /**
     * Image категории для экранов с маленьким разрешением
     */
    private String image480;
    /**
     * Image категории для экранов с большим разрешением
     */
    private String image1024;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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
}
