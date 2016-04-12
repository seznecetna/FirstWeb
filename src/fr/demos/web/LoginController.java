package fr.demos.web;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		boolean erreur;
		String connexion = request.getParameter("connect");
		if (connexion != null && connexion.equals("Connection")) {
			String nomLogin = request.getParameter("nomUser");
			erreur = false;

			if (nomLogin == null || nomLogin.equals("")) {
				erreur = true;
				request.setAttribute("Nom erreur", "Rentrez un nom");
			}

			if (nomLogin != null && !nomLogin.equals("")) {
				session.setAttribute("nomUser", nomLogin);
				RequestDispatcher rd = request.getRequestDispatcher("/ClimatisationController");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);

			}
		}
	}

}