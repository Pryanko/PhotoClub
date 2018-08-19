package com.photoprint.network;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Конвертер ответа с сервера в формате retrofit {@link retrofit2.Response}
 * во внутренний формат {@link Response}.
 *
 * @author Dmitry Vinogradov
 */
public class ResponseConverter {

    /**
     * Конвертирует {@link retrofit2.Response} в {@link Response}.
     *
     * @param retrofitResponse Ответ сервера в формате {@link Retrofit}
     * @param <E>              Тип сущности сервера
     * @return Ответ сервера в формате {@link Response}
     * @throws ApiException В случае ошибки  ковертации
     */
    public static <E> Response<E> convert(retrofit2.Response<E> retrofitResponse) throws ApiException {
        if (retrofitResponse.isSuccessful()) {
            E data = retrofitResponse.body();
            return new Response<>(data, retrofitResponse.code());
        } else {
            ResponseBody responseBody = retrofitResponse.errorBody();
            if (responseBody == null) {
                throw new ApiException(retrofitResponse.code());
            } else {
                try {
                    String message = responseBody.string();
                    throw new ApiException(message, retrofitResponse.code());
                } catch (Exception e) {
                    throw new ApiException(e, retrofitResponse.code());
                }
            }
        }
    }
}