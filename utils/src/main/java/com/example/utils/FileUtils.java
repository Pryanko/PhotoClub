package com.example.utils;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;

import java.io.File;

/**
 * Класс со вспомогательными функциями для работы с файлами.
 *
 * @author Aleksandr Brazhkin
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static String getFileNemWithoutExtension(@NonNull File file) {
        String name = file.getName();
        int pos = name.lastIndexOf('.');
        if (pos > 0) {
            return name.substring(0, pos);
        } else {
            return name;
        }
    }

    /**
     * Удаляет файл.
     *
     * @param file    Удаляемый файл
     * @return {@code true} при успешном выполнении операции, {@code false} иначе
     */
    public static boolean deleteFile(@NonNull File file) {
        return file.delete();
    }

    /**
     * Удаляет папку.
     *
     * @param dir     Удаляемая папка
     * @return {@code true} при успешном выполнении операции, {@code false} иначе
     */
    public static boolean deleteDir(@NonNull File dir) {
        if (!dir.exists()) {
            logger.error("deleteDir: dir not found: " + dir.getAbsolutePath());
            return false;
        }

        // list all the directory contents
        File files[] = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                boolean result;
                // recursive deleting
                if (file.isDirectory()) {
                    result = deleteDir(file);
                } else {
                    result = deleteFile(file);
                }
                if (!result) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
