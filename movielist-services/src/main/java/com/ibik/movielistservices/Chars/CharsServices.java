package com.ibik.movielistservices.Chars;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CharsServices {

    @Autowired
    private CharsRepo charsRepo;

    public Chars save(Chars chars){
        return charsRepo.save(chars);
    }

    public Chars findOne(int id){
        return charsRepo.findById(id).get();
    }

    public Iterable<Chars> findAll(){
        return charsRepo.findAll();
    }

    public void removeOne(int id){
        charsRepo.deleteById(id);
    }

}

