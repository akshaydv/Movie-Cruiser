package com.stackroute.moviecruiser.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.moviecruiser.domain.Movie; 
 
public interface MovieRepository extends CrudRepository<Movie, Integer> {
	Movie findByName(String name);
	
//	@Query("UPDATE Movie movie SET comments=:comments WHERE id=:id")
//    public Movie updateMovieComments (@Param("id") Integer id, @Param("comments")String comments);
}
