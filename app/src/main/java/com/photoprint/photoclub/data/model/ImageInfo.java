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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageInfo imageInfo = (ImageInfo) o;

        if (folder != null ? !folder.equals(imageInfo.folder) : imageInfo.folder != null)
            return false;
        return name != null ? name.equals(imageInfo.name) : imageInfo.name == null;
    }

    @Override
    public int hashCode() {
        int result = folder != null ? folder.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
