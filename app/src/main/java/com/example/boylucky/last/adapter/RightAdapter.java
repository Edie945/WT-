package com.example.boylucky.last.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boylucky.last.R;
import com.example.boylucky.last.bean.ClassfiyRightBean;

import java.util.List;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class RightAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassfiyRightBean.DataBean> data;
    private View inflate;

    public RightAdapter(Context context, List<ClassfiyRightBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = View.inflate(parent.getContext(), R.layout.right_item, null);
        return new MyViewHodel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHodel myViewHodel = (MyViewHodel) holder;
        myViewHodel.tv.setText(data.get(position).getName());
        List<ClassfiyRightBean.DataBean.ListBean> list = data.get(position).getList();
        RightImgAdapter rightImgAdapter = new RightImgAdapter(context, list);
        myViewHodel.rlv.setLayoutManager(new GridLayoutManager(context,3));
        myViewHodel.rlv.setAdapter(rightImgAdapter);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHodel extends RecyclerView.ViewHolder {
        TextView tv;
        RecyclerView rlv;
        public MyViewHodel(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            rlv = itemView.findViewById(R.id.rlv);
        }
    }
}
