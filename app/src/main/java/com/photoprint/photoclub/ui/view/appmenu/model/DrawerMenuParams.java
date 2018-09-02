package com.photoprint.photoclub.ui.view.appmenu.model;

/**
 * Праметры для бокового меню
 *
 * @author Grigoriy Pryamov.
 */
public class DrawerMenuParams {
    /**
     * Активный раздел бокового меню
     */
    private DrawerMenuItem selectedItem;

    public DrawerMenuItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(DrawerMenuItem selectedItem) {
        this.selectedItem = selectedItem;
    }
}
