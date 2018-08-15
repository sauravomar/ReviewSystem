package com.flipkart.platform.review.beans;

import java.util.HashMap;
import java.util.Map;

public class Movie {

	private String name;
	private String genres;
	private int year;
	private Map<User,Integer> review;
	int totalRating;
	float avgRating;
	int totalNoPersonReview;

	public Movie(String name, String genres, int year) {
		super();
		this.name = name;
		this.genres = genres;
		this.year = year;
		this.review = new HashMap<User,Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Map<User,Integer> getReview() {
		return review;
	}

	public void setReview(Map<User,Integer> review) {
		this.review = review;
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public int getTotalNoPersonReview() {
		return totalNoPersonReview;
	}

	public void setTotalNoPersonReview(int totalNoPersonReview) {
		this.totalNoPersonReview = totalNoPersonReview;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", genres=" + genres + ", year=" + year + ", review=" + review + ", totalRating="
				+ totalRating + ", avgRating=" + avgRating + ", totalNoPersonReview=" + totalNoPersonReview + "]";
	}
}
