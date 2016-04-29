package com.example.slavko.retrofit;

import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Track;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Slavko on 29.4.2016.
 */
public interface SongRecomenderApi {
    @GET("/sp/tracks")
    Call<List<Track>> getTrackByName(@Query("trackNames") String trackName);
}
