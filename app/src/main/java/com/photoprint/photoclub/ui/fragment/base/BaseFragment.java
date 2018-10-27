package com.photoprint.photoclub.ui.fragment.base;


import android.support.v4.app.Fragment;

import com.photoprint.photoclub.AppComponent;
import com.photoprint.photoclub.di.Dagger;

/**
 * Базовый класс для всех {@link Fragment} приложения.
 *
 * @author Grigoriy Pryamov.
 */
public class BaseFragment extends Fragment {

    protected final AppComponent appComponent() {
        return Dagger.appComponent();
    }
}
