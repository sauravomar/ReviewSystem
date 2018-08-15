package com.flipkart.platform.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.activity.InvalidActivityException;

import com.flipkart.platform.review.beans.Movie;
import com.flipkart.platform.review.beans.RatingMap;
import com.flipkart.platform.review.beans.User;

public class ReviewPlatform {

	Map<Integer, RatingMap> yearVsMovies = new HashMap<Integer, RatingMap>();

	public boolean isUserRated(Movie movie, User user) {
		if (movie.getReview().containsKey(user)) {
			return true;
		} else {
			return false;
		}
	}

	public void setUserReviewCount(User user) {
		user.setReviewCount(user.getReviewCount() + 1);
		if (user.getReviewCount() >= 3) {
			user.setCritic(true);
		}
	}

	public void addReview(Movie movie, User user, int rating) throws InvalidActivityException {

		if (yearVsMovies.containsKey(movie.getYear())) {

			RatingMap ratingMap = yearVsMovies.get(movie.getYear());
			Movie movieObj = ratingMap.getMovie().stream().filter(mov -> mov.equals(movie)).findFirst().orElse(null);

			if (movieObj == null) {
				movieObj = movie;
				ratingMap.getMovie().add(movieObj);
			}

			// check User already givenRating
			if (isUserRated(movieObj, user)) {
				throw new InvalidActivityException("User Already Rated");
			} else {
				Map<User, Integer> reviewMap = movieObj.getReview();
				reviewMap.put(user, rating);
				int totalPersonReview = movieObj.getTotalNoPersonReview() + 1;
				// if user is critic
				if (user.isCritic()) {
					rating = 2 * rating;
					totalPersonReview = totalPersonReview + 1;
				}

				movieObj.setAvgRating(
						((movieObj.getAvgRating() * movieObj.getTotalNoPersonReview()) + rating) / totalPersonReview);

				movie.setTotalNoPersonReview(totalPersonReview);
				movieObj.setTotalRating(movieObj.getTotalRating() + rating);

				movieObj.setReview(reviewMap);
			}

		} else {
			List<Movie> movieList = new ArrayList<Movie>();
			movie.setAvgRating(rating);
			movie.setTotalRating(rating);
			movie.setTotalNoPersonReview(1);
			Map<User, Integer> reviewMap = new HashMap<User, Integer>();
			reviewMap.put(user, rating);
			movie.setReview(reviewMap);
			movieList.add(movie);
			RatingMap ratingMap = new RatingMap(movie.getYear(), movieList);
			yearVsMovies.put(movie.getYear(), ratingMap);
		}

		// update user rating
		setUserReviewCount(user);

	}

	public Movie getTopMovie(int year) {

		List<Movie> movies = yearVsMovies.get(year).getMovie();
		Movie movie = movies.get(0);

		for (Movie mov : movies) {
			if (mov.getTotalRating() > movie.getTotalRating()) {
				movie = mov;
			}
		}

		return movie;
	}

	public float getTopMovieAvgReview(int year) {

		float rating = 0;
		float totalPerson = 0;
		List<Movie> movies = yearVsMovies.get(year).getMovie();

		for (Movie mov : movies) {
			rating = rating + mov.getTotalRating();
			totalPerson = totalPerson + mov.getTotalNoPersonReview();
		}

		return (rating / totalPerson);
	}

	public Movie getTopMovieByGener(String gener) {

		Movie movie = null;

		for (Entry<Integer, RatingMap> entry : yearVsMovies.entrySet()) {
			List<Movie> movies = entry.getValue().getMovie();
			for (Movie mov : movies) {
				if (mov.getGenres().equalsIgnoreCase(gener)) {
					if (movie == null) {
						movie = mov;
					} else if (mov.getTotalRating() > movie.getTotalRating()) {
						movie = mov;
					}
				}
			}
		}

		return movie;
	}

	public static void main(String[] args) {

		ReviewPlatform platform = new ReviewPlatform();

		// create Movies;
		Movie don = new Movie("Don", "Action & Comedy", 2006);
		Movie tiger = new Movie("Tiger", "Drama", 2008);
		Movie padmaavt = new Movie("Padmavaat", "Comedy", 2006);
		Movie lunchBox = new Movie("LunchBox", "Drama", 2019);
		Movie guru = new Movie("Guru", "Drama", 2006);
		Movie metro = new Movie("Metro", "Romance", 2006);

		// create users;
		User srk = new User("SRK");
		User salman = new User("Salman");
		User deepika = new User("Deepika");

		try {
			platform.addReview(don, srk, 2);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(padmaavt, srk, 8);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(don, salman, 5);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(don, deepika, 9);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(guru, deepika, 6);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(don, srk, 10);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(lunchBox, deepika, 5);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		try {
			platform.addReview(tiger, srk, 5);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}
		try {
			platform.addReview(metro, srk, 7);
		} catch (InvalidActivityException e) {
			e.printStackTrace();
		}

		// test cases;

		Movie movie = platform.getTopMovie(2006);
		System.out.println(movie.getName() + " " + movie.getTotalRating());

		movie = platform.getTopMovieByGener("Drama");
		System.out.println(movie.getName() + " " + movie.getTotalRating());

		System.out.println(platform.getTopMovieAvgReview(2006));

	}

}
