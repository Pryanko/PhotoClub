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
import com.photoprint.photoclub.data.synchronization.base.BaseSynchronizer;
import com.photoprint.photoclub.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Синхронайзер категорий услуг
 *
 * @author Grigoriy Pryamov.
 */
@Singleton
public class CategorySynchronizer extends BaseSynchronizer<Category> {

    private static final Logger logger = LoggerFactory.getLogger(CategorySynchronizer.class);

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Inject
    CategorySynchronizer(ApiWorker apiWorker,
                         DbTransaction transaction,
                         CategoryRepository categoryRepository) {
        super(apiWorker, transaction);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Single<Response<Data<Category>>> getSync() {
        logger.trace("Category sync: Start");
        return apiWorker.getCategory();
    }

    @Override
    protected List<Category> store(@NonNull List<Category> categories) {
        if (ListUtils.notEmpty(categories)) {
            categoryRepository.deleteAll();
            categoryRepository.insert(mapper.entityListToModelList(categories));
            logger.trace("Category store - success");
        }
        return categories;
    }
}
