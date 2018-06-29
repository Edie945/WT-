package com.example.boylucky.last.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.boylucky.last.R;
import com.example.boylucky.last.bean.PriceBean;
import com.example.boylucky.last.bean.QuanBean;
import com.example.boylucky.last.bean.ShopBean;
import com.example.boylucky.last.view.JiaJianView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class ElvAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ShopBean.DataBean> data;

    public ElvAdapter(Context context, List<ShopBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolde viewHolde;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.group_item,null);
            viewHolde = new GroupViewHolde();
            viewHolde.group_box = convertView.findViewById(R.id.group_box);
            viewHolde.group_name = convertView.findViewById(R.id.group_name);
            convertView.setTag(viewHolde);
        }else{
          viewHolde = (GroupViewHolde) convertView.getTag();
        }
        viewHolde.group_name.setText(data.get(groupPosition).getSellerName());
        viewHolde.group_box.setChecked(data.get(groupPosition).isGroup_box());

        viewHolde.group_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(groupPosition).setGroup_box(viewHolde.group_box.isChecked());
                childBox(groupPosition,viewHolde.group_box.isChecked());
                EventBus.getDefault().post(setPrice());

                if (viewHolde.group_box.isChecked()){
                    if (groupAllBox()){
                        getQuan(viewHolde.group_box.isChecked());

                    }
                }else{
                    getQuan(viewHolde.group_box.isChecked());

                }

                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolde viewHolde;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.child_item,null);
            viewHolde = new ChildViewHolde();
            viewHolde.child_box = convertView.findViewById(R.id.child_box);
            viewHolde.child_name = convertView.findViewById(R.id.child_name);
            viewHolde.child_img = convertView.findViewById(R.id.child_img);
            viewHolde.child_price = convertView.findViewById(R.id.child_price);
            viewHolde.count = convertView.findViewById(R.id.count);
            convertView.setTag(viewHolde);
        }else{
            viewHolde = (ChildViewHolde) convertView.getTag();
        }
        viewHolde.child_name.setText(data.get(groupPosition).getList().get(childPosition).getTitle());
        viewHolde.child_box.setChecked(data.get(groupPosition).getList().get(childPosition).isChild_box());
        viewHolde.child_price.setText(data.get(groupPosition).getList().get(childPosition).getPrice()+"");
        viewHolde.child_img.setImageURI(Uri.parse(data.get(groupPosition).getList().get(childPosition).getImages().split("\\|")[0]));
        viewHolde.count.setNum(data.get(groupPosition).getList().get(childPosition).getNum()+"");

        viewHolde.child_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(groupPosition).getList().get(childPosition).setChild_box(viewHolde.child_box.isChecked());
                EventBus.getDefault().post(setPrice());
                if (viewHolde.child_box.isChecked()){
                    if (childAllBox(groupPosition)){
                        groupBox(groupPosition,viewHolde.child_box.isChecked());
                        getQuan(viewHolde.child_box.isChecked());

                    }
                }else{
                    groupBox(groupPosition,viewHolde.child_box.isChecked());
                    getQuan(viewHolde.child_box.isChecked());

                }
                notifyDataSetChanged();
            }
        });
        //加号
        viewHolde.count.setAddOnClickListent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = data.get(groupPosition).getList().get(childPosition).getNum();
                num++;
                data.get(groupPosition).getList().get(childPosition).setNum(num);
                viewHolde.count.setNum(num+"");
                if (viewHolde.child_box.isChecked()){
                    EventBus.getDefault().post(setPrice());
                }
                notifyDataSetChanged();
            }
        });
        //减号
        viewHolde.count.setSubOnClickListent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = data.get(groupPosition).getList().get(childPosition).getNum();
                if (num > 1){
                    num--;
                    data.get(groupPosition).getList().get(childPosition).setNum(num);
                    viewHolde.count.setNum(num+"");
                    if (viewHolde.child_box.isChecked()){
                        EventBus.getDefault().post(setPrice());
                    }
                }

                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolde{
        private CheckBox group_box;
        private TextView group_name;
    }
    class ChildViewHolde{
        private CheckBox child_box;
        private SimpleDraweeView child_img;
        private TextView child_name;
        private TextView child_price;
        private JiaJianView count;
    }

    /**
     * 改变商家的checkedbox状态
     */
    public void groupBox(int porsion,boolean flag){
        data.get(porsion).setGroup_box(flag);
    }
    /**
     * 改变商家商品chekcedbox状态
     */
    public void childBox(int porsion,boolean flag){
        List<ShopBean.DataBean.ListBean> list = data.get(porsion).getList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChild_box(flag);
        }
    }
    /**
     * 判断商家checkedbox是否全选
     */
    public boolean groupAllBox(){
        for (int i = 0; i < data.size(); i++) {
            boolean group_box = data.get(i).isGroup_box();
            if (!group_box){
                return false;
            }
        }
        return true;
    }
    /**
     * 判断商品的checkedbox是否全选
     */
    public boolean childAllBox(int porsion){
        List<ShopBean.DataBean.ListBean> list = data.get(porsion).getList();
        for (int i = 0; i < list.size(); i++) {
            boolean child_box = list.get(i).isChild_box();
            if (!child_box){
                return false;
            }
        }
        return true;
    }

    /**
     * 商品全选，选中全选
     * @param falg
     */
    public void getQuan(boolean falg){
        QuanBean quanBean = new QuanBean();
        quanBean.setQuan(falg);
        EventBus.getDefault().post(quanBean);
    }
    /**
     * 点击全选，
     */
    public void setQuan(boolean flag){
        for (int i = 0; i < data.size(); i++) {
            groupBox(i,flag);
            childBox(i,flag);
        }
        EventBus.getDefault().post(setPrice());
        notifyDataSetChanged();
    }

    /*
    计算总价
     */
    private PriceBean setPrice(){
        int price = 0;
        for (int j = 0; j < data.size(); j++) {
            List<ShopBean.DataBean.ListBean> list = data.get(j).getList();
            for (int i = 0; i < list.size(); i++) {
                ShopBean.DataBean.ListBean listBean = list.get(i);
                if (listBean.isChild_box()){
                    price+=listBean.getPrice()*listBean.getNum();
                }
            }
        }
        PriceBean priceBean = new PriceBean();
        priceBean.setPrice(price);
        return priceBean;
    }

}
