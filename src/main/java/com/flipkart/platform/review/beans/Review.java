package com.flipkart.platform.review.beans;

public class Review {

	User user;
	int rating;

	public Review(User user, int rating) {
		super();
		this.user = user;
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Review [user=" + user + ", rating=" + rating + "]";
	}
}
