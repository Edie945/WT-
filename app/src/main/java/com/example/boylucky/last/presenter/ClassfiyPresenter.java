package com.example.boylucky.last.presenter;

import com.example.boylucky.last.bean.ClassfiyBean;
import com.example.boylucky.last.bean.ClassfiyRightBean;
import com.example.boylucky.last.model.ClassFiyModel;
import com.example.boylucky.last.view.interfaces.IClassView;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class ClassfiyPresenter {
    private IClassView iClassView;
    private ClassFiyModel classFiyModel;
    public ClassfiyPresenter(IClassView iClassView) {
        this.iClassView = iClassView;
        classFiyModel = new ClassFiyModel();
    }

    public void getData() {
        classFiyModel.getDataFrom(new ClassFiyModel.GetModel() {
            @Override
            public void getModel(ClassfiyBean classfiyBean) {
                iClassView.onSuccess(classfiyBean);
            }

            @Override
            public void getModel(ClassfiyRightBean classfiyRightBean) {

            }
        });
    }

    public void dech(){
        if (iClassView != null){
            iClassView = null;
        }
    }

    public void getData2(int cid) {
        classFiyModel.getDataFrom2(cid,new ClassFiyModel.GetModel() {
            @Override
            public void getModel(ClassfiyBean classfiyBean) {

            }

            @Override
            public void getModel(ClassfiyRightBean classfiyRightBean) {
                iClassView.onSuccess(classfiyRightBean);
            }
        });
    }
}
