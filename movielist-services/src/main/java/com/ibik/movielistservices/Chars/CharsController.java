package com.ibik.movielistservices.Chars;

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
@RequestMapping("/api/chars")
@CrossOrigin(origins = "http://localhost:3000")
public class CharsController {

    @Autowired
    private CharsServices charsServices;

    @PostMapping
    public ResponseEntity<ResponseData<Chars>> postChars(@Valid @RequestBody Chars chars, Errors errors) {
        ResponseData<Chars> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setResult(true);
        List<Chars> value = new ArrayList<>();
        value.add(charsServices.save(chars));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Chars>> fetchChars() {

        ResponseData<Chars> responseData = new ResponseData<>();

        try {
            Iterable<Chars> values = charsServices.findAll();
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
    public ResponseEntity<ResponseData<Chars>> fetchCharsById(@PathVariable("id") int id) {
        ResponseData<Chars> responseData = new ResponseData<>();
        try {
            Chars value = charsServices.findOne(id);
            List<Chars> result = new ArrayList<>();
            result.add(value);
            responseData.setData(result);
            responseData.setResult(true);
            responseData.setMessage(null);
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

    @PutMapping
    public ResponseEntity<ResponseData<Chars>> updateGenres(@Valid @RequestBody Chars chars, Errors errors) {
        ResponseData<Chars> responseData = new ResponseData<>();

        if (chars.getId() != 0) {

            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }

                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }

            responseData.setResult(true);
            List<Chars> value = new ArrayList<>();
            value.add(charsServices.save(chars));
            responseData.setData(value);
            return ResponseEntity.ok(responseData);
        } else {
            responseData.setResult(false);
            responseData.setData(null);
            List<Chars> value = new ArrayList<>();
            value.add(charsServices.save(chars));
            List<String> message = new ArrayList<>();
            message.add("ID is required");
            responseData.setMessage(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

}

