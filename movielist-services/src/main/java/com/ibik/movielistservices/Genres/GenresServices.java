package com.ibik.movielistservices.Genres;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GenresServices {

    @Autowired
    private GenresRepo genresRepo;

    public Genres save(Genres genres){
        return genresRepo.save(genres);
    }

    public Genres findOne(int id){
        return genresRepo.findById(id).get();
    }

    public Iterable<Genres> findAll(){
        return genresRepo.findAll();
    }

    public void removeOne(int id){
        genresRepo.deleteById(id);
    }

}
