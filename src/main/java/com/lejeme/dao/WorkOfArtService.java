package com.lejeme.dao;

import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.CRUD.WorkOfArtCRUDForm;
import com.lejeme.metier.WorkOfArt;
import com.lejeme.persistance.DialogueBd;

import java.util.ArrayList;
import java.util.List;

public class WorkOfArtService extends Service{

    public void insert(WorkOfArt workOfArt) throws MonException {
        String mysql;
        List<Object> rs;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "select id_proprietaire from proprietaire where nom_proprietaire = '"+workOfArt.getOwner().getName()+"'";
        rs = DialogueBd.lecture(mysql);
        mysql = "insert into oeuvrevente  (titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire)  " + "values ('"
                + workOfArt.getTitle() + "','"
                + "L" + "','"
                + workOfArt.getPrice() + "','"
                + Integer.parseInt(rs.get(0).toString())+ "')";
        dialogueBd.insertionBD(mysql);
    }

    public WorkOfArt get(int id) throws MonException {
        String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
                " FROM `oeuvrevente` as x,`proprietaire` as y " +
                "WHERE x.id_proprietaire = y.id_proprietaire " +
                "AND id_oeuvrevente = " + id;
        List<WorkOfArt> worksOfArt = getList(mysql);
        if (worksOfArt.isEmpty())
            return null;
        else {
            return worksOfArt.get(0);
        }
    }

    public WorkOfArtCRUDForm getListCRUD() throws MonException {
        String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
                " FROM `oeuvrevente` as x,`proprietaire` as y " +
                "WHERE x.id_proprietaire = y.id_proprietaire ";
        return new WorkOfArtCRUDForm(getList(mysql));
    }

    public List<WorkOfArt> getList() throws MonException {
        String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
                " FROM `oeuvrevente` as x,`proprietaire` as y " +
                "WHERE x.id_proprietaire = y.id_proprietaire ";
        return getList(mysql);
    }

    public List<WorkOfArt> getListAvailable() throws MonException {
        String mysql = "SELECT  id_oeuvrevente, titre_oeuvrevente, prix_oeuvrevente, nom_proprietaire, prenom_proprietaire" +
                " FROM `oeuvrevente` as x,`proprietaire` as y " +
                "WHERE x.id_proprietaire = y.id_proprietaire AND x.etat_oeuvrevente = 'L' ";
        return getList(mysql);
    }

    public void edit(WorkOfArt workOfArt, int id) throws MonException {
        String mysql;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "UPDATE oeuvrevente set titre_oeuvrevente='" + workOfArt.getTitle() + "',prix_oeuvrevente='" + workOfArt.getPrice() +
                "' WHERE id_oeuvrevente="+ id;
        dialogueBd.insertionBD(mysql);
    }

    public void remove(int id) throws MonException {
        String mysql = "DELETE from oeuvrevente WHERE id_oeuvrevente=" + id;
        remove(mysql);
    }

    public boolean isReserved(int id) throws MonException {
        String mysql = "SELECT etat_oeuvrevente FROM oeuvrevente WHERE id_oeuvrevente='" + id + "'";
        List<Object> rs = DialogueBd.lecture(mysql);
        return !rs.isEmpty() && rs.get(0).toString().equals("R");
    }

    private List<WorkOfArt> getList(String mysql) throws MonException {
        List<Object> rs;
        List<WorkOfArt> worksOfArt = new ArrayList<>();
        int index = 0;

        rs = DialogueBd.lecture(mysql);
        while (index < rs.size()) {
            WorkOfArt workOfArt = new WorkOfArt();
            workOfArt.setId(Integer.parseInt(rs.get(index).toString()));
            workOfArt.setTitle(rs.get(index + 1).toString());
            workOfArt.setPrice(Integer.parseInt(rs.get(index + 2).toString()));
            workOfArt.getOwner().setName(rs.get(index + 3).toString());
            workOfArt.getOwner().setFirstName(rs.get(index + 4).toString());
            index = index + 5;
            worksOfArt.add(workOfArt);
        }
        return worksOfArt;
    }
}
