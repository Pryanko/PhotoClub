package com.photoprint.photoclub.helper.runtimepermission;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;

/**
 * Обработчик {@link UndeliverableException} для Rx.
 *
 * @author Grigoriy Pryamov.
 */
public class RxErrorHandler implements Consumer<Throwable> {

    private static final Logger logger = LoggerFactory.getLogger(RxErrorHandler.class);

    @Override
    public void accept(Throwable throwable) throws Exception {
        Throwable original;
        if (throwable instanceof UndeliverableException) {
            original = throwable.getCause();
        } else {
            original = throwable;
        }
        if (original instanceof UnknownHostException) {
            logger.trace("skipping UnknownHostException");
        } else if (original instanceof ConnectException) {
            logger.trace("skipping ConnectException");
        } else if (original instanceof SSLHandshakeException) {
            logger.trace("skipping SSLHandshakeException");
        } else if (original instanceof SocketTimeoutException) {
            logger.trace("skipping SocketTimeoutException");
//        } else if (original instanceof ApiException) {
//            logger.trace("skipping ApiException");
        } else {
            throw new RuntimeException(throwable);
        }
    }
}
