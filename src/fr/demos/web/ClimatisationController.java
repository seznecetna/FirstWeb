package fr.demos.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.demos.data.*;
import fr.demos.formation.essai.Climatisation;

/**
 * Servlet implementation class ClimatisationController
 */
@WebServlet("/ClimatisationController")
public class ClimatisationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClimatisationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// response.getWriter().append("Served at :
	// ").append(request.getContextPath());
	// }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/saisieClimatisation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean erreur = false;
		// ArrayList<String> erreurs = new ArrayList<>();
		// request.setAttribute("erreurs", erreurs);
		RequestDispatcher rd = request.getRequestDispatcher("/saisieClimatisation.jsp");
		String validate = request.getParameter("action");

		if (validate != null && validate.equals("Valider")) {
			String pressure = request.getParameter("pression");
			String year = request.getParameter("annee");
			String name = request.getParameter("nom");
			String temp = request.getParameter("temperature");

			// Garder les valeurs precedement tapées

			request.setAttribute("pression", pressure);
			request.setAttribute("annee", year);
			request.setAttribute("nom", name);
			request.setAttribute("temperature", temp);

			// conversion

			double pression = 0;
			long annee = 0;
			double temperature = 0;

			try {
				pression = Double.parseDouble(pressure);
				// annee = Long.parseLong(year);
				// temperature = Double.parseDouble(temp);

			} catch (NumberFormatException ex) {
				erreur = true;
				request.setAttribute("pressureErreur", "erreur de saissie");

			}
			try {

				annee = Long.parseLong(year);

			} catch (NumberFormatException ex) {
				erreur = true;
				request.setAttribute("anneeErreur", "erreur de saisie");

			}

			try {

				temperature = Double.parseDouble(temp);

			} catch (NumberFormatException ex) {
				erreur = true;
				request.setAttribute("temperatureErreur", "Erreur de saisie");
			}
			// fin de conversion

			if (name == null || name.equals("")) {
				erreur = true;
				request.setAttribute("Appareil Erreur", "nom appareil obligatoire");
			}

			if (pression <= 0) {
				erreur = true;
				request.setAttribute("pressureErreur", "La pression doit êter positive");
			}

			if (temperature < 0 || temperature > 50) {
				erreur = true;
				request.setAttribute("temperatureErreur", "La temperature doit être comprise entre 0 et 50 °C");
			}

			if (!erreur) {
				Climatisation clim = new Climatisation(pression, annee, name, temperature);
				try {
					ClimatisationDAO dao = new SqlClimatisationDAO();
					dao.sauve(clim);
					System.out.println("sauve");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					ClimatisationDAO dao = new FileClimatisationDAO();
					dao.sauve(clim);
					System.out.println("sauve");
				} catch (Exception e) {
					e.printStackTrace();
				}
				rd = request.getRequestDispatcher("/successClimatisation.jsp");

			}

			// PrintWriter out = response.getWriter();
			// out.println("<html>");
			// out.println("<body>");
			// out.println("<p>");

		}
//		String check = request.getParameter("checkList");
//		if (check != null && check.equals("Valider")) {
//			
//		}

		// RequestDispatcher rd =
		// request.getRequestDispatcher("/saisieClimatisation.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
