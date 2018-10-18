package com.photoprint.photoclub.ui.activity.gallery.fragment.folder.model;

/**
 * Модель папки с фотографиями
 *
 * @author Grigoriy Pryamov.
 */
public class Folder {
    /**
     * Наименование папки
     */
    private String name;
    /**
     * Превью
     */
    private String folderImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolderImage() {
        return folderImage;
    }

    public void setFolderImage(String folderImage) {
        this.folderImage = folderImage;
    }
}
