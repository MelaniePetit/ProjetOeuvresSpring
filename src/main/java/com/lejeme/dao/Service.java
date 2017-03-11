package com.lejeme.dao;

import com.lejeme.metier.Adherent;
import com.lejeme.metier.Reservation;
import com.lejeme.metier.Oeuvrevente;
import com.lejeme.persistance.DialogueBd;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.*;
import com.lejeme.metier.CRUD.AdherentCRUDForm;
import com.lejeme.metier.CRUD.OeuvreCRUDForm;
import com.lejeme.metier.CRUD.ProprietaireCRUDForm;
import com.lejeme.metier.CRUD.ReservationCRUDForm;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Service {

	/****************************************** ADHERENTS **********************************************/

	// Mise a jour des caracteristiques d'un adherent
	// Le booleen indique s'il s'agit d'un nouvel adherent, auquel cas on fait
	// une creation

	public void insertAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent().toUpperCase()+"'"
					+ ",'" + unAdherent.getPrenomAdherent()
					+ "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	// gestion des adherents
	// Consultation d'un adherent par son numero
	// Fabrique et renvoie un objet adherent contenant le resultat de la requete
	// BDD
	public Adherent consulterAdherent(String numero) throws MonException {
		String mysql = "select * from adherent where id_adherent=" + numero;
		List<Adherent> mesAdh = consulterListeAdherents(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	}

	// Consultation des adherents
	// Fabrique et renvoie une liste d'objets adherent contenant le resultat de
	// la requete BDD
	public AdherentCRUDForm consulterListeAdherentsCRUD() throws MonException {
		String mysql = "select * from adherent";
		return new AdherentCRUDForm(consulterListeAdherents(mysql));
	}

	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent";
		return consulterListeAdherents(mysql);
	}

	private List<Adherent> consulterListeAdherents(String mysql) throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cree un stage
				Adherent unA = new Adherent();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				// On incremente tous les 3 champs
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	//Modification d'un adhérent
	public void editAdherent(Adherent unAdherent, String numero) throws MonException {
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();

		try {
			mysql = "UPDATE adherent set nom_adherent='" + unAdherent.getNomAdherent().toUpperCase() + "',prenom_adherent='" + unAdherent.getPrenomAdherent() +
					"',ville_adherent='" + unAdherent.getVilleAdherent() + "' WHERE id_adherent="+ numero;

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	public void supprimerAdherent(String id) throws MonException {
		String mysql = "DELETE from adherent WHERE id_adherent='" + id + "'";
		supprimer(mysql);
	}

	/****************************************** OEUVRES **********************************************/

	// Mise a jour des caracteristiques d'une oeuvre
	// Le booleen indique s'il s'agit d'une nouvelle oeuvre, auquel cas on fait
	// une creation
	public void insertOeuvre(Oeuvrevente uneOeuvre) throws MonException {
		String mysql;
		List<Object> rs;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "select id_proprietaire from proprietaire where nom_proprietaire = '"+uneOeuvre.getProprietaire().getNomProprietaire()+"'";
			rs = DialogueBd.lecture(mysql);
			mysql = "insert into oeuvrevente  (titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire)  " + "values ('"
					+ uneOeuvre.getTitreOeuvrevente() + "','"
                    + "L" + "','"
                    + uneOeuvre.getPrixOeuvrevente() + "','"
					+ Integer.parseInt(rs.get(0).toString())+ "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	// Consultation des oeuvres
	// Fabrique et renvoie une liste d'objets oeuvres contenant le resultat de
	// la requete BDD
	public Oeuvrevente consulterOeuvre(String numero) throws MonException {
		String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
				" FROM `oeuvrevente` as x,`proprietaire` as y " +
				"WHERE x.id_proprietaire = y.id_proprietaire " +
				"AND id_oeuvrevente = " + numero;
		List<Oeuvrevente> mesO = consulterListeOeuvres(mysql);
		if (mesO.isEmpty())
			return null;
		else {
			return mesO.get(0);
		}
	}
	public OeuvreCRUDForm consulterListeOeuvresCRUD() throws MonException {
		String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
				" FROM `oeuvrevente` as x,`proprietaire` as y " +
				"WHERE x.id_proprietaire = y.id_proprietaire ";
		return new OeuvreCRUDForm(consulterListeOeuvres(mysql));
	}

	public List<Oeuvrevente> consulterListeOeuvres() throws MonException {
		String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
				" FROM `oeuvrevente` as x,`proprietaire` as y " +
				"WHERE x.id_proprietaire = y.id_proprietaire ";
		return consulterListeOeuvres(mysql);
	}

    public List<Oeuvrevente> consulterListeOeuvresDisponibles() throws MonException {
        String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
                " FROM `oeuvrevente` as x,`proprietaire` as y " +
                "WHERE x.id_proprietaire = y.id_proprietaire AND x.etat_oeuvrevente = 'L' ";
        return consulterListeOeuvres(mysql);
    }

	private List<Oeuvrevente> consulterListeOeuvres(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvres = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cree un stage
				Oeuvrevente unA = new Oeuvrevente();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setTitreOeuvrevente(rs.get(index + 1).toString());
				unA.setPrixOeuvrevente(Integer.parseInt(rs.get(index + 2).toString()));
				unA.getProprietaire().setNomProprietaire(rs.get(index + 3).toString());
				unA.getProprietaire().setPrenomProprietaire(rs.get(index + 4).toString());

				// On incremente tous les 5 champs
				index = index + 5;
				mesOeuvres.add(unA);
			}
			return mesOeuvres;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	//Modification d'une oeuvre
	public void editOeuvre(Oeuvrevente uneOeuvre, String numero) throws MonException {
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();

		try {
			mysql = "UPDATE oeuvrevente set titre_oeuvrevente='" + uneOeuvre.getTitreOeuvrevente() + "',prix_oeuvrevente='" + uneOeuvre.getPrixOeuvrevente() +
					"' WHERE id_oeuvrevente="+ numero;
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	public void supprimerOeuvre(int id) throws MonException {
		String mysql = "DELETE from oeuvrevente WHERE id_oeuvrevente=" + id;
		supprimer(mysql);
	}

	public boolean isOeuvreReserved(int id) throws MonException {
		String mysql = "SELECT etat_oeuvrevente FROM oeuvrevente WHERE id_oeuvrevente='" + id + "'";
		List<Object> rs = DialogueBd.lecture(mysql);
		return !rs.isEmpty() && rs.get(0).toString().equals("R");
	}

	/****************************************** RESERVATIONS **********************************************/

	// Mise a jour des caracteristiques d'une oeuvre
	// Le booleen indique s'il s'agit d'une nouvelle oeuvre, auquel cas on fait
	// une creation
	public void insertReservation(Reservation uneResa) throws MonException {
		String mysql;
		List<Object> rs1;
		List<Object> rs2;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "SELECT id_oeuvrevente FROM oeuvrevente WHERE titre_oeuvrevente = '" + uneResa.getOeuvrevente().getTitreOeuvrevente() + "'";
			rs1 = DialogueBd.lecture(mysql);
			mysql = "SELECT id_adherent FROM adherent WHERE nom_adherent = '" + uneResa.getAdherent().getNomAdherent() + "'";
			rs2 = DialogueBd.lecture(mysql);
			mysql = "INSERT INTO reservation (id_oeuvrevente, id_adherent, date_reservation, statut)  " + "values ('"
					+ Integer.parseInt(rs1.get(0).toString()) + "','"
					+ Integer.parseInt(rs2.get(0).toString()) + "','"
					+ uneResa.getDate() + "','"
					+ "confirmee" + "')";
			unDialogueBd.insertionBD(mysql);
			mysql = "UPDATE oeuvrevente SET etat_oeuvrevente = 'R' WHERE id_oeuvrevente='" + Integer.parseInt(rs1.get(0).toString()) +"'";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	//Reservation
	public ReservationCRUDForm consulterListeReservationCRUD() throws MonException {
		String mysql = "SELECT r.id_oeuvrevente, r.id_adherent, r.date_reservation, a.nom_adherent, a.prenom_adherent, o.titre_oeuvrevente " +
				"FROM reservation as r, adherent as a, oeuvrevente as o " +
				"WHERE r.id_oeuvrevente = o.id_oeuvrevente AND r.id_adherent = a.id_adherent";
		return new ReservationCRUDForm(consulterListeReservation(mysql));
	}

	public List<Adherent> consulterListeReservation() throws MonException {
		String mysql = "SELECT r.id_oeuvrevente, r.id_adherent, r.date_reservation, a.nom_adherent, a.prenom_adherent, o.titre_oeuvrevente " +
				"FROM reservation as r, adherent as a, oeuvrevente as o " +
				"WHERE r.id_oeuvrevente = o.id_oeuvrevente AND r.id_adherent = a.id_adherent";
		return consulterListeAdherents(mysql);
	}

	private List<Reservation> consulterListeReservation(String mysql) throws MonException {
		List<Object> rs;
		List<Reservation> mesResa = new ArrayList<Reservation>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);

			while (index < rs.size()) {
				// On cree un stage

				Reservation unR = new Reservation();
				// il faut redecouper la liste pour retrouver les lignes
				unR.getOeuvrevente().setIdOeuvrevente(Integer.parseInt(rs.get(index).toString()));
				unR.getAdherent().setIdAdherent(Integer.parseInt(rs.get(index + 1).toString()));

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(df.parse(rs.get(index+2).toString()).getTime());
				unR.setDate(date);

				unR.getAdherent().setNomAdherent(rs.get(index+3).toString());
				unR.getAdherent().setPrenomAdherent(rs.get(index+4).toString());
				unR.getOeuvrevente().setTitreOeuvrevente(rs.get(index+5).toString());

				// On incremente tous les 6 champs
				index = index + 6;

				mesResa.add(unR);
			}

			return mesResa;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	public Reservation consulterReservation(String numero) throws MonException {
		String mysql = "SELECT r.id_oeuvrevente, r.id_adherent, r.date_reservation, a.nom_adherent, a.prenom_adherent, o.titre_oeuvrevente FROM reservation as r, adherent as a, oeuvrevente as o WHERE r.id_oeuvrevente = o.id_oeuvrevente AND r.id_adherent = a.id_adherent AND r.id_oeuvrevente = " + numero;
		List<Reservation> mesResa = consulterListeReservation(mysql);
		if (mesResa.isEmpty())
			return null;
		else {
			return mesResa.get(0);
		}
	}

	public void editReservation(Reservation uneResa, String numero) throws MonException {
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE `reservation` SET `date_reservation`='" + uneResa.getDate() + "' WHERE `id_oeuvrevente` = " + numero;
			System.out.println(mysql);
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	public void supprimerReservation(String id) throws MonException {
		String mysql = "DELETE from reservation WHERE id_oeuvrevente='" + id + "'";
		supprimer(mysql);
	}

	public void updateOeuvreBeforeDeleteReservation(String id) throws MonException{
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "UPDATE oeuvrevente SET etat_oeuvrevente='L' WHERE id_oeuvrevente='" + id +"'";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}


	/****************************************** PROPRIETAIRES **********************************************/

	// Consultation des proprietaire
	// Fabrique et renvoie une liste d'objets proprietaire contenant le resultat de
	// la requete BDD
	public ProprietaireCRUDForm consulterListeProprietairesCRUD() throws MonException {
		String mysql = "select * from proprietaire";
		return new ProprietaireCRUDForm(consulterListeProprietaire(mysql));
	}

	public List<Proprietaire> consulterListeProprietaire() throws MonException {
		String mysql = "SELECT * FROM proprietaire ";
		return consulterListeProprietaire(mysql);
	}

	private List<Proprietaire> consulterListeProprietaire(String mysql) throws MonException {
		List<Object> rs;
		List<Proprietaire> mesProprietaires = new ArrayList<Proprietaire>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs = DialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On cree un stage
				Proprietaire unA = new Proprietaire();

				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomProprietaire(rs.get(index + 1).toString());
				unA.setPrenomProprietaire(rs.get(index + 2).toString());

				// On incremente tous les 3 champs
				index = index + 3;
				mesProprietaires.add(unA);

			}
			return mesProprietaires;
		} catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	public void insertProprietaire(Proprietaire proprio) throws MonException{
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into proprietaire (nom_proprietaire, prenom_proprietaire)  " + "values ('"
					+ proprio.getNomProprietaire().toUpperCase()+"'"
					+ ",'" + proprio.getPrenomProprietaire() +"')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	public void supprimerProprietaire(String id) throws MonException {
		String mysql = "DELETE from proprietaire WHERE id_proprietaire='" + id + "'";
		supprimer(mysql);
	}

	public Proprietaire consulterProprietaire(String numero) throws MonException {
		String mysql = "select * from proprietaire where id_proprietaire=" + numero;
		List<Proprietaire> mesProprio = consulterListeProprietaire(mysql);
		if (mesProprio.isEmpty()) {
			return null;
		}
		else {
			return mesProprio.get(0);
		}
	}

	public void editProprietaire(Proprietaire unProprio, String numero) throws MonException {
		String mysql;
		DialogueBd unDialogueBd = DialogueBd.getInstance();

		try {
			mysql = "UPDATE proprietaire set nom_proprietaire='" + unProprio.getNomProprietaire().toUpperCase() + "',prenom_proprietaire='"
					+ unProprio.getPrenomProprietaire() + "' WHERE id_proprietaire="+ numero;
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
	}

	/****************************************** AUTRES **********************************************/

	public void supprimer(String mysql) throws MonException {

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}

	}



}
