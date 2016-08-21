package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

/**
 * Created by igor on 5/15/16.
 */
public class ClusteringResponse {

    protected Boolean success;
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
