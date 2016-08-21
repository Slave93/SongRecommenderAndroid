package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

/**
 * Created by igor on 7/3/16.
 */
public class FeatureWeights {

    protected Integer year;
    protected Integer danceability;
    protected Integer energy;
    protected Integer track_key;
    protected Integer loudness;
    protected Integer mode;
    protected Integer speechiness;
    protected Integer acousticness;
    protected Integer instrumentalness;
    protected Integer liveness;
    protected Integer valence;
    protected Integer tempo;
    protected Integer time_signature;
    protected Integer popularity;

    public Integer getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(Integer acousticness) {
        this.acousticness = acousticness;
    }

    public Integer getDanceability() {
        return danceability;
    }

    public void setDanceability(Integer danceability) {
        this.danceability = danceability;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(Integer instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public Integer getLiveness() {
        return liveness;
    }

    public void setLiveness(Integer liveness) {
        this.liveness = liveness;
    }

    public Integer getLoudness() {
        return loudness;
    }

    public void setLoudness(Integer loudness) {
        this.loudness = loudness;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(Integer speechiness) {
        this.speechiness = speechiness;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Integer getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(Integer time_signature) {
        this.time_signature = time_signature;
    }

    public Integer getTrack_key() {
        return track_key;
    }

    public void setTrack_key(Integer track_key) {
        this.track_key = track_key;
    }

    public Integer getValence() {
        return valence;
    }

    public void setValence(Integer valence) {
        this.valence = valence;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "FeatureWeights{" +
                "acousticness=" + acousticness +
                ", year=" + year +
                ", danceability=" + danceability +
                ", energy=" + energy +
                ", track_key=" + track_key +
                ", loudness=" + loudness +
                ", mode=" + mode +
                ", speechiness=" + speechiness +
                ", instrumentalness=" + instrumentalness +
                ", liveness=" + liveness +
                ", valence=" + valence +
                ", tempo=" + tempo +
                ", time_signature=" + time_signature +
                ", popularity=" + popularity +
                '}';
    }
}
