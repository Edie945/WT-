package com.example.boylucky.last.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boylucky.last.R;
import com.example.boylucky.last.bean.ClassfiyRightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class RightImgAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassfiyRightBean.DataBean.ListBean> list;
    private View inflate;

    public RightImgAdapter(Context context, List<ClassfiyRightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = View.inflate(parent.getContext(), R.layout.rigth_nei_item, null);
        return new MyViewHodel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHodel myViewHodel = (MyViewHodel) holder;
        myViewHodel.tv.setText(list.get(position).getName());
        myViewHodel.r_img.setImageURI(Uri.parse(list.get(position).getIcon().split("\\|")[0]));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHodel extends RecyclerView.ViewHolder {
        TextView tv;
        SimpleDraweeView r_img;
        public MyViewHodel(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            r_img = itemView.findViewById(R.id.r_img);
        }
    }
}
