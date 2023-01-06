package com.ibik.movielistservices.Genres;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;

import com.ibik.movielistservices.Movie.Movie;

@Entity
@Table(name="Genres")
public class Genres implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean is_active;

    @ManyToMany
    @JoinTable(
        name = "movie_rel_genres",
        joinColumns = @JoinColumn(name = "genres_id"),
        inverseJoinColumns = @JoinColumn(name="movie_id")
    )
    private Set<Movie> Movie;

    public Genres() {
    }

    public Genres(int id, @NotEmpty(message = "Name is required") String name,
            boolean is_active, Set<Movie> Movie) {
        this.id = id;
        this.name = name;
        this.is_active = is_active;
        this.Movie = Movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
}