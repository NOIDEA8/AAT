package com.example.mvp.model;

import android.app.Activity;
import android.util.Log;

import com.example.mvp.presenter.playerPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {

    //1.私有构造方法
    private Model(){}
    //2.声明变量
    private static volatile Model instance;
    //3.书写getIstance
    public static Model getInstance(){
        if (instance== null){
            synchronized (playerPresenter.class){
                if (instance == null)
                    instance = new Model();
            }
        }
        return instance;
    }
    public void getSong(SongCallback callback) {
        Retrofit retrofit = new Retrofit.Builder() // 注意：这里应该是Retrofit的实际实例化方式，Retrofit本身不是一个可实例化的类
                .baseUrl("https://api.uomg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Get get = retrofit.create(Get.class);
        Call<Resourse<Song>> m = get.getJsonData("热歌榜", "json");

        m.enqueue(new Callback<Resourse<Song>>() {
            @Override
            public void onResponse(Call<Resourse<Song>> call, Response<Resourse<Song>> response) {
                if (response.isSuccessful()) {
                    Resourse<Song> body = response.body();
                    if (body != null && body.getData() != null) {
                        Log.d("model成功启动",String.valueOf(body.getData()));
                        callback.onSuccess(body.getData());
                    } else {
                        // 处理数据为空的情况
                        callback.onFailure(new Exception("Received null or empty data"));
                    }
                } else {
                    callback.onFailure(new Exception("Request failed with code " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Resourse<Song>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}