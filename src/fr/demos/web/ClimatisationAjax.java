package fr.demos.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.demos.data.ClimatisationDAO;
import fr.demos.data.FileClimatisationDAO;
import fr.demos.data.SqlClimatisationDAO;

/**
 * Servlet implementation class ClimatisationAjax
 */
@WebServlet("/ClimatisationAjax")
public class ClimatisationAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClimatisationAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClimatisationDAO dao = new FileClimatisationDAO();
		int nb = dao.nombre("");
		PrintWriter out = response.getWriter();
		out.println("il y a " + nb + " climatisation(s) enregistrée(s) dans le fichier 'climatisation-version'  -  ");
		
		try {
			ClimatisationDAO dao2 = new SqlClimatisationDAO();
			int nb2 = dao2.nombre("");
			PrintWriter out2 = response.getWriter();
			out2.println("il y a " + nb2 + " climatisation(s) enregistrée(s) sur le serveur sql");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
