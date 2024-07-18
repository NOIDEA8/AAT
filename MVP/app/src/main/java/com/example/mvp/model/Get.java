package com.example.mvp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Get {
    @GET("api/rand.music")
    Call<Resourse<Song>> getJsonData(@Query("sort") String sort, @Query("format") String format);
}
