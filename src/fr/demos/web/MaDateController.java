package fr.demos.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.demos.formation.essai.Climatisation;

/**
 * Servlet implementation class MaDate
 */
@WebServlet("/MaDateController")
public class MaDateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MaDateController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//d represente le model		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		SimpleDateFormat sdfHour = new SimpleDateFormat ("HH 'h' : mm 'min' :ss 's'");
		String s = sdf.format(d);
		String heure = sdfHour.format(d);
		
		Climatisation clim1 = new Climatisation(1013.5, 2016, "Salle 206", 25.6);
		
	// transfert du model
		
		request.setAttribute("dateDuJour", d);
		request.setAttribute("dateSdf", s);
		request.setAttribute("heure", heure);
		request.setAttribute("clim1", clim1);
		
		
	//appeler la vue 
		
		RequestDispatcher rd = request.getRequestDispatcher("/maDateView.jsp");
		rd.forward(request, response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
