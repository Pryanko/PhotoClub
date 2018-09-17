package com.photoprint.photoclub.model;

import com.photoprint.photoclub.model.base.ModelWithId;

/**
 * Макет
 *
 * @author Grigoriy Pryamov.
 */
public class Maquette implements ModelWithId<Long> {
    /**
     * Id макета
     */
    private long id;

    /**
     * Наименование макета
     */
    private String name;

    /**
     * Изображение макета
     */
    private String imageMaquette;


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

    public String getImageMaquette() {
        return imageMaquette;
    }

    public void setImageMaquette(String imageMaquette) {
        this.imageMaquette = imageMaquette;
    }
}
