package com.photoprint.photoclub.data.synchronization;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.apppreferences.AppPrefs;
import com.photoprint.photoclub.helper.system.AppSystemMetrics;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

/**
 * Класс для общей синхронизации данных с данными сервера
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class AppSynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(AppSynchronizer.class);

    /**
     * Срок актуальности полученных данных
     */
    private static final long APP_DATA_ACTUALITY_TIME = TimeUnit.HOURS.toMillis(12); //12 Часов

    private final CategorySynchronizer categorySynchronizer;
    private final GuideSynchronizer guideSynchronizer;
    private final ServiceSynchronizer serviceSynchronizer;
    private final AppSystemMetrics appSystemMetrics;
    private final AppPrefs appPrefs;

    @Inject
    AppSynchronizer(CategorySynchronizer categorySynchronizer,
                    GuideSynchronizer guideSynchronizer,
                    ServiceSynchronizer serviceSynchronizer,
                    AppSystemMetrics appSystemMetrics,
                    AppPrefs appPrefs) {
        this.categorySynchronizer = categorySynchronizer;
        this.guideSynchronizer = guideSynchronizer;
        this.serviceSynchronizer = serviceSynchronizer;
        this.appSystemMetrics = appSystemMetrics;
        this.appPrefs = appPrefs;
    }

    public Completable appDataSync() {
        logger.trace("appDataSync: create");
        if (isSyncRequired()) {
            logger.trace("Synchronization enabled");
            return categorySynchronizer.sync()
                    .concatWith(guideSynchronizer.sync())
                    .concatWith(serviceSynchronizer.sync())
                    .doOnComplete(() -> saveSynchronizationTime(appSystemMetrics.getCurrentDeviceTime()));
        } else {
            logger.trace("Synchronization is not required");
            return Completable.complete();
        }
    }

    private boolean isSyncRequired() {
        return appSystemMetrics.getCurrentDeviceTime() - getSynchronizationTime() > APP_DATA_ACTUALITY_TIME;
    }

    private void saveSynchronizationTime(long time) {
        appPrefs.setDataSyncTime(time);
    }

    private long getSynchronizationTime() {
        return appPrefs.getDataSyncTime();
    }
}
