package com.stories.model;

public class StoryComments {

	
	String by;
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "StoryComments [by=" + by + ", text=" + text + "]";
	}
}
