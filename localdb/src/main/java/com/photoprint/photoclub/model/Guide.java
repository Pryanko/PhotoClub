package com.photoprint.photoclub.model;

import android.arch.persistence.room.PrimaryKey;

import com.photoprint.photoclub.model.base.ModelWithId;

/**
 * Основная модель для экрана обучения
 *
 * @author Grigoriy Pryamov.
 */
public class Guide implements ModelWithId<Long> {

    /**
     * Id мануала
     */
    @PrimaryKey
    private long id;
    /**
     * Заголовок (нименование)
     */
    private String header;
    /**
     * Картинка для большого разрешения
     */
    private String image1024;
    /**
     * Картинка для экранов с маленьким разрешением
     */
    private String image480;
    /**
     * Текст мануала
     */
    private String text;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage1024() {
        return image1024;
    }

    public void setImage1024(String image1024) {
        this.image1024 = image1024;
    }

    public String getImage480() {
        return image480;
    }

    public void setImage480(String image480) {
        this.image480 = image480;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", image1024='" + image1024 + '\'' +
                ", image480='" + image480 + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
