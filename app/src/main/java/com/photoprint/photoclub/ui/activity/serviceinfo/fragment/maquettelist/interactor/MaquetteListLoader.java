package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.interactor;

import android.support.annotation.NonNull;

import com.photoprint.logger.Logger;
import com.photoprint.logger.LoggerFactory;
import com.photoprint.photoclub.data.model.UserError;
import com.photoprint.photoclub.model.Maquette;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Загрузчик макетов
 *
 * @author Grigoriy Pryamov.
 */
public class MaquetteListLoader {

    private static final Logger logger = LoggerFactory.getLogger(MaquetteListLoader.class);

    @Inject
    public MaquetteListLoader() {
    }

    public Single<Result> getMaquettes() {
        return Single.fromCallable(this::testMaquettes)
                .map(maquettes -> new Result(UserError.NO_ERROR, maquettes));
    }

    private List<Maquette> testMaquettes() {
        List<Maquette> maquettes = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Maquette maquette = new Maquette();
            maquette.setId((long) i);
            maquette.setName("Макет");
            maquette.setImageMaquette("https://getfile.dokpub.com/yandex/get/https://yadi.sk/i/l9QMn25IrrcF7g");
            maquettes.add(maquette);
        }
        return maquettes;
    }

    public static class Result {

        private final UserError userError;
        private final List<Maquette> maquettes;

        Result(UserError userError, List<Maquette> maquettes) {
            this.userError = userError;
            this.maquettes = maquettes;
        }

        @NonNull
        public UserError getUserError() {
            return userError;
        }

        @NonNull
        public List<Maquette> getMaquettes() {
            return maquettes;
        }
    }
}
