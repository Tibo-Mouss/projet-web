package org.jboss.as;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User {
    
    @Id
    private String username;
    private String password;
    @ManyToMany
    private List<Manga> favorites = new ArrayList<>();
    
    public User(){}

    public User(String usr, String pswd){
        this.username = usr;
        this.password = pswd;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public List<Manga> getFavorites(){
        return favorites;
    }

    public void setUsername(String usr){
        this.username = usr;
    }

    public void setPassword(String pswd){
        this.password = pswd;
    }



    

}
