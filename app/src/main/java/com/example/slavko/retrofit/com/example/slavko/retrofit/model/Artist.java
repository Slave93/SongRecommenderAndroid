package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

import java.util.List;

public class Artist {
	
	protected List<String> genres;	
	protected String id;	
	protected String name;	
	protected Integer popularity;

	public List<String> getGenres() {
		return genres;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "Artist [genres=" + genres + ", id=" + id + ", name=" + name
				+ ", popularity=" + popularity + "]";
	}

	
	

}
