package fr.demos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

import fr.demos.formation.essai.Climatisation;
import fr.demos.data.ClimatisationDAO;

/**
 * Servlet implementation class SqlClimatisationDAO
 */
@WebServlet("/SqlClimatisationDAO")
public class SqlClimatisationDAO implements ClimatisationDAO {

	private DataSource ds = null;

	public SqlClimatisationDAO() throws Exception {
		// recherche dans l'annuaire du pool de connecetion (utilisation de la livrairie JNDI)
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("jdbc/appiclim");

	}

	@Override
	public void sauve(Climatisation cl) throws Exception {
		// on demande une connexion au pool
		Connection cx = ds.getConnection();
		// On va pouvoir préparer notre requête SQL
		PreparedStatement psmt = cx.prepareStatement("insert into climatisation values (?,?,?,?)");
		// Statement stmt = cx.createStatement(à
		// stmt.executeUpdate("insert into climatisation values
		// ("+cl.getNomAppareil()+","+...
		psmt.setString(1, cl.getNomAppareil());
		psmt.setDouble(2, cl.getTemperature());
		psmt.setDouble(3, cl.getPression());
		psmt.setLong(4, cl.getDatation());
		psmt.executeUpdate();
		// on rend la connexion au pool
		cx.close();

	}

	@Override
	public List<Climatisation> rechercheTout() throws Exception {
		Connection cx = ds.getConnection();
		PreparedStatement psmt = cx.prepareStatement("select * from climatisation");
		ResultSet rs = psmt.executeQuery();
		ArrayList<Climatisation> liste = new ArrayList<>();
		while (rs.next()) {
			String nomAppareil = rs.getString(1);
			double temperature = rs.getDouble(2);
			double pression = rs.getDouble(3);
			long datation = rs.getLong(4);
			Climatisation cl = new Climatisation(pression, datation, nomAppareil, temperature);
			liste.add(cl);

		}
		return liste;
	}

	@Override
	public List<Climatisation> recherche(String critere) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nombre(String critere) {
		// TODO Auto-generated method stub
		int nb = 0;
		try {

			// peu performant : en sql on peut demander directement le nb
			// d'elements
			List<Climatisation> liste = this.rechercheTout();
			nb = liste.size();
		} catch (Exception e) {
		}
		return nb;
	}

}
