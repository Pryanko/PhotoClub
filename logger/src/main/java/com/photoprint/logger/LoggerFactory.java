package com.photoprint.logger;

/**
 *
 * @author Grigoriy Pryamov.
 */
public class LoggerFactory {

    public static Logger getLogger(String name) {
        return new LoggerImpl(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }
}
