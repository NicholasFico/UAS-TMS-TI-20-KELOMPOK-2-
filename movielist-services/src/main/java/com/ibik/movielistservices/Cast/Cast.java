package com.ibik.movielistservices.Cast;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;

import com.ibik.movielistservices.Chars.Chars;
import com.ibik.movielistservices.Movie.Movie;

@Entity
@Table(name = "Cast")
public class Cast implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    @NotEmpty(message = "Name is required")
    private String Name;

    @NotEmpty(message = "Photo is required")
    private String Photo;

    @ManyToMany
    @JoinTable(
        name = "Credit",
        joinColumns = @JoinColumn(name = "cast_id"),
        inverseJoinColumns = @JoinColumn(name="movie_id")
    )
    private Set<Movie> Movie;

    @OneToMany
    @JoinColumn(name = "chars_id")
    private Set<Chars> chars;

    public Cast() {
    }

    public Cast(int id, String Name, String Photo,  Set<Movie> Movie, Set<Chars> chars) { 
        this.id = id;
        this.Name = Name;
        this.Photo = Photo;
        this.chars = chars;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public Set<Chars> getChars() {
        return chars;
    }

    public void setChars(Set<Chars> chars) {
        this.chars = chars;
    }

    

}