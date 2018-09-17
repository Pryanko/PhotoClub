package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter;

import android.support.annotation.NonNull;

import com.photoprint.photoclub.model.Maquette;

import java.util.List;

/**
 * @author Grigoriy Pryamov.
 */
public interface MaquetteListAdapter {

    void setMaquettes(@NonNull List<Maquette> maquettes);
}
