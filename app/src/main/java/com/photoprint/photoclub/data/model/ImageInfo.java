package com.photoprint.photoclub.data.model;

/**
 * Промежуточная модель с информацией о фотографии
 *
 * @author Grigoriy Pryamov.
 */
public class ImageInfo {
    /**
     * Папка фотографии
     */
    private String folder;
    /**
     * Наименование фотографии
     */
    private String name;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
