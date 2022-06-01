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
		mg1.setDescription("Gloire, fortune et puissance, c'est ce que possédait Gold Roger, le tout puissant roi des pirates, avant de mourir sur l'échafaud. Mais ses dernières paroles ont éveillées bien des convoitises, et lança la fabuleuse \"ère de la piraterie\", chacun voulant trouver le fabuleux trésor qu'il disait avoir laissé. Bien des années plus tard, Shanks, un redoutable pirate aux cheveux rouges, rencontre Luffy, un jeune garçon d'une dizaine d'années dans un petit port de pêche. Il veut devenir pirate et le rejoindre, mais Shanks lui répond qu'il est trop jeune. Plus tard, Luffy avalera accidentellement le fruit Gomu Gomu qui rendra son corps élastique, mais aussi maudit par les eaux. Incapable de nager, Luffy ne veut pourtant pas renoncer à son rêve. Pour le consoler lorsqu'il part, Shanks lui offre son chapeau. Luffy jure alors de le rejoindre un jour avec son propre équipage. A 17 ans, Luffy prend la mer dans une petite barque avec pour but de réunir un équipage de pirates, mais de pirates pas comme les autres, qui devront partager sa conception un peu étrange de la piraterie. L'aventure est lancée.");
		List<Genre> genres1 = new ArrayList();
		genres1.add(Genre.Shonen);
		genres1.add(Genre.Aventure);
		genres1.add(Genre.Action);
		genres1.add(Genre.Comedie);
		genres1.add(Genre.Drame);
		genres1.add(Genre.Fantasy);
		mg1.setPublication(1997);
		mg1.setGenres(genres1);
		List<URL> chapters1 = new ArrayList<>();

		Manga mg2 = new Manga();
		mg2.setAuthor("Tite Kubo");
		mg2.setTitle("Bleach");
		mg2.setDescription("Kurosaki Ichigo, un étudiant de quinze ans aux cheveux orange qui aime la bagarre (comme son père) a la particularité de voir les fantômes ainsi que de pouvoir les toucher. Cela l'amène à rencontrer Kuchiki Rukia, un Shinigami (dieu de la mort) qui combat un Hollow. Le déroulement du combat amène Kuchiki à donner ses pouvoirs à Ichigo qui deviens alors lui même un Shinigami. C'est maintenant à son tour de protéger la ville des Hollows.");
		List<Genre> genres2 = new ArrayList();
		genres2.add(Genre.Shonen);
		genres2.add(Genre.Aventure);
		genres2.add(Genre.Action);
		genres2.add(Genre.Comedie);
		genres2.add(Genre.Drame);
		genres2.add(Genre.Fantasy);
		mg2.setPublication(2001);
		mg2.setGenres(genres2);
		List<URL> chapters2 = new ArrayList<>();

		Manga mg3 = new Manga();
		mg3.setAuthor("Ohba Tsugumi");
		mg3.setTitle("Death Note");
		mg3.setDescription("Light Yagami, un jeune étudiant surdoué, ramasse un jour le \"Death Note\", un carnet tenu auparavant par un shinigami (Dieu de la mort), Ryuk, qui apparemment s'ennuyait dans son monde. Il suffit d'écrire le nom d'une personne dans ce carnet, et celle-ci meurt (selon certaines conditions que le shinigami expliquera à Light lors de leur rencontre). C'est ainsi qu'avec le \"Death Note\" entre les mains, Light décide de débarrasser la planète de tous les criminels pour en faire un monde juste, un monde parfait. Cependant, qui est-il pour juger les gens ? Il devient donc le pire criminel recherché de toute la planète...");
		List<Genre> genres3 = new ArrayList();
		genres3.add(Genre.Drame);
		mg3.setPublication(2006);
		mg3.setGenres(genres3);
		List<URL> chapters3 = new ArrayList<>();

		try {
			chapters1.add(new URL("https://drive.google.com/file/d/1-zOWenyMifdDWUEP48MqqxgNUMs5S99C/view?usp=sharing"));
			mg1.setCover(new URL("https://drive.google.com/uc?export=download&id=1Rsp-UqacS3mMucs-dCxlS6Mq-x3XeC6U"));
			chapters2.add(new URL("https://drive.google.com/uc?export=download&id=11pNtUJECkMpHSmXzqIaMjW3VATnhuXIP"));
			mg2.setCover(new URL("https://drive.google.com/uc?export=download&id=1fGDVTXVdp9PjfuxhCpQ201KFTR6VWzDv"));
			chapters3.add(new URL("https://drive.google.com/uc?export=download&id=1eP9euoFfNPDHUwo56j4FACST0jq6TFn0"));
			mg3.setCover(new URL("https://drive.google.com/uc?export=download&id=1l-5p6Cu7nAV7qeJkv0_7N7FvSOeZ7rUm"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		mg1.setChapters(chapters1);
		mg2.setChapters(chapters2);
		mg3.setChapters(chapters3);
		facade.addManga(mg1);
		facade.addManga(mg2);
		facade.addManga(mg3);
		
		
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