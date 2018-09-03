package com.photoprint.photoclub.ui.view.appmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.photoprint.photoclub.R;
import com.photoprint.photoclub.di.Dagger;
import com.photoprint.photoclub.ui.mvp.MvpDelegate;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuItem;
import com.photoprint.photoclub.ui.view.appmenu.model.DrawerMenuParams;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Боковое меню
 *
 * @author Grigoriy Pryamov.
 */
public class DrawerMenuView extends FrameLayout implements DrawerMenuMvpView {

    //region Di
    private DrawerMenuComponent component;
    //endregion
    //region Mvp
    private MvpDelegate mvpDelegate;
    private DrawerMenuPresenter presenter;
    //endregion
    //region Views
    @BindView(R.id.menuItemNewOrder)
    TextView menuItemNewOrder;
    @BindView(R.id.menuItemMyOrder)
    TextView menuItemMyOrder;
    @BindView(R.id.menuItemInstruction)
    TextView menuItemInstruction;
    //endregion
    private InteractionListener interactionListener;

    public DrawerMenuView(@NonNull Context context) {
        this(context, null, 0);
    }

    public DrawerMenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerMenuView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            component = Dagger.appComponent().drawerMenuComponent();
            component.inject(this);
        }
        View.inflate(getContext(), R.layout.view_drawer_menu, this);
        ButterKnife.bind(this);

        if (isInEditMode()) {
            return;
        }

        menuItemNewOrder.setOnClickListener(v -> interactionListener.onNewOrderClicked());

        menuItemMyOrder.setOnClickListener(v -> interactionListener.onMyOrderClicked());

        menuItemInstruction.setOnClickListener(v -> interactionListener.onInstructionClicked()
        );

        mvpDelegate = new MvpDelegate(component.mvpProcessor(), this);
    }

    public void init(MvpDelegate parent, String id) {
        mvpDelegate.init(parent, id);
        presenter = mvpDelegate.getPresenter(component::drawerMenuPresenter, DrawerMenuPresenter.class);
        presenter.initialize();
    }

    public void setInteractionListener(InteractionListener interactionListener) {
        this.interactionListener = interactionListener;
    }

    @Override
    public void setSelectedItem(@Nullable DrawerMenuItem drawerMenuItem) {
        if (drawerMenuItem == null) {
            return;
        }
        switch (drawerMenuItem) {
            case NEW_ORDER: {
                menuItemNewOrder.setSelected(true);
                break;
            }
            case MY_ORDER: {
                menuItemMyOrder.setSelected(true);
                break;
            }
            case INSTRUCTION: {
                break;
            }
            case DELIVERY: {
                break;
            }
            case APPLICATION: {
                break;
            }
        }
    }

    public void setParams(DrawerMenuParams drawerMenuParams) {
        presenter.setParams(drawerMenuParams);
    }

    @Override
    public void setVersionName(String name, int code) {

    }

    @Override
    public void setUserName(String userName) {

    }

    public interface InteractionListener {

        void onNewOrderClicked();

        void onMyOrderClicked();

        void onInstructionClicked();
    }
}
