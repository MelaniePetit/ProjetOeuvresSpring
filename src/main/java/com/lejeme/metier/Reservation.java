package com.lejeme.metier;

import java.io.Serializable;
import java.sql.Date;


/**
 * The persistent class for the reservation database table.
 * 
 */
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	private java.util.Date date;
	private Adherent adherent;
	private Oeuvrevente oeuvrevente;
	private String statut;

	public Reservation() {
		oeuvrevente = new Oeuvrevente();
		adherent = new Adherent();
	}

	public Reservation(int id, Date date, Adherent adherent, Oeuvrevente oeuvrevente) {
		super();
		this.date = date;
		this.adherent = adherent;
		this.oeuvrevente = oeuvrevente;
	}

	public int getId(){
		return this.getOeuvrevente().getIdOeuvrevente();
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Adherent getAdherent() {
		return this.adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Oeuvrevente getOeuvrevente() {
		return this.oeuvrevente;
	}

	public void setOeuvrevente(Oeuvrevente oeuvrevente) {
		this.oeuvrevente = oeuvrevente;
	}

}