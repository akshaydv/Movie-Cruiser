package com.stackroute.moviecruiser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiser.repositories.MovieRepository;
import com.stackroute.moviecruiser.services.MovieService;

@Controller    
@RequestMapping(path="/v1.0/movieservice/movie") 

@CrossOrigin(origins = "*")
public class MovieController {
	@Autowired
	private MovieService movieService;

	//<-- Getter Methods -->
	@GetMapping(path="/{id:[0-9]+}") 
	public @ResponseBody ResponseEntity<?> fetchMovieByID (@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Movie>(movieService.getMovieById(id), HttpStatus.OK);
		}
		catch(Exception e) {  
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	} 
	 
	//<-- Save Methods -->
	@PostMapping
	public @ResponseBody ResponseEntity<String> saveNewMovie (@RequestBody Movie movie){ 
		return new ResponseEntity<String>(movieService.saveMovie(movie), HttpStatus.OK);
	}
	
	//<-- Update Methods -->
	@PutMapping(path="/{id:[0-9]+}") 
	public @ResponseBody ResponseEntity<String> updateMovie (@PathVariable("id") Integer id, @RequestBody String comments){ 
		System.out.println(comments);
		try {
			
			return new ResponseEntity<String>(movieService.updateMovieComments(id, comments), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	  
	//<-- Delete Methods -->
	@DeleteMapping
	public @ResponseBody ResponseEntity<String> deleteMovie (@RequestBody Movie movie){
		try {
			return new ResponseEntity<String>(movieService.deleteMovie(movie), HttpStatus.OK);
		}
		catch(Exception e) {  
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}  
 
	@DeleteMapping(value = "/{id:[0-9]+}") 
	public @ResponseBody ResponseEntity<String> deleteMovieById (@PathVariable("id") int id){ 
		try {
			return new ResponseEntity<String>(movieService.deleteMovieById(id), HttpStatus.OK);
		}
		catch(Exception e) {  
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	  
	//<-- GetAll Method -->
	@GetMapping
	public @ResponseBody Iterable<Movie> getAllUsers() { 
		return movieService.getAllMovies();
	}
}
