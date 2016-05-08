package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

/**
 * Created by igor on 5/8/16.
 */
public class Recommendation {

    protected Boolean success;
    protected String message;
    protected Track request_song;
    protected Track suggested_song;


    public Recommendation() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Track getRequest_song() {
        return request_song;
    }

    public void setRequest_song(Track request_song) {
        this.request_song = request_song;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Track getSuggested_song() {
        return suggested_song;
    }

    public void setSuggested_song(Track suggested_song) {
        this.suggested_song = suggested_song;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", request_song=" + request_song +
                ", suggested_song=" + suggested_song +
                '}';
    }
}
