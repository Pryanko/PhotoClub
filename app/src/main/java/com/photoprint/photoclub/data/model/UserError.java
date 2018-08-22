package com.photoprint.photoclub.data.model;

/**
 * @author Grigoriy Pryamov.
 */
public interface UserError {

    UserError NO_ERROR = () -> "";

    /**
     * Возвращает текст сообщения
     */
    String getMessage();
}
