package com.ibik.movielistservices.Genres;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenresRepo extends CrudRepository<Genres, Integer>{
    @Query("SELECT a FROM Genres a WHERE a.name LIKE %:name% ")
    public Iterable<Genres> findGenresByName(@Param("name") String name);
}
