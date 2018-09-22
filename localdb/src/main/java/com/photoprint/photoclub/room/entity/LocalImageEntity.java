package com.photoprint.photoclub.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.photoprint.photoclub.room.entity.base.EntityWithId;

/**
 * @author Grigoriy Pryamov.
 */
@Entity(tableName = "LocalImage")
public class LocalImageEntity implements EntityWithId<Long> {
    /**
     * Id локального изображения
     */
    @PrimaryKey(autoGenerate = true)
    private long id;
    /**
     * Родительская (Основная) папка местоположения изображения
     */
    private String parentFolder;
    /**
     * Полный путь до изображения
     */
    private String fullPath;
    /**
     * Выбрано ли изображение для последующей печати
     */
    private boolean selectedForPrint;
    /**
     * Отправлено ли изображение на сервер
     */
    private boolean sentToServer;
    /**
     * Кол-во фотографий для печати
     */
    private int countPrint;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(String parentFolder) {
        this.parentFolder = parentFolder;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public boolean isSelectedForPrint() {
        return selectedForPrint;
    }

    public void setSelectedForPrint(boolean selectedForPrint) {
        this.selectedForPrint = selectedForPrint;
    }

    public boolean isSentToServer() {
        return sentToServer;
    }

    public void setSentToServer(boolean sentToServer) {
        this.sentToServer = sentToServer;
    }

    public int getCountPrint() {
        return countPrint;
    }

    public void setCountPrint(int countPrint) {
        this.countPrint = countPrint;
    }
}
