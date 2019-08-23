package com.cskaoyan.bean.wx.cart;

public class ProductIsChecked {
    private boolean isChecked;

    private int[] productIds;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int[] getProductIds() {
        return productIds;
    }

    public void setProductIds(int[] productIds) {
        this.productIds = productIds;
    }
}
