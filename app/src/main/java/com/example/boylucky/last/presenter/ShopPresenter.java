package com.example.boylucky.last.presenter;

import com.example.boylucky.last.bean.ShopBean;
import com.example.boylucky.last.model.ShopModel;
import com.example.boylucky.last.view.interfaces.IShopView;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class ShopPresenter {
    private IShopView iShopView;
    private ShopModel shopModel;

    public ShopPresenter(IShopView iShopView) {
        this.iShopView = iShopView;
        shopModel = new ShopModel();
    }

    public void getData(final String s) {
        shopModel.getDataFrom(s, new ShopModel.GetModel() {
            @Override
            public void getModel(ShopBean shopBean) {
                iShopView.onSuccess(shopBean);
            }
        });
    }
}
