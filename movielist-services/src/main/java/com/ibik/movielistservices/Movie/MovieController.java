package com.ibik.movielistservices.Movie;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.movielistservices.dto.ResponseData;
import com.ibik.movielistservices.dto.SearchData;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

  // @GetMapping
  // public String helloWorld(){
  // return "<h1>Hello World</h1>";
  // }

  @Autowired
  private MovieServices movieServices;

  @PostMapping
  // public Students postPrograms(@Valid @RequestBody Students programs, Errors
  // errors){
  public ResponseEntity<ResponseData<Movie>> postPrograms(@Valid @RequestBody Movie movie, Errors errors) {
    ResponseData<Movie> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessage().add(error.getDefaultMessage());
        // System.out.println(error.getDefaultMessage());
      }
      responseData.setResult(false);
      responseData.setData(null);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
      // throw new RuntimeException("Validation Error");
    }

    responseData.setResult(true);
    List<Movie> value = new ArrayList<>();
    value.add(movieServices.save(movie));
    responseData.setData(value);
    return ResponseEntity.ok(responseData);

    // return studentsServices.save(programs);
  }

  @GetMapping
  public ResponseEntity<ResponseData<Movie>> fetchPrograms() {
    ResponseData<Movie> responseData = new ResponseData<>();

    try {
      responseData.setResult(true);
      responseData.setMessage(null);
      List<Movie> value = (List<Movie>) movieServices.findAll();
      responseData.setData(value);

      return ResponseEntity.ok(responseData);

    } catch (Exception e) {
      responseData.setResult(false);
      responseData.getMessage().add(e.getMessage());

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

    }
    // return studentsServices.findAll();
  }

  @GetMapping("/{id}")
  // public Students fetchProgramsById(@PathVariable("id") int id) {
  public ResponseEntity<ResponseData<Movie>> postPrograms(@Valid @PathVariable("id") int id, Movie movie,
      Errors errors) {
    ResponseData<Movie> responseData = new ResponseData<>();

    try {
      responseData.setResult(true);
      List<Movie> value = new ArrayList<>();
      value.add(movieServices.findOne(id));

      responseData.setData(value);

      return ResponseEntity.ok(responseData);
    } catch (Exception e) {
      responseData.setResult(false);
      responseData.getMessage().add(e.getMessage());

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

    }
    // return studentsServices.findOne(id);
  }

  @PutMapping
  public ResponseEntity<ResponseData<Movie>> updateMovie(@Valid @RequestBody Movie movie, Errors errors) {
    ResponseData<Movie> responseData = new ResponseData<>();

    if (movie.getId() != 0) {

      if (errors.hasErrors()) {
        for (ObjectError error : errors.getAllErrors()) {
          responseData.getMessage().add(error.getDefaultMessage());
        }
        responseData.setResult(false);
        responseData.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
      }

      responseData.setResult(true);
      List<Movie> value = new ArrayList<>();
      value.add(movieServices.save(movie));
      responseData.setData(value);
      return ResponseEntity.ok(responseData);

    } else {
      responseData.setResult(false);
      responseData.setData(null);
      List<String> message = new ArrayList<>();
      message.add("ID is required");
      responseData.setMessage(message);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
  }

  @DeleteMapping("/{id}")
  // public void deleteProgramsById(@PathVariable("id") int id) {
  public ResponseEntity<ResponseData<Movie>> deleteMovieById(@PathVariable("id") int id) {
    ResponseData<Movie> responseData = new ResponseData<>();

    if (id != 0) {
      try {
        movieServices.removeOne(id);
        responseData.setResult(true);
        responseData.getMessage().add("Successfully Removed");

        return ResponseEntity.ok(responseData);

      } catch (Exception e) {
        responseData.setResult(false);
        responseData.getMessage().add(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

      }

    } else {
      List<String> message = new ArrayList<>();
      message.add("ID is required");
      responseData.setMessage(message);
      responseData.setData(null);
      responseData.setResult(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    // studentsServices.removeOne(id);
  }

  @PostMapping("/search")
  public ResponseEntity<ResponseData<Movie>> getMovieByName(@RequestBody SearchData searchData) {
    ResponseData<Movie> responseData = new ResponseData<>();

    try{
      Iterable<Movie> values = movieServices.findByName(searchData.getSearchKey());
      responseData.setResult(true);
      responseData.setMessage(null);
      responseData.setData(values);
      return ResponseEntity.ok(responseData);

    } catch (Exception e ) {
      List<String> message = new ArrayList<>();
      message.add(e.getMessage());
      responseData.setMessage(message);
      responseData.setData(null);
      responseData.setResult(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
  }

}
