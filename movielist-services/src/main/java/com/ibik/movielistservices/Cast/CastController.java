package com.ibik.movielistservices.Cast;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.movielistservices.dto.ResponseData;

@RestController
@RequestMapping("/api/cast")
@CrossOrigin(origins = "http://localhost:3000")
public class CastController {

    @Autowired
    private CastServices castServices;

    @PostMapping
    public ResponseEntity<ResponseData<Cast>> postCast(@Valid @RequestBody Cast cast, Errors errors) {
        ResponseData<Cast> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setResult(true);
        List<Cast> value = new ArrayList<>();
        value.add(castServices.save(cast));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Cast>> fetchCast() {

        ResponseData<Cast> responseData = new ResponseData<>();

        try {
            Iterable<Cast> values = castServices.findAll();
            responseData.setResult(true);
            responseData.setMessage(null);
            responseData.setData(values);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            List<String> message = new ArrayList<>();
            message.add(e.getMessage());
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

    }

    @GetMapping("/{id}")
  // public Students fetchProgramsById(@PathVariable("id") int id) {
  public ResponseEntity<ResponseData<Cast>> postPrograms(@Valid @PathVariable("id") int id, Cast cast,
      Errors errors) {
    ResponseData<Cast> responseData = new ResponseData<>();

    try {
      responseData.setResult(true);
      List<Cast> value = new ArrayList<>();
      value.add(castServices.findOne(id));

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
    public ResponseEntity<ResponseData<Cast>> updateCast(@Valid @RequestBody Cast cast, Errors errors) {
        ResponseData<Cast> responseData = new ResponseData<>();

        if (cast.getId() != 0) {

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }

                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            responseData.setResult(true);
            List<Cast> value = new ArrayList<>();
            value.add(castServices.save(cast));
            responseData.setData(value);
            return ResponseEntity.ok(responseData);
        } else {
            responseData.setResult(false);
            responseData.setData(null);
            List<Cast> value = new ArrayList<>();
            value.add(castServices.save(cast));
            List<String> message = new ArrayList<>();
            message.add("ID is required");
            responseData.setMessage(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

}


