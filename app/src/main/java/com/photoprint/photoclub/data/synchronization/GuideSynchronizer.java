package com.photoprint.photoclub.data.synchronization;

import android.support.annotation.NonNull;

import com.photoprint.utils.ListUtils;
import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.network.Response;
import com.photoprint.network.api.ApiWorker;
import com.photoprint.network.api.model.base.Data;
import com.photoprint.network.api.model.manual.Guide;
import com.photoprint.photoclub.base.DbTransaction;
import com.photoprint.photoclub.data.api.mapper.GuideMapper;
import com.photoprint.photoclub.data.synchronization.base.BaseSynchronizer;
import com.photoprint.photoclub.repository.GuideRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
public class GuideSynchronizer extends BaseSynchronizer<Guide> {

    private static final Logger logger = LoggerFactory.getLogger(GuideSynchronizer.class);

    private final GuideRepository guideRepository;
    private final GuideMapper mapper = GuideMapper.INSTANCE;

    @Inject
    GuideSynchronizer(ApiWorker apiWorker,
                      DbTransaction transaction,
                      GuideRepository guideRepository) {
        super(apiWorker, transaction);
        this.guideRepository = guideRepository;
    }

    @Override
    protected Single<Response<Data<Guide>>> getApiSource() {
        logger.trace("Guide sync: Start");
        return apiWorker.getManuals();
    }

    @Override
    protected List<Guide> store(@NonNull List<Guide> entities) {
        if (ListUtils.notEmpty(entities)) {
            guideRepository.deleteAll();
            guideRepository.insert(mapper.entityListToModelList(entities));
            logger.trace("Guide store - success");
        }
        return entities;
    }
}
