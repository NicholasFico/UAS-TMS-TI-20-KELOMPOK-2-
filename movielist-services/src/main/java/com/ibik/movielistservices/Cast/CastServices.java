package com.ibik.movielistservices.Cast;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CastServices {

    @Autowired
    private CastRepo castRepo;

    public Cast save(Cast cast){
        return castRepo.save(cast);
    }

    public Cast findOne(int id){
        return castRepo.findById(id).get();
    }

    public Iterable<Cast> findAll(){
        return castRepo.findAll();
    }

    public void removeOne(int id){
        castRepo.deleteById(id);
    }

}

