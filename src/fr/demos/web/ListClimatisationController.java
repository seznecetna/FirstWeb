package fr.demos.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.demos.data.ClimatisationDAO;
import fr.demos.data.FileClimatisationDAO;
//import org.glassfish.api.container.RequestDispatcher;
import fr.demos.formation.essai.Climatisation;

/**
 * Servlet implementation class ListClimatisation
 */
@WebServlet("/ListClimatisation")
public class ListClimatisationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListClimatisationController() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("/listClimatisation.jsp");
		List<Climatisation> listClims = null;
		ClimatisationDAO climDao = new FileClimatisationDAO();
		
		try {
			listClims = climDao.rechercheTout();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try (ObjectInputStream ois = new ObjectInputStream(
//				new BufferedInputStream(new FileInputStream("climatisation")));) {
//			listClims = (ArrayList<Climatisation>) ois.readObject();
//
//		} catch (EOFException exc) {
//			System.out.println("fin de fichier");
//
//		} catch (IOException exc) {
//			exc.printStackTrace();
//			System.out.println(exc.getMessage());
//		} catch (ClassNotFoundException exc) {
//			exc.printStackTrace();
//			System.out.println(exc.getMessage());
//		}

		request.setAttribute("listClims", listClims);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String check = request.getParameter("checkList");
		if (check != null && check.equals("checkList")) {
			doGet(request, response);
			// RequestDispatcher rd = request.getRequestDispatcher("/listClimatisation.jsp");
			// rd.forward(request, response);
		}
	}

}
