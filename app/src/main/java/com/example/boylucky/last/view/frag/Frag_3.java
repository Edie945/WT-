package com.example.boylucky.last.view.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.boylucky.last.R;
import com.example.boylucky.last.adapter.ElvAdapter;
import com.example.boylucky.last.bean.PriceBean;
import com.example.boylucky.last.bean.QuanBean;
import com.example.boylucky.last.bean.ShopBean;
import com.example.boylucky.last.presenter.ShopPresenter;
import com.example.boylucky.last.view.interfaces.IShopView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class Frag_3 extends Fragment implements IShopView {

    @BindView(R.id.elv)
    ExpandableListView mElv;
    @BindView(R.id.shop)
    TextView mShop;
    @BindView(R.id.quan_box)
    CheckBox mQuanBox;
    @BindView(R.id.allprice)
    TextView mAllprice;
    private View view;
    private Unbinder unbinder;
    private ElvAdapter elvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_3, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mQuanBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elvAdapter.setQuan(mQuanBox.isChecked());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onMessageEvent(QuanBean event) {
        mQuanBox.setChecked(event.isQuan());
    }

    //    @Subscribe
//    public void onMessageEvent(PriceBean event) {
//        mZongjia.setText(""+event.getPrice() );
//    }
    @Subscribe
    public void onPrice(PriceBean priceBean) {
        mAllprice.setText("￥ " + priceBean.getPrice());
    }

    private void initData() {
        ShopPresenter shopPresenter = new ShopPresenter(this);
        shopPresenter.getData("13592");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(ShopBean shopBean) {
        List<ShopBean.DataBean> data = shopBean.getData();
        //设置 属性 GroupIndicator 去掉默认向下的箭头
        mElv.setGroupIndicator(null);
        elvAdapter = new ElvAdapter(getContext(), data);
        mElv.setAdapter(elvAdapter);
        for (int i = 0; i < data.size(); i++) {
            mElv.expandGroup(i);
        }

    }
}
