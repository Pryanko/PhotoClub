package com.photoprint.photoclub.ui.activity.guide.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.photoprint.photoclub.model.Guide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public class GuideAdapterImpl extends FragmentStatePagerAdapter {

    private List<Guide> guides = new ArrayList<>();

    public GuideAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return guides.size();
    }

    public void setGuides(List<Guide> guides) {
        this.guides = guides;
    }
}
