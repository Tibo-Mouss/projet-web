package com.pixels.services;

import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixels.adapters.PixelAdapter;
import com.pixels.adapters.UserAdapter;
import com.pixels.beans.Pixel;
import com.pixels.beans.User;

@Singleton
@Path("/user")
public class UserService {

    @PersistenceContext
    private EntityManager em;

    private final Logger LOGGER = Logger.getLogger("UserService");

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Pixel.class, new PixelAdapter())
            .registerTypeAdapter(User.class, new UserAdapter())
            .create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String myself(@CookieParam("JSESSIONID") Cookie cookie) {
        // on trouve l'utilisateur connecté via son sessionID
        User user = User.fromSessionID(cookie.getValue(), em);

        // on renvoie les informations
        return gson.toJson(user);
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        TypedQuery<User> query = em.createNamedQuery("User.list", User.class);
        return gson.toJson(query.getResultList());
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String single(@PathParam("username") String username) {
        User user = em.find(User.class, username);
        if (user == null) {
            throw new NotFoundException();
        } else {
            LOGGER.info(gson.toJson(user.getPixels()));
            return gson.toJson(user);
        }
    }

    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void signup(@CookieParam("JSESSIONID") Cookie cookie, @FormParam("username") String username,
            @FormParam("password") String password) {

        // on créé un utilisateur
        User new_user = new User(username, password);

        // on associe aussi le sessionID de l'utilisateur
        new_user.setSessionID(cookie.getValue());

        // on commit les modifs dans la db
        em.persist(new_user);

        LOGGER.info("new user: " + new_user.getUsername());
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void login(@CookieParam("JSESSIONID") Cookie cookie, @FormParam("username") String username,
            @FormParam("password") String password) {

        User user;
        try {
            // si on trouve l'utilisateur via son sessionID
            user = User.fromSessionID(cookie.getValue(), em);
            LOGGER.info(user.getUsername() + " already logged in");

        } catch (NoResultException e) {
            // on trouve le user à partir de son username
            user = em.find(User.class, username);

            // si les identifiants sont valides
            if (user.validPassword(password)) {
                // on enregistre son sessionID
                user.setSessionID(cookie.getValue());

                // on commit les modifs dans la db
                em.merge(user);

                LOGGER.info(user.getUsername() + " logged in");
            } else {
                LOGGER.info("incorrect creds for " + user.getUsername());
                throw new ForbiddenException();
            }
        }
    }

    @POST
    @Path("logout")
    public void logout(@CookieParam("JSESSIONID") Cookie cookie) {
        try {
            // on trouve l'utilisateur connecté via son sessionID
            User user = User.fromSessionID(cookie.getValue(), em);

            // on supprime le sessionID
            user.setSessionID(null);

            // on commit les modifs dans la db
            em.merge(user);

            LOGGER.info(user.getUsername() + " logged out");
        } catch (NoResultException e) {
            LOGGER.info("user not logged in, cannot logout");
        }
    }

    @POST
    @Path("pay")
    public void pay(@CookieParam("JSESSIONID") Cookie cookie, @FormParam("amount") int amount) {
        if (amount > 0) {
            try {
                // on trouve l'utilisateur connecté via son sessionID
                User user = User.fromSessionID(cookie.getValue(), em);

                // on rajoute l'argent
                user.setBalance(user.getBalance() + amount);

                // on commit les modifs dans la db
                em.merge(user);
            } catch (NoResultException e) {
                LOGGER.info("user not logged in, cannot logout");
            }
        }
    }

}
