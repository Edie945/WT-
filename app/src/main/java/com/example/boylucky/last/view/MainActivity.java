package com.example.boylucky.last.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.boylucky.last.R;
import com.example.boylucky.last.view.frag.Frag_1;
import com.example.boylucky.last.view.frag.Frag_2;
import com.example.boylucky.last.view.frag.Frag_3;
import com.example.boylucky.last.view.frag.Frag_4;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomTabBar)
    BottomTabBar mBottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(16)
                .setChangeColor(Color.RED,Color.BLACK)
                .addTabItem("首页",R.mipmap.home1,new Frag_1().getClass())
                .addTabItem("分类",R.mipmap.classify1,new Frag_2().getClass())
                .addTabItem("购物车",R.mipmap.shop1,new Frag_3().getClass())
                .addTabItem("我的",R.mipmap.me1,new Frag_4().getClass())
                .isShowDivider(true);
    }

}
