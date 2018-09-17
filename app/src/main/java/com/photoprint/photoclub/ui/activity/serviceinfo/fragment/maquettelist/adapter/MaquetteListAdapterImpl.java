package com.photoprint.photoclub.ui.activity.serviceinfo.fragment.maquettelist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.model.Maquette;
import com.photoprint.photoclub.ui.adapter.base.BaseItemsRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class MaquetteListAdapterImpl extends BaseItemsRecyclerAdapter<Maquette, MaquetteListAdapterImpl.MaquetteHolder> implements MaquetteListAdapter {

    @Inject
    MaquetteListAdapterImpl() {
    }

    private MaquetteListAdapterImpl.InteractionListener interactionListener;

    @NonNull
    @Override
    public MaquetteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_maquette, parent, false);
        return new MaquetteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MaquetteHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void setMaquettes(@NonNull List<Maquette> maquettes) {
        DiffUtilCallback diffUtilCallback = new DiffUtilCallback(this.items, maquettes);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback, false);
        this.items = maquettes;
        diffResult.dispatchUpdatesTo(this);
    }

    class MaquetteHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        SimpleDraweeView imageView;

        MaquetteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (interactionListener != null) {
                    interactionListener.onMaquetteClicked(getItem(getAdapterPosition()));
                }
            });
        }

        void bind(Maquette maquette) {
            imageView.setImageURI(maquette.getImageMaquette());
        }
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    /**
     * Взаимодействие
     */
    public interface InteractionListener {
        void onMaquetteClicked(Maquette maquette);
    }
}
