package com.example.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.mvp.model.Model;
import com.example.mvp.model.Song;
import com.example.mvp.model.SongCallback;

import java.util.ArrayList;
import java.util.List;

public class playerPresenter {
    static List<Song> songList;
    static Model model;
    static Activity activity;
    static int songOrder=-1;

    //1.私有构造方法
    private playerPresenter(Activity activity){
        this.activity=activity;
        this.model=Model.getInstance();
        songList=new ArrayList<>();
    }
    //2.声明变量
    private static volatile playerPresenter instance;
    //3.书写getIstance
    public static playerPresenter getInstance(Activity activity){
        if (instance== null){
            synchronized (playerPresenter.class){
                if (instance == null)
                    instance = new playerPresenter(activity);
            }
        }
        return instance;
    }


    public void clickBack(){
        activity.finish();
    }
    public void clickNext(SongCallback callback) {
        songOrder++;
        if (model != null&&songOrder==songList.size()) {
            model.getSong(new SongCallback() {
                @Override
                public void onSuccess(Song song) {
                    Log.d("player",song.getUrl());
                    // 处理获取到的歌曲
                    if (!conflictSong(song)) {
                        songList.add(song);
                        // 通知调用者成功获取到歌曲
                        callback.onSuccess(song);
                    } else {
                        // 如果歌曲冲突，调用 onFailure 传递一个自定义异常
                        callback.onFailure(new Exception("Song conflict"));
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    // 处理错误
                    callback.onFailure(t);
                }
            });
        } else if(model==null){
            // 处理 model 为 null 的情况
            Log.e("Model", "Model instance is null");
            // 通知调用者 model 为 null
            callback.onFailure(new NullPointerException("Model instance is null"));
        }
        else{
            callback.onSuccess(songList.get(songOrder));
        }
    }
    /*private Song clickPlay(){

    }*/
    public Song clickPrevious(){
        Song temp=null;
        if(songOrder==0||songOrder==-1){temp=null;}
        else{
            songOrder--;
            temp=songList.get(songOrder);
        }
        return temp;
    }
    
    private  boolean conflictSong(Song song){
        boolean judge=true;
        if(songList.size()<=0){return false;}
        for (int i = 0; i < songList.size(); i++) {
            //每首歌的url不同，只比这个就好
           judge=song.getUrl().equals(songList.get(i).getUrl());
        }
        return judge;
    }
}
