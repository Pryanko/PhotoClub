package com.photoprint.network;

/**
 * Оболочка для ответа из АПИ
 *
 * @author Grigoriy Pryamov.
 */
public class Response<T> {

    private final T data;
    private final int code;

    Response(T data, int code) {
        this.data = data;
        this.code = code;
    }

    public T get() {
        return data;
    }

    public int getCode() {
        return code;
    }

    /**
     * Returns true if the code is in [200..300), which means the request was successfully received,
     * understood, and accepted {@link okhttp3.Response#isSuccessful()}
     */
    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
