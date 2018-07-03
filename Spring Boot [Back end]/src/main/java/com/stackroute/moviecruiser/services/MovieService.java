package com.stackroute.moviecruiser.services; 
 
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;


public interface MovieService {
	public Movie getMovieById(int id)  throws MovieNotFoundException; 
	public String deleteMovie(Movie movie)  throws MovieNotFoundException;
	public String deleteMovieById(int id)  throws MovieNotFoundException; 
	public Iterable<Movie> getAllMovies();
	public String saveMovie(Movie movie);
	public String updateMovieComments(Integer id, String comments) throws MovieNotFoundException;
}
