package com.photoprint.photoclub.ui.activity.category.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.model.Category;
import com.photoprint.photoclub.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик категорий услуг
 *
 * @author Grigoriy Pryamov.
 */
public class CategoryLoader {

    private static final Logger logger = LoggerFactory.getLogger(CategoryLoader.class);

    private final CategoryRepository categoryRepository;

    @Inject
    CategoryLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Single<Result> getCategories() {
        return Single
                .fromCallable(categoryRepository::getCategories)
                .map(categories -> new Result(UserError.NO_ERROR, categories))
//                .onErrorReturn(throwable -> {
//                    UserError userError = userErrorBuilder.fromThrowable(throwable);
//                    return new Result(userError, Collections.emptyList(), true);
//                })
                .subscribeOn(AppSchedulers.db());
    }

    public static class Result {

        private final UserError userError;
        private final List<Category> categories;

        Result(UserError userError, List<Category> categories) {
            this.userError = userError;
            this.categories = categories;
        }


        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public List<Category> getCategories() {
            return categories;
        }

        public boolean isSuccessful() {
            return userError == UserError.NO_ERROR;
        }
    }
}
