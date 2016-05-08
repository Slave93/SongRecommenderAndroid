package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

/**
 * Created by igor on 5/8/16.
 */
public class Features {


    protected Float danceability;
    protected Float energy;
    protected Integer track_key;
    protected Float loudness;
    protected Integer mode;
    protected Float speechiness;
    protected Float acousticness;
    protected Float instrumentalness;
    protected Float liveness;
    protected Float valence;
    protected Float tempo;
    protected Integer time_signature;
    protected Float popularity;

    public Features() {
    }

    public Float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(Float acousticness) {
        this.acousticness = acousticness;
    }

    public Float getDanceability() {
        return danceability;
    }

    public void setDanceability(Float danceability) {
        this.danceability = danceability;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(Float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public Float getLiveness() {
        return liveness;
    }

    public void setLiveness(Float liveness) {
        this.liveness = liveness;
    }

    public Float getLoudness() {
        return loudness;
    }

    public void setLoudness(Float loudness) {
        this.loudness = loudness;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(Float speechiness) {
        this.speechiness = speechiness;
    }

    public Float getTempo() {
        return tempo;
    }

    public void setTempo(Float tempo) {
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

    public Float getValence() {
        return valence;
    }

    public void setValence(Float valence) {
        this.valence = valence;
    }

    @Override
    public String toString() {
        return "Features{" +
                "acousticness=" + acousticness +
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
