package com.example.slavko.retrofit.com.example.slavko.retrofit.model;

import java.util.ArrayList;
import java.util.List;



public class Track {
	
	// TRACK-INFO
	private String name;
	private String album;
	private Integer cluster;
	private Integer duration;
	private Features features;
	private Long id;
	private String previewUrl;
	private String remoteId;
	private List<Artist> artists;

	public Track() {

	}

	public String getRemoteId() {
		return remoteId;
	}

	public void setRemoteId(String remoteId) {
		this.remoteId = remoteId;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Integer getCluster() {
		return cluster;
	}

	public void setCluster(Integer cluster) {
		this.cluster = cluster;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	@Override
	public String toString() {
		return "Track{" +
				"album='" + album + '\'' +
				", name='" + name + '\'' +
				", cluster=" + cluster +
				", duration=" + duration +
				", features=" + features +
				", id=" + id +
				", previewUrl='" + previewUrl + '\'' +
				", remoteId='" + remoteId + '\'' +
				", artists=" + artists +
				'}';
	}
}
