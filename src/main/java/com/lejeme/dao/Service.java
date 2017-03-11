package com.lejeme.dao;

import com.lejeme.persistance.DialogueBd;
import com.lejeme.meserreurs.MonException;

public class Service {
	public void remove(String mysql) throws MonException {
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		unDialogueBd.insertionBD(mysql);
	}
}
