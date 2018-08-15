package com.flipkart.platform.review.beans;

import java.util.List;

public class RatingMap {

	int year;
	List<Movie> movies;
	
	public RatingMap(int year, List<Movie> movies) {
		super();
		this.year = year;
		this.movies = movies;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Movie> getMovie() {
		return movies;
	}

	public void setMovie(List<Movie> movie) {
		this.movies = movie;
	}

	@Override
	public String toString() {
		return "RatingMap [year=" + year + ", movies=" + movies + "]";
	}
}
