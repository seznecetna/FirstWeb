package fr.demos.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.demos.formation.essai.*;

public class FileClimatisationDAO implements ClimatisationDAO {

	@Override
	public void sauve(Climatisation cl) throws Exception {
		ArrayList<Climatisation> liste = new ArrayList<>();
		// lecture fichier avant écriture : on récupère la liste des clims (si elle existe)
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("climatisations-version")));) {
			liste = (ArrayList) ois.readObject();
		} catch (IOException exc) {
			System.out.println(exc.getMessage());
		} catch (ClassNotFoundException exc) {
			System.out.println(exc.getMessage());
		}
		// on complete la liste retrouvée avec le nouvel élément
		
		liste.add(cl);
		// on écrit la nouvelle liste
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("climatisations-version")));) {
			oos.writeObject(liste);
			oos.flush();
		}
		
	}

	@Override
	public List<Climatisation> rechercheTout() throws Exception {
		ArrayList<Climatisation> listClims = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("climatisations-version")));) {
			
			listClims = (ArrayList<Climatisation>) ois.readObject();

		} catch (EOFException exc) {
			System.out.println("fin de fichier");

		} catch (IOException exc) {
			exc.printStackTrace();
			System.out.println(exc.getMessage());
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
			System.out.println(exc.getMessage());
		}
		return listClims;
	}

	@Override
	public List<Climatisation> recherche(String critere) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nombre(String critere) {
		// TODO Auto-generated method stub
		List<Climatisation> liste = null;
		int nb = 0;
		try {
			liste = this.rechercheTout();
			nb = liste.size();
		} catch (Exception e) {
	
			System.out.println(e.getMessage());
		}
		
		return nb;
	}
	
	

}
