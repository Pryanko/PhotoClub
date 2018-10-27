package com.photoprint.photoclub.ui.activity.gallery.fragment.image.imagesettingcard;

import com.photoprint.photoclub.ui.mvp.viewstate.BaseMvpViewState;

import javax.inject.Inject;

/**
 * @author Grigoriy Pryamov.
 */
class ImageSettingCardViewState extends BaseMvpViewState<ImageSettingCardView> implements ImageSettingCardView {

    private boolean cardEnabled;

    @Inject
    ImageSettingCardViewState() {
    }

    @Override
    protected void onViewAttached(ImageSettingCardView view) {
        view.setCardEnabled(cardEnabled);
    }

    @Override
    protected void onViewDetached(ImageSettingCardView view) {

    }

    @Override
    public void setCardEnabled(boolean enabled) {
        this.cardEnabled = enabled;
        forEachView(view -> view.setCardEnabled(this.cardEnabled));
    }
}
