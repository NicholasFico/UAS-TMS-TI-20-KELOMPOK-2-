package com.ibik.movielistservices.Movie;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import com.ibik.movielistservices.Genres.Genres;
import com.ibik.movielistservices.Cast.Cast;

@Entity
@Table(name = "Movie")
public class Movie implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Poster is required")
    private String Poster;

    @Column(length = 20)
    @NotEmpty(message = "Title is required")
    private String Title;

    @NotEmpty(message = "Synopsis is required")
    private String Synopsis;

    @Column(length = 10)
    @NotEmpty(message = "Rating is required")
    private String Rating;


    @NotEmpty(message = "Video is required")
    private String Video;

    @ManyToMany(mappedBy = "Movie")
    private Set<Genres> Genres;

    @ManyToMany(mappedBy = "Movie")
    private Set<Cast> Cast;

    public Movie() {
    }

    public Movie(int id, String Poster, String Title, String Synopsis, String Rating, String Video) { 
        this.id = id;
        this.Poster = Poster;
        this.Title = Title;
        this.Synopsis = Synopsis;
        this.Rating = Rating;
        this.Video = Video;
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

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSynopsis() {
        return Synopsis;
    }

    public void setSynopsis(String synopsis) {
        Synopsis = synopsis;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public Set<Genres> getGenres() {
        return Genres;
    }

    public void setGenres(Set<Genres> genres) {
        Genres = genres;
    }

    public Set<Cast> getCast() {
        return Cast;
    }

    public void setCast(Set<Cast> cast) {
        Cast = cast;
    }
    
    
}