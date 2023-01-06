package com.ibik.movielistservices.Auth;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServices {
    
    @Autowired
    private AuthRepo authRepo;

    public Auth save(Auth auth){
        return authRepo.save(auth);
    }

    public Auth findOne(int id){
        return authRepo.findById(id).get();
    }    

    public Iterable<Auth> findAll(){
        return authRepo.findAll();
    }

    public void removeOne(int id){
        authRepo.deleteById(id);
    }

    public Auth findAuth(String email, String password){
        return authRepo.findAuth(email, password);
      }
}
