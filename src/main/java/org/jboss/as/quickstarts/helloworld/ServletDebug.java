package org.jboss.as.quickstarts.helloworld;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.as.FacadeTheComeback;
import org.jboss.as.Genre;
import org.jboss.as.Manga;


/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletDebug")
public class ServletDebug extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	@EJB
	private FacadeTheComeback facade;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDebug() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Manga mg1 = new Manga();
		mg1.setAuthor("Oda Eiichiro");
		mg1.setTitle("One Piece");
		mg1.setDescription("Gloire, fortune et puissance, c'est ce que possédait Gold Roger, le tout puissant roi des pirates, avant de mourir sur l'échafaud. Mais ses dernières paroles ont éveillées bien des convoitises, et lança la fabuleuse 'ère de la piraterie', chacun voulant trouver le fabuleux trésor qu'il disait avoir laissé. Bien des années plus tard, Shanks, un redoutable pirate aux cheveux rouges, rencontre Luffy, un jeune garçon d'une dizaine d'années dans un petit port de pêche. Il veut devenir pirate et le rejoindre, mais Shanks lui répond qu'il est trop jeune. Plus tard, Luffy avalera accidentellement le fruit Gomu Gomu qui rendra son corps élastique, mais aussi maudit par les eaux. Incapable de nager, Luffy ne veut pourtant pas renoncer à son rêve. Pour le consoler lorsqu'il part, Shanks lui offre son chapeau. Luffy jure alors de le rejoindre un jour avec son propre équipage. A 17 ans, Luffy prend la mer dans une petite barque avec pour but de réunir un équipage de pirates, mais de pirates pas comme les autres, qui devront partager sa conception un peu étrange de la piraterie. L'aventure est lancée.");
		List<Genre> genres = new ArrayList();
		genres.add(Genre.Shonen);
		genres.add(Genre.Aventure);
		mg1.setGenres(genres);
		List<URL> chapters = new ArrayList<>();
		try {
			chapters.add(new URL("https://drive.google.com/uc?export=download&id=11pNtUJECkMpHSmXzqIaMjW3VATnhuXIP"));
			mg1.setCover(new URL("https://drive.google.com/uc?export=download&id=1fGDVTXVdp9PjfuxhCpQ201KFTR6VWzDv"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		mg1.setChapters(chapters);
		facade.addManga(mg1);

		
		
		/* Exemple calculatrice 
		String nb1 = request.getParameter("nb1");
		String nb2 = request.getParameter("nb2");
		int resultat = Integer.parseInt(nb1) + Integer.parseInt(nb2);
		response.getWriter().println("<html><body>le resultat est : " + resultat +"</body></html>");
		*/


		response.getWriter().println("<html><body> La base virale VPN a été mise à jour </body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}