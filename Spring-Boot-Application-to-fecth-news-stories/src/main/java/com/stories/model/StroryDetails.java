package com.stories.model;

import java.util.List;

public class StroryDetails {

	String by;
	String descendants;
	Double id;
	List<Long> kids;
	Double score;
	long time;
	String title;
	String story;
	String url;
	
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getDescendants() {
		return descendants;
	}
	public void setDescendants(String descendants) {
		this.descendants = descendants;
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public List<Long> getKids() {
		return kids;
	}
	public void setKids(List<Long> kids) {
		this.kids = kids;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "StroryDetails [by=" + by + ", descendants=" + descendants + ", id=" + id + ", kids=" + kids + ", score="
				+ score + ", time=" + time + ", title=" + title + ", story=" + story + ", url=" + url + "]";
	}
	
	
}
