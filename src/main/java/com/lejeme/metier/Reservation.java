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
	private Member member;
	private WorkOfArt workOfArt;
	private String statut;

	public Reservation() {
		workOfArt = new WorkOfArt();
		member = new Member();
	}

	public Reservation(int id, Date date, Member member, WorkOfArt workOfArt) {
		super();
		this.date = date;
		this.member = member;
		this.workOfArt = workOfArt;
	}

	public int getId(){
		return this.getWorkOfArt().getId();
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

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public WorkOfArt getWorkOfArt() {
		return this.workOfArt;
	}

	public void setWorkOfArt(WorkOfArt workOfArt) {
		this.workOfArt = workOfArt;
	}

}