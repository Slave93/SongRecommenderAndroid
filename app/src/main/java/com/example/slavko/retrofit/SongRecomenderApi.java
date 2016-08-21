package com.example.slavko.retrofit;

import com.example.slavko.retrofit.com.example.slavko.retrofit.model.ClusteringResponse;
import com.example.slavko.retrofit.com.example.slavko.retrofit.model.Recommendation;
import com.example.slavko.retrofit.com.example.slavko.retrofit.model.RecommendationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface SongRecomenderApi {
    @GET("/suggest-similar/{songName}")
    Call<Recommendation> suggestSimilar(@Path("songName") String songName, @Query("genre") String genre);

    @POST("/suggest-similar")
    Call<Recommendation> performRecommendation(@Body RecommendationRequest recommendationRequest);

    @GET("/perform-clustering/{clusteringAlgorithm}")
    Call<ClusteringResponse> performClustering(@Path("clusteringAlgorithm") String clusteringAlgorithm);
}
