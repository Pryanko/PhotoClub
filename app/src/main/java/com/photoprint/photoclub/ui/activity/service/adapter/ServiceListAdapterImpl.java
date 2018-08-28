package com.photoprint.photoclub.ui.activity.service.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.ScreenScope;
import com.photoprint.photoclub.model.Service;
import com.photoprint.photoclub.ui.adapter.base.BaseItemsRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class ServiceListAdapterImpl
        extends BaseItemsRecyclerAdapter<Service, ServiceListAdapterImpl.ServiceHolder>
        implements ServiceListAdapter {

    private InteractionListener interactionListener;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    @Inject
    ServiceListAdapterImpl() {
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_service, parent, false);
        return new ServiceHolder(v);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        spinnerAdapter = ArrayAdapter
                .createFromResource(context, R.array.paper_type, R.layout.service_spinner_item);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void setServices(@NonNull List<Service> services) {
        DiffUtilCallback diffUtilCallback = new DiffUtilCallback(this.items, services);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback, false);
        this.items = services;
        diffResult.dispatchUpdatesTo(this);
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    class ServiceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.serviceImage)
        SimpleDraweeView serviceImage;
        @BindView(R.id.serviceName)
        TextView serviceName;
        @BindView(R.id.servicePrice)
        TextView servicePrice;
        @BindView(R.id.spinner)
        Spinner spinner;

        ServiceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (interactionListener != null) {
                    interactionListener.onServiceClicked(getAdapterPosition());
                }
            });

        }

        void bind(Service service) {
            serviceName.setText(service.getName());
            servicePrice.setText(service.getPrice());
            spinner.setAdapter(spinnerAdapter);
        }
    }

    public interface InteractionListener {
        void onServiceClicked(int position);
    }
}
