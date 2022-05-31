package com.pixels.services;

import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixels.adapters.PixelAdapter;
import com.pixels.adapters.UserAdapter;
import com.pixels.beans.Pixel;
import com.pixels.beans.Transaction;
import com.pixels.beans.User;

@Singleton
@Path("/pixel")
public class PixelService {

    @PersistenceContext
    private EntityManager em;

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Pixel.class, new PixelAdapter())
            .registerTypeAdapter(User.class, new UserAdapter())
            .create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        TypedQuery<Pixel> query = em.createNamedQuery("Pixel.list", Pixel.class);
        return gson.toJson(query.getResultList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String single(@PathParam("id") Long id) {
        Pixel pixel = em.find(Pixel.class, id);
        if (pixel == null) {
            throw new NotFoundException();
        } else {
            return gson.toJson(pixel);
        }
    }

    @POST
    @Path("{id}/buy/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void buy_pixel(@PathParam("id") Long id, @CookieParam("JSESSIONID") Cookie cookie) {
        // on récupère le pixel de la db via son id
        Pixel pixel = em.find(Pixel.class, id);

        // on récupère le nouveau proprio
        User user = User.fromSessionID(cookie.getValue(), em);

        if (user.getBalance() >= pixel.getPrice()) {
            // on update l'owner
            pixel.setOwner(user);
            user.setBalance(user.getBalance() - pixel.getPrice());

            // on ajoute la transaction
            Date now = new Date(System.currentTimeMillis());
            Transaction new_transaction = new Transaction(now, pixel);
            em.persist(new_transaction);

            // on commit les modifs dans la db
            em.merge(pixel);
            em.merge(user);
        }
    }

    @POST
    @Path("{id}/modify/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void modify(@PathParam("id") Long id, @CookieParam("JSESSIONID") Cookie cookie,
            @FormParam("red") int red, @FormParam("green") int green, @FormParam("blue") int blue,
            @FormParam("price") int price, @FormParam("description") String description) {
        // on récupère le pixel de la db via son id
        Pixel pixel = em.find(Pixel.class, id);

        // on récupère le nouveau proprio
        User user = User.fromSessionID(cookie.getValue(), em);

        if (user.equals(pixel.getOwner())) {
            pixel.setPrice(price);
            pixel.setDescription(description);
            pixel.setColor(String.format("%02X%02X%02X", red, green, blue));

            // on ajoute la transaction
            Date now = new Date(System.currentTimeMillis());
            Transaction new_transaction = new Transaction(now, pixel);
            em.persist(new_transaction);

            // on commit les modifs dans la db
            em.merge(pixel);
            em.merge(user);
        }
    }
}
