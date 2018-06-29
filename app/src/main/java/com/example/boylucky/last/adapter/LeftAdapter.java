package com.example.boylucky.last.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boylucky.last.R;
import com.example.boylucky.last.bean.ClassfiyBean;
import com.example.boylucky.last.common.Contanx;
import com.example.boylucky.last.view.interfaces.OnItemListent;

import java.util.List;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class LeftAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassfiyBean.DataBean> data;
    private View inflate;
    private OnItemListent onItemListent;

    public void setOnItemListent(OnItemListent onItemListent){
        this.onItemListent = onItemListent;
    }

    public LeftAdapter(Context context, List<ClassfiyBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = View.inflate(parent.getContext(), R.layout.left_item, null);
        return new MyViewHodel(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHodel myViewHodel = (MyViewHodel) holder;
        myViewHodel.tv.setText(data.get(position).getName());
        myViewHodel.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListent.onItemClickListent(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHodel extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHodel(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
