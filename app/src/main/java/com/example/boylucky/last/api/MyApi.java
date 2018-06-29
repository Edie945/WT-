package com.example.boylucky.last.api;

import com.example.boylucky.last.bean.ClassfiyBean;
import com.example.boylucky.last.bean.ClassfiyRightBean;
import com.example.boylucky.last.bean.ShopBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public interface MyApi {
    @GET("product/getCatagory")
    Observable<ClassfiyBean> getLift();
    @GET("product/getProductCatagory")
    Observable<ClassfiyRightBean> getRight(@Query("cid")String cid);
    @GET("product/getCarts")
    Observable<ShopBean> getShop(@Query("uid")String uid);
}
