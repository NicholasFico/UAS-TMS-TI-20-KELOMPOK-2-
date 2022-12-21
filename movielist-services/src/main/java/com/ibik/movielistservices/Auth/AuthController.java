package com.ibik.movielistservices.Auth;

import java.util.ArrayList;
import java.util.List;
    
import javax.validation.Valid;
    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.movielistservices.dto.AuthentificationKey;
import com.ibik.movielistservices.dto.ResponseData;
    
    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {
        
        @Autowired
        private AuthServices authServices;
    
        @PostMapping
        public ResponseEntity<ResponseData<Auth>> postAuth(@Valid @RequestBody Auth auth, Errors errors) {
            
            ResponseData<Auth> responseData = new ResponseData<>();
            
            if (errors.hasErrors()) {
                for(ObjectError error : errors.getAllErrors()){
                    responseData.getMessage().add(error.getDefaultMessage());
                }
                
                responseData.setResult(false);
                responseData.setData(null);
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            
            responseData.setResult(true);
            List<Auth> value = new ArrayList<>();
            value.add(authServices.save(auth));
            responseData.setData(value);
            return ResponseEntity.ok(responseData);
        }
    
        @GetMapping
        public ResponseEntity<ResponseData<Auth>> fetchAuth() {
            ResponseData<Auth> responseData = new ResponseData<>();
            try {
                responseData.setResult(true);
                List<Auth> value = (List<Auth>) authServices.findAll();
                responseData.setData(value);
    
                return ResponseEntity.ok(responseData);
            } catch (Exception ex) {
                responseData.setResult(false);
                responseData.getMessage().add(ex.getMessage());
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<ResponseData<Auth>> fetchAuthById(@PathVariable("id") int id) {
            ResponseData<Auth> responseData = new ResponseData<>();
            try {
                responseData.setResult(true);
                List<Auth> value = new ArrayList<>();
                value.add(authServices.findOne(id));
                responseData.setData(value);
    
                return ResponseEntity.ok(responseData);
            } catch (Exception ex) {
                responseData.setResult(false);
                responseData.getMessage().add(ex.getMessage());
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        }
    
        @PutMapping
        public ResponseEntity<ResponseData<Auth>> updateAuth(@Valid @RequestBody Auth auth, Errors errors) {
    
            ResponseData<Auth> responseData = new ResponseData<>();
            if(auth.getId() != 0){
                if (errors.hasErrors()) {
                    for (ObjectError error : errors.getAllErrors()) {
                        responseData.getMessage().add(error.getDefaultMessage());
                    }
                    responseData.setResult(false);
                    responseData.setData(null);
    
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
                responseData.setResult(true);
                List<Auth> value = new ArrayList<>();
                value.add(authServices.save(auth));
                responseData.setData(value);
    
                return ResponseEntity.ok(responseData);
            } else {
                responseData.getMessage().add("ID is Required");
                responseData.setResult(false);
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<ResponseData<Void>> deleteAuthById(@PathVariable("id") int id) {
            ResponseData<Void> responseData = new ResponseData<>();
            try {
                authServices.removeOne(id);
                responseData.setResult(true);
                responseData.getMessage().add("Successfully Remove");
    
                return ResponseEntity.ok(responseData);
            } catch (Exception ex) {
                responseData.setResult(false);
                responseData.getMessage().add(ex.getMessage());
    
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        }
    
        @PostMapping("/auth")
        public ResponseEntity<ResponseData<Auth>> getAuth(@RequestBody AuthentificationKey authentificationKey) {
        ResponseData<Auth> responseData = new ResponseData<>();
    
        System.out.print(authentificationKey.getEmail());
        System.out.print(authentificationKey.getPassword());
    
        try{
          Iterable<Auth> values = authServices.findAuth(authentificationKey.getEmail(), authentificationKey.getPassword());
          responseData.setResult(true);
          responseData.getMessage();
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
