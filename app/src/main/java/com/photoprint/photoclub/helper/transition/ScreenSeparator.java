package com.photoprint.photoclub.helper.transition;

import com.photoprint.photoclub.model.enums.CategoryType;

import javax.inject.Inject;

/**
 * Класс, создержащий вспомогательные методы, реализующие частично логику для переходов
 *
 * @author Grigoriy Pryamov.
 */
public class ScreenSeparator {

    @Inject
    ScreenSeparator() {
    }

    public TypeTransition getTransitionForCategory(CategoryType categoryType) {
        switch (categoryType) {
            case PHOTO:
            case MAGNETS:
            case HOLST:
            default: {
                return TypeTransition.SETTINGS;
            }
            case CUPS:
            case SHORTS:
            case UNKNOWN: {
                return TypeTransition.SERVICE;
            }
        }
    }
}
