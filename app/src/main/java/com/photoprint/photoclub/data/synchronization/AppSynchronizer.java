package com.photoprint.photoclub.data.synchronization;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;

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
    private static final long APP_DATA_ACTUALITY_TIME = TimeUnit.DAYS.toMillis(1); //1 день

    private final CategorySynchronizer categorySynchronizer;
    private final GuideSynchronizer guideSynchronizer;
    private final ServiceSynchronizer serviceSynchronizer;

    @Inject
    AppSynchronizer(CategorySynchronizer categorySynchronizer,
                    GuideSynchronizer guideSynchronizer,
                    ServiceSynchronizer serviceSynchronizer) {
        this.categorySynchronizer = categorySynchronizer;
        this.guideSynchronizer = guideSynchronizer;
        this.serviceSynchronizer = serviceSynchronizer;
    }

    public Completable appDataSync() {
        logger.trace("appDataSync");
        return categorySynchronizer.sync()
                .concatWith(guideSynchronizer.sync())
                .concatWith(serviceSynchronizer.sync());
    }
}
