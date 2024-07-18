package com.example.mvp.model;

public interface SongCallback {
    void onSuccess(Song song);
    void onFailure(Throwable t);
}
