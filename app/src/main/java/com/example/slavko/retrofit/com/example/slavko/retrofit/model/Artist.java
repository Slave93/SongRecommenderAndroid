package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

import java.util.List;

public class Artist {
	
	protected List<String> genres;	
	protected String remoteId;
	protected String name;	
	protected Integer popularity;

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getRemoteId() {
		return remoteId;
	}

	public void setRemoteId(String remoteId) {
		this.remoteId = remoteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	@Override
	public String toString() {
		return "Artist [genres=" + genres + ", id=" + remoteId + ", name=" + name
				+ ", popularity=" + popularity + "]";
	}

	
	

}
