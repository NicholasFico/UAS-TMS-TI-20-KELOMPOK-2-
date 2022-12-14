package com.ibik.movielistservices.Movie;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepo extends CrudRepository<Movie, Integer>{
    @Query("SELECT a FROM Movie a WHERE a.Title LIKE %:Title% ")
  public Iterable<Movie> findMovieByName(@Param("Title") String Title);
}

