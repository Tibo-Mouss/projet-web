package com.pixels.beans;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pixels.utils.Hash;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@NamedQuery(name = "User.list", query = "SELECT u FROM User u")
public class User implements Serializable {

    // nom d'utilisateur
    @Id
    @NonNull
    @NotBlank
    private String username;

    // mot de passe hashé en sha256
    @NonNull
    @NotBlank
    private String password;

    // portefeuille
    @OneToMany(mappedBy = "owner")
    private List<Pixel> pixels;

    // le solde de l'utilisateur
    @NotNull
    @Min(value = 0)
    private Integer balance = 0;

    @Column(unique = true)
    private String sessionID;

    public User(String username, String password) {
        this.username = username;
        this.password = Hash.sha256(password);
    }

    public void setPassword(String password) {
        this.password = Hash.sha256(password);
    }

    public boolean validPassword(String password) {
        return this.password.equals(Hash.sha256(password));
    }

    public static User fromSessionID(String sessionID, EntityManager em) {
        return em.createQuery("SELECT u FROM User u WHERE sessionID = :session_id", User.class)
                .setParameter("session_id", sessionID)
                .getSingleResult();
    }

}
