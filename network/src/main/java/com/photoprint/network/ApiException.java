package com.photoprint.network;

import io.reactivex.annotations.NonNull;

/**
 * Исключение для внутренних ошибок запроса
 *
 * @author Grigoriy Pryamov.
 */
public class ApiException extends Exception {

    private int statusCode;

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApiException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public ApiException(@NonNull Response response) {
        this.statusCode = response.getCode();
    }

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
