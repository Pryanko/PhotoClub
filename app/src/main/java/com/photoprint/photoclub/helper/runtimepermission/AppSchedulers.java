package com.photoprint.photoclub.helper.runtimepermission;

import com.photoprint.utils.Executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Static factory methods for returning standard Scheduler instances.
 *
 * @author Aleksandr Brazhkin and Grigory Pryamov
 */
public class AppSchedulers {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors(); // количество доступных ядер процессора
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1; // количество потоков для работы
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1; // максимальное количество потоков для работы
    private static final int KEEP_ALIVE = 1;

    private static final Executor NETWORK_EXECUTOR = Executors.newLoggableThreadPoolExecutor(
            "NetworkThread",
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(128), // количество задач очереди
            r -> new Thread(r, "NetworkThread"));

    private static final Executor BACKGROUND_EXECUTOR = Executors.newLoggableThreadPoolExecutor(
            "BackgroundThread",
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(128), // количество задач очереди
            r -> new Thread(r, "BackgroundThread"));

    private static final ExecutorService DB_EXECUTOR = Executors.newLoggableSingleThreadExecutor("DbThread", r -> new Thread(r, "DbThread"));
    private static final Scheduler DB_SCHEDULER = Schedulers.from(DB_EXECUTOR);
    private static final Scheduler NETWORK_SCHEDULER = Schedulers.from(NETWORK_EXECUTOR);
    private static final Scheduler BACKGROUND_SCHEDULER = Schedulers.from(BACKGROUND_EXECUTOR);

    /**
     * Возвращает Scheduler для работы с UI.
     */
    public static Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    /**
     * Возвращает Scheduler для работы с БД.
     */
    public static Scheduler db() {
        return DB_SCHEDULER;
    }

    /**
     * Возвращает Scheduler для работы в фоне.
     */
    public static Scheduler background() {
        return BACKGROUND_SCHEDULER;
    }

    /**
     * Возвращает Scheduler для работы с сетью.
     */
    public static Scheduler network() {
        return NETWORK_SCHEDULER;
    }
}