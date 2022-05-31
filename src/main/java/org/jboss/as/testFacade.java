package org.jboss.as;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.ManageReferralControl;

public class testFacade {
    public static void main(String[] args) {
        FacadeTheComeback f = new FacadeTheComeback();
        List<Manga> mangas = f.getMangas();
        
        Manga manga1 = new Manga();
        manga1.getGenres().add(Genre.Seinen);
        manga1.getGenres().add(Genre.Shonen);

        Manga manga2 = new Manga();
        manga2.getGenres().add(Genre.Seinen);

        mangas.add(manga1);
        mangas.add(manga2);

        List<Genre> criteres = new ArrayList<Genre>();
        criteres.add(Genre.Shonen);
        criteres.add(Genre.Seinen);

        System.out.print(f.searchbyGenre(criteres));
    }
}
