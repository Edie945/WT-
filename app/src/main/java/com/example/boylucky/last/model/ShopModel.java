package com.example.boylucky.last.model;

import com.example.boylucky.last.bean.ShopBean;
import com.example.boylucky.last.utils.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class ShopModel {
    public void getDataFrom(String s, final GetModel getModel) {
        RetrofitUtil.getIntent().api()
                .getShop(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopBean value) {
                        getModel.getModel(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface GetModel{
        void getModel(ShopBean shopBean);
    }
}
