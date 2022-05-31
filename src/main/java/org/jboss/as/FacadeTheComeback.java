package org.jboss.as;

import java.util.ArrayList;
import java.util.*;
import javax.ejb.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class FacadeTheComeback {

    @PersistenceContext
    EntityManager em;
    
    private List<Manga> mangas = new ArrayList();
    private List<User> users = new ArrayList();

    public List<User> getUsers() {
        return users;
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    public void addReview(int idUser, int idManga, int grade, String text){
        Review rev = new Review();
        rev.setAuthor(em.find(User.class, idUser));
        rev.setComment(text);
        rev.setGrade(grade);
        Manga temp = em.find(Manga.class, idManga);
        temp.getReviews().add(rev);
        em.persist(temp);
    }



    public void addToFavorite(String idUser, int idManga){
        User usr = em.find(User.class, idUser);
        Manga mg = em.find(Manga.class, idManga);
        usr.getFavorites().add(mg);
    }

    public List<Manga> searchbyGenre(List<Genre> genres){
        List<Manga> resultat = new ArrayList<Manga>(this.mangas);
        ListIterator<Genre> itg = genres.listIterator();

        while (itg.hasNext()){
            ListIterator<Manga> itm = resultat.listIterator();
            Genre genre = itg.next();
            List<Manga> toRemove = new ArrayList<>();
            while(itm.hasNext()){
                Manga temp = itm.next();
                if (!temp.getGenres().contains(genre)){
                    toRemove.add(temp);
                }
            }
            resultat.removeAll(toRemove);
        }

        return resultat;
    }

    public boolean login(String id, String pswd){
        User usr = em.find(User.class, id);
        return this.users.contains(usr);
    }

    public boolean register(String idUser, String pswd){
        if (em.find(User.class, idUser) != null){
            return false;
        } else{
            User usr = new User();
            usr.setPassword(pswd);
            usr.setUsername(idUser);
            this.users.add(usr);
            em.persist(usr);
            return true;
        }
    }
    
    public List<Manga> getFavourites(String userId){
        return em.find(User.class, userId).getFavorites();
    }

    public List<Review> getReviews(String mangaId){
        return em.find(Manga.class, mangaId).getReviews();
    }
    

    


}
