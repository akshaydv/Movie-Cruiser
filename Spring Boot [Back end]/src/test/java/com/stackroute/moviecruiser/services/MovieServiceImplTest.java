package com.stackroute.moviecruiser.services;

import static org.junit.Assert.*;

import org.junit.Test; 
import org.junit.Before;  
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.repositories.MovieRepository;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieServiceImplTest {

    private MovieServiceImpl movieServiceImpl;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private Movie movie;
    
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        movieServiceImpl=new MovieServiceImpl();
        movieServiceImpl.setMovieRepository(movieRepository); 
    }
    
    @Test
    public void shouldReturnMovie_whenGetMovieByIdIsCalled() throws Exception {
        // Arrange
        when(movieRepository.findOne(5)).thenReturn(movie);
        // Act
        Movie retrievedMovie = movieServiceImpl.getMovieById(5);
        // Assert
        assertThat(retrievedMovie, is(equalTo(movie)));

   }
    
    @Test
    public void shouldReturnProduct_whenSaveProductIsCalled() throws Exception {
        // Arrange
        when(movieRepository.save(movie)).thenReturn(movie);
        // Act
        Movie savedMovie = movieServiceImpl.save(movie);
        // Assert
        assertThat(savedMovie, is(equalTo(movie)));
    }
    
    @Test
    public void shouldCallDeleteMethodOfProductRepository_whenDeleteProductIsCalled() throws Exception {
        // Arrange
        doNothing().when(movieRepository).delete(5);
        MovieRepository my = Mockito.mock(MovieRepository.class);
        // Act
        movieServiceImpl.deleteMovieById(5);
        // Assert
        verify(movieRepository, times(1)).delete(5);
    }
}