package com.example.slavko.retrofit;

import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Recommendation;
import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Track;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Slavko on 29.4.2016.
 */
public interface SongRecomenderApi {
    @GET("/suggest-similar/{songName}")
    Call<Recommendation> getTrackByName(@Path("songName") String songName);
}
