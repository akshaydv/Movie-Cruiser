package com.stackroute.moviecruiser.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
     
    
    public Movie(Integer id, String name, String comments, String poster_path, Date release_date, double vote_average,
			Integer vote_count) {
		super();
		this.id = id;
		this.name = name;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
	}

	public Movie() {};

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Id
    @Column(name="id")
    private Integer id;
	
    @Column(name="name")
    private String name;
    
    @Column(name="comments")
    private String comments;
    
    @Column(name="poster_path")
    private String poster_path;
    
    @Column(name="release_date")
    private Date release_date;
    
    @Column(name="vote_average")
    private Double vote_average;
    
    @Column(name="vote_count")
    private Integer vote_count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public Integer getVote_count() {
		return vote_count;
	}

	public void setVote_count(Integer vote_count) {
		this.vote_count = vote_count;
	}
 
    
    
}

