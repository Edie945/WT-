package com.example.boylucky.last.model;

import com.example.boylucky.last.bean.ClassfiyBean;
import com.example.boylucky.last.bean.ClassfiyRightBean;
import com.example.boylucky.last.utils.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class ClassFiyModel {
    public void getDataFrom(final GetModel getModel) {
        RetrofitUtil.getIntent().api()
                .getLift()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassfiyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassfiyBean value) {
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

    public void getDataFrom2(int cid, final GetModel getModel) {
        RetrofitUtil.getIntent().api()
                .getRight(cid+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassfiyRightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassfiyRightBean value) {
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
        void getModel(ClassfiyBean classfiyBean);
        void getModel(ClassfiyRightBean classfiyRightBean);
    }
}
