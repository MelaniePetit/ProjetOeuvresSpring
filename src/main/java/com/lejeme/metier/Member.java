package com.lejeme.metier;

import java.io.Serializable;


/**
 * The persistent class for the adherent database table.
 *
 */

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String firstName;
	private String city;


	public Member(int id, String name, String firstName, String city) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.city = city;
	}

	public Member() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}