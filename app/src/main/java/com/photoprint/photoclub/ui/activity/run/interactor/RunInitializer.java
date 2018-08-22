package com.photoprint.photoclub.ui.activity.run.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.auth.AuthManager;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.data.synchronization.AppSynchronizer;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
public class RunInitializer {

    private static final Logger logger = LoggerFactory.getLogger(RunInitializer.class);

    private final AuthManager authManager;
    private final AppSynchronizer appSynchronizer;

    @Inject
    RunInitializer(AuthManager authManager,
                   AppSynchronizer appSynchronizer) {
        this.authManager = authManager;
        this.appSynchronizer = appSynchronizer;
    }

    public Single<Result> init() {
        logger.trace("init");
        return authManager.register()
                .andThen(appSynchronizer.appDataSync())
                .toSingle(() -> new Result(UserError.NO_ERROR, true));
    }

    public static class Result {
        private final UserError userError;
        private final boolean initialaized;


        Result(@NonNull UserError userError, boolean initialaized) {
            this.userError = userError;
            this.initialaized = initialaized;

        }

        @NonNull
        public UserError getUserError() {
            return userError;
        }

        public boolean isInitialaized() {
            return initialaized;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }

}
