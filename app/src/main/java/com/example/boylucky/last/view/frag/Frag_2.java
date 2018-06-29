package com.example.boylucky.last.view.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boylucky.last.R;
import com.example.boylucky.last.adapter.LeftAdapter;
import com.example.boylucky.last.adapter.RightAdapter;
import com.example.boylucky.last.bean.ClassfiyBean;
import com.example.boylucky.last.bean.ClassfiyRightBean;
import com.example.boylucky.last.presenter.ClassfiyPresenter;
import com.example.boylucky.last.view.interfaces.IClassView;
import com.example.boylucky.last.view.interfaces.OnItemListent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class Frag_2 extends Fragment implements IClassView {

    @BindView(R.id.lift_rlv)
    RecyclerView mLiftRlv;
    @BindView(R.id.right_rlv)
    RecyclerView mRightRlv;
    private View view;
    private Unbinder unbinder;
    private ClassfiyPresenter classfiyPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_2, container, false);
        initView();
        initData();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        classfiyPresenter = new ClassfiyPresenter(this);
        classfiyPresenter.getData();
    }

    private void initView() {


    }

    @Override
    public void onSuccess(ClassfiyBean classfiyBean) {
        final List<ClassfiyBean.DataBean> data = classfiyBean.getData();
        LeftAdapter leftAdapter = new LeftAdapter(getContext(),data);
        mLiftRlv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mLiftRlv.setAdapter(leftAdapter);
        leftAdapter.setOnItemListent(new OnItemListent() {
            @Override
            public void onItemClickListent(View view, int prosion) {
                int cid = data.get(prosion).getCid();
                classfiyPresenter.getData2(cid);
            }
        });
    }

    @Override
    public void onSuccess(ClassfiyRightBean classfiyRightBean) {
        List<ClassfiyRightBean.DataBean> data = classfiyRightBean.getData();
        mRightRlv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        RightAdapter rightAdapter = new RightAdapter(getContext(), data);
        mRightRlv.setAdapter(rightAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        classfiyPresenter.dech();
    }
}
