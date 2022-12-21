package com.ibik.movielistservices.Auth;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthRepo extends CrudRepository<Auth, Integer>{

    @Query(value = "SELECT a.* FROM auth a WHERE a.email = :email AND a.password = :password", nativeQuery = true)
    public Iterable<Auth> findAuth(@Param("email") String email, @Param("password") String password);
}
