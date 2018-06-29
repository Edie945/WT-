package com.example.boylucky.last.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.boylucky.last.R;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class JiaJianView extends LinearLayout {

    private TextView num;
    private Button jian;
    private Button jia;

    public JiaJianView(Context context) {
        this(context,null);
    }

    public JiaJianView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.jiajian, this);
        jian = findViewById(R.id.jian);
        num = findViewById(R.id.num);
        jia = findViewById(R.id.jia);
    }

    public void setNum(String str) {
        num.setText(str);
    }
    public String getNum() {
        return num.getText().toString();
    }


    public void setAddOnClickListent(OnClickListener onClickListent){
        jia.setOnClickListener(onClickListent);
    }
    public void setSubOnClickListent(OnClickListener onClickListent){
        jian.setOnClickListener(onClickListent);
    }
}
