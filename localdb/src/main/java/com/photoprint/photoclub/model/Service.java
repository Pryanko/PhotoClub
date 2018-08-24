package com.photoprint.photoclub.model;

import com.photoprint.photoclub.model.base.ModelWithId;
import com.photoprint.photoclub.model.enums.ServiceType;

/**
 * Основная модель для услуг
 *
 * @author Grigoriy Pryamov.
 */
public class Service implements ModelWithId<Long> {

    /**
     * Id услуги
     */
    private long id;
    /**
     * Id категории к которой привязана услуга
     */
    private Integer categoryId;
    /**
     * Наименование услуги
     */
    private String name;
    /**
     * Описание услуги
     */
    private String description;
    /**
     * Тип составной услуги
     * 0 - обычная услуга например печать фотографии,
     * 1 - составная услуга где в одной услуге несколько фотографий например печать календаря
     */
    private ServiceType serviceType;
    /**
     * Стоимость услуги
     */
    private String price;
    /**
     * Image для экранов с маленьким разрешением
     */
    private String image480;
    /**
     * Ширина
     */
    private Integer image480Width;
    /**
     * Высота
     */
    private Integer image480Height;
    /**
     * Image для экранов с большим разрешением
     */
    private String image1024;
    /**
     * Ширина
     */
    private Integer image1024Width;
    /**
     * Высота
     */
    private Integer image1024Height;
    /**
     * Порядок сортировки в списке категории, тип integer, по возрастанию, 0 первый
     */
    private Integer sort;
    /**
     * Имя категории к которйо принадлежит услуга
     */
    private String categoryName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
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
                ", serviceType=" + serviceType +
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
