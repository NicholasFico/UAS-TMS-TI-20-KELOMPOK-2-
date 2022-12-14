package com.ibik.movielistservices.Movie;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovieServices {
  
  @Autowired
  private MovieRepo movieRepo;

  public Movie save(Movie movie){
    return movieRepo.save(movie);
  }

  public Movie findOne(int id){
    return movieRepo.findById(id).get();
  }

  public Iterable<Movie> findAll(){
    return movieRepo.findAll();
  }

  public void removeOne(int id){
    movieRepo.deleteById(id);
  }

  public Iterable<Movie> findByName(String name){
    return movieRepo.findMovieByName(name);
  }

}
