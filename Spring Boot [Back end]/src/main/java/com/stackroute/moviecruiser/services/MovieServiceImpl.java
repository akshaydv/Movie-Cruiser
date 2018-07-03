package com.stackroute.moviecruiser.services;
 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
 
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiser.repositories.MovieRepository;

@Service 
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	public MovieRepository getMovieRepository() {
		return movieRepository;
	}

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub 
		if(!movieRepository.exists(id)) throw new MovieNotFoundException("Movie not found!");
		return movieRepository.findOne(id); 
	}

	 
	
	
	@Override
	public String deleteMovie(Movie movie)  throws MovieNotFoundException{
		// TODO Auto-generated method stub
		if(!movieRepository.exists(movie.getId())) throw new MovieNotFoundException("Couldn't delete. Movie not found!");
		movieRepository.delete(movie);
		return "Movie deleted successfully";
	}

	@Override
	public String deleteMovieById(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub 
		if(!movieRepository.exists(id)) throw new MovieNotFoundException("Movie not found!");
		movieRepository.delete(id);
		return "Movie deleted successfully";
	}

	 

	@Override
	public Iterable<Movie> getAllMovies() {
		// TODO Auto-generated method stub 
		return movieRepository.findAll();
	}

	@Override
	public String saveMovie(Movie movie) {
		// TODO Auto-generated method stub
		movieRepository.save(movie);
		return "Movie saved successfully";
	}

	public Movie save(Movie movie) {
		// TODO Auto-generated method stub
		movieRepository.save(movie);
		return movie;
	}

	@Override
	public String updateMovieComments(Integer id, String comments) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		if(!movieRepository.exists(id)) throw new MovieNotFoundException("Couldn't update movie. Movie not found!");
		Movie movie = getMovieById(id);
		movie.setComments(comments);
		movieRepository.save(movie);
		return "Movie updated successfully";
	}
 
	
}
