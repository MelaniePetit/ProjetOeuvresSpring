package com.lejeme.metier;

import java.io.Serializable;

/**
 * The persistent class for the proprietaire database table.
 */
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
    private String firstName;
    
	public Owner() {
	}

	public Owner(int id, String name, String firstName) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
	}

	public int getId() {
		return this.id;
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

}