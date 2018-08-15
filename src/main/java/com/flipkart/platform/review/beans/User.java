package com.flipkart.platform.review.beans;

public class User {
	String name;
	boolean isCritic;
	int reviewCount;
	
	public User(String name) {
		super();
		this.name = name;
		this.isCritic = false;
		this.reviewCount = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCritic() {
		return isCritic;
	}

	public void setCritic(boolean isCritic) {
		this.isCritic = isCritic;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", isCritic=" + isCritic + ", reviewCount=" + reviewCount + "]";
	}

}
