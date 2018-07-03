package com.stackroute.moviecruiser.services;

import static org.junit.Assert.*; 
import org.junit.Test;
import org.junit.runner.RunWith; 
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.repositories.MovieRepository;

import org.mockito.Mock;
import org.mockito.Mockito; 
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplSpyTest {
	
    @Spy
    private MovieServiceImpl movieServiceSpy;
    
    @Mock
    private MovieRepository movieRepository;
    
    @Mock
    private Movie movie;

   @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetProductByIdIsCalledWithoutContext() throws Exception {
        //Act
	   Movie retrievedMovie = movieServiceSpy.getMovieById(5);
        //Assert
        assertThat(retrievedMovie, is(equalTo(movie)));
    }
   
   @Test(expected=NullPointerException.class)
   public void shouldThrowNullPointerException_whenSaveProductIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(movie).when(movieRepository).save(movie);
        //Act
        Movie savedProduct = movieServiceSpy.save(movie);
        //Assert
        assertThat(savedProduct, is(equalTo(movie)));
    }

   @Test
    public void shouldVerifyThatGetProductByIdIsCalled() throws Exception {
        //Arrange
        Mockito.doReturn(movie).when(movieServiceSpy).getMovieById(5);
        //Act
        Movie retrievedProduct = movieServiceSpy.getMovieById(5);
        //Assert
        Mockito.verify(movieServiceSpy).getMovieById(5);
    }
    @Test
    public void shouldVerifyThatSaveProductIsNotCalled() throws Exception {
        //Arrange
        Mockito.doReturn(movie).when(movieServiceSpy).getMovieById(5);
        //Act
        Movie retrievedProduct = movieServiceSpy.getMovieById(5);
        //Assert
        Mockito.verify(movieServiceSpy,never()).save(movie);
    }
}