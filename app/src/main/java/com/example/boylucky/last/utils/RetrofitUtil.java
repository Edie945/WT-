package com.example.boylucky.last.utils;

import com.example.boylucky.last.api.MyApi;
import com.example.boylucky.last.common.Contanx;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BoyLucky on 2018/6/27.
 */

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private static MyApi myApi;
    public RetrofitUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contanx.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        myApi = retrofit.create(MyApi.class);
    }

    public static RetrofitUtil getIntent(){
        if (retrofitUtil == null ){
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    public MyApi api(){
        return myApi;
    }

}
