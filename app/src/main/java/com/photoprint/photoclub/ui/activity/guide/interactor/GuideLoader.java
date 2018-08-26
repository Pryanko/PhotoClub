package com.photoprint.photoclub.ui.activity.guide.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.repository.GuideRepository;
import com.photoprint.photoclub.ui.activity.category.interactor.CategoryLoader;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик гайдов
 *
 * @author Grigoriy Pryamov.
 */
public class GuideLoader {

    private static final Logger logger = LoggerFactory.getLogger(CategoryLoader.class);

    private final GuideRepository guideRepository;

    @Inject
    GuideLoader(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public Single<Result> getGuides() {
        return Single
                .fromCallable(guideRepository::getGuides)
                .map(guides -> new Result(UserError.NO_ERROR, guides))
                //                .onErrorReturn(throwable -> {
//                    UserError userError = userErrorBuilder.fromThrowable(throwable);
//                    return new Result(userError, Collections.emptyList(), true);
//                })
                .subscribeOn(AppSchedulers.db());
    }

    public static class Result {

        private final UserError userError;
        private final List<Guide> guides;

        Result(UserError userError, List<Guide> guides) {
            this.userError = userError;
            this.guides = guides;
        }

        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public List<Guide> getguides() {
            return guides;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }
}
