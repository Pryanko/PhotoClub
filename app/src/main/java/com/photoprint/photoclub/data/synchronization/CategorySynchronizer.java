package com.photoprint.photoclub.data.synchronization;

import android.support.annotation.NonNull;

import com.example.utils.ListUtils;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.category.Category;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.api.mapper.CategoryMapper;
import com.photoprint.photoclub.helper.runtimepermission.AppSchedulers;
import com.photoprint.photoclub.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

/**
 * Синхронайзер категорий услуг
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class CategorySynchronizer {

    private static final Logger logger = LoggerFactory.getLogger(CategorySynchronizer.class);

    private final ApiWorker apiWorker;
    private final DbTransaction transaction;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Inject
    CategorySynchronizer(ApiWorker apiWorker,
                         DbTransaction transaction,
                         CategoryRepository categoryRepository) {
        this.apiWorker = apiWorker;
        this.categoryRepository = categoryRepository;
        this.transaction = transaction;
    }

    public Completable sync() {
        logger.trace("sync");
        return apiWorker.getCategory()
                .map(Response::get)
                .map(Data::getData)
                .observeOn(AppSchedulers.db())
                .doOnSuccess(categories -> transaction.callInTx(() -> storeCategory(categories)))
                .toCompletable();
    }

    private List<Category> storeCategory(@NonNull List<Category> categories) {
        if (ListUtils.notEmpty(categories)) {
            categoryRepository.deleteAll();
            categoryRepository.insert(mapper.entityListToModelList(categories));
        }
        return categories;
    }
}
