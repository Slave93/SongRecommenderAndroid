package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

/**
 * Created by igor on 7/3/16.
 */
public class RecommendationRequest {
    private String songName;
    private FeatureWeights featureWeights;
    private String criteriaKey;
    private String criteriaValue;

    public String getCriteriaKey() {
        return criteriaKey;
    }

    public void setCriteriaKey(String criteriaKey) {
        this.criteriaKey = criteriaKey;
    }

    public String getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(String criteriaValue) {
        this.criteriaValue = criteriaValue;
    }

    public FeatureWeights getFeatureWeights() {
        return featureWeights;
    }

    public void setFeatureWeights(FeatureWeights featureWeights) {
        this.featureWeights = featureWeights;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "RecommendationRequest{" +
                "criteriaKey='" + criteriaKey + '\'' +
                ", songName='" + songName + '\'' +
                ", featureWeights=" + featureWeights +
                ", criteriaValue='" + criteriaValue + '\'' +
                '}';
    }
}
