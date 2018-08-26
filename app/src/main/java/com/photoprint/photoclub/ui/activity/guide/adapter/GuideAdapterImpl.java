package com.photoprint.photoclub.ui.activity.guide.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.photoprint.photoclub.model.Guide;
import com.photoprint.photoclub.ui.activity.guide.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер для экрана гайдов
 *
 * @author Grigoriy Pryamov.
 */
public class GuideAdapterImpl extends FragmentStatePagerAdapter {

    private List<Guide> guides = new ArrayList<>();

    public GuideAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        GuideFragment guideFragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(GuideFragment.EXTRA_GUIDE, guides.get(position));
        guideFragment.setArguments(bundle);
        return guideFragment;
    }

    @Override
    public int getCount() {
        return guides.size();
    }

    public void setGuides(List<Guide> guides) {
        this.guides = guides;
        notifyDataSetChanged();
    }
}
