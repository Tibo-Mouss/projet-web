package org.jboss.as.quickstarts.helloworld;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.as.FacadeTheComeback;


/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/Servlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private FacadeTheComeback facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/* Exemple calculatrice 
		String nb1 = request.getParameter("nb1");
		String nb2 = request.getParameter("nb2");
		int resultat = Integer.parseInt(nb1) + Integer.parseInt(nb2);
		response.getWriter().println("<html><body>le resultat est : " + resultat +"</body></html>");
		*/

		String op = request.getParameter("op");

		switch(op) {
			case "login":
			case "register":
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				response.getWriter().println("<html><body>L'username : " + username +
				" puis password : " + password + 
				" puis c'est un : " + op + "</body></html>");
				break;
			case "homepage":
				RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
				disp.forward(request, response);
				break;
			default:
				response.getWriter().println("<html><body>L'username : " +
				"Erreur, l'op spécifié n'est pas pris en charge"
				+ "</body></html>");
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		

		switch(op) {
			case "login":
			case "register":
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				/*response.getWriter().println("<html><body>L'username : " + username +
				" puis password : " + password + 
				" puis c'est un : " + login_register + "</body></html>"); */

				Boolean operation_succeeded;
				if (op.equals("login")) {
					operation_succeeded = facade.login(username, password);
				} else {
					operation_succeeded = facade.register(username, password);
				}

				if (operation_succeeded) {
					RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
					disp.forward(request,response);
				} else if (op.equals("login")) {
					response.getWriter().println("<html><body> Erreur de login, mauvais mot de passe ou username non existant.</body></html>");
				} else {
					response.getWriter().println("<html><body> Erreur de register, mauvais mot de passe ou username non existant.</body></html>");
				}
			break;
				

			default:
			break;
		}

		
	}

}