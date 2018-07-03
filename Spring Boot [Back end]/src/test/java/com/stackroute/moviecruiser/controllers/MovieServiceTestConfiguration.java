package com.stackroute.moviecruiser.controllers;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.stackroute.moviecruiser.services.MovieService;

@Profile("test")
@Configuration
public class MovieServiceTestConfiguration {
    @Bean
    @Primary
    public MovieService movieService() {
        return Mockito.mock(MovieService.class);
    }
}