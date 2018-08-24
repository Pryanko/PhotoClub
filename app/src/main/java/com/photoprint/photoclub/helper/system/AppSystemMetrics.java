package com.photoprint.photoclub.helper.system;

import java.util.Date;

/**
 * Прокси на систмеными данными
 *
 * @author Grigoriy Pryamov.
 */
public interface AppSystemMetrics {

    long getCurrentDeviceTime();

    Date getCurrentDeviceDate();
}
