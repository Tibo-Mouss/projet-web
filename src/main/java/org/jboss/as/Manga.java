package org.jboss.as;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.*;

import javax.persistence.*;

@Entity
public class Manga {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private URL cover;

    @ElementCollection
    private List<URL> chapters = new ArrayList<URL>();

    private String author;
    private String description;

    @ElementCollection
    private List<Genre> genres = new ArrayList<Genre>();
    
    private int publication;
    private float grade;

    @OneToMany
    private List<Review> reviews;

    public Manga(){}

    public int getId(){
        return this.id;
    }

    public URL getCover(){
        return this.cover;
    }
    
    public List<URL> getChapters(){
        return this.chapters;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getDescription(){
        return this.description;
    }

    public List<Genre> getGenres(){
        return this.genres;
    }

    public int getPublication(){
        return this.publication;
    }

    public float getGrade(){
        return this.grade;
    }

    public List<Review> getReviews(){
        return this.reviews;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setChapters(List<URL> chapters) {
        this.chapters = chapters;
    }

    public void setCover(URL cover) {
        this.cover = cover;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    

    
    
}
