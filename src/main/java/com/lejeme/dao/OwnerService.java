package com.lejeme.dao;

import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.CRUD.OwnerCRUDForm;
import com.lejeme.metier.Owner;
import com.lejeme.persistance.DialogueBd;

import java.util.ArrayList;
import java.util.List;

public class OwnerService extends Service{

    public OwnerCRUDForm getListCRUD() throws MonException {
        String mysql = "select * from proprietaire";
        return new OwnerCRUDForm(getList(mysql));
    }

    public List<Owner> getList() throws MonException {
        String mysql = "SELECT * FROM proprietaire ";
        return getList(mysql);
    }

    private List<Owner> getList(String mysql) throws MonException {
        List<Object> rs;
        List<Owner> owners = new ArrayList<>();
        int index = 0;
        rs = DialogueBd.lecture(mysql);
        while (index < rs.size()) {
            Owner owner = new Owner();
            owner.setId(Integer.parseInt(rs.get(index).toString()));
            owner.setName(rs.get(index + 1).toString());
            owner.setFirstName(rs.get(index + 2).toString());
            index = index + 3;
            owners.add(owner);
        }
        return owners;
    }

    public void insert(Owner owner) throws MonException{
        String mysql;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        mysql = "insert into proprietaire (nom_proprietaire, prenom_proprietaire) values ('"
                + owner.getName().toUpperCase()+"' ,'" + owner.getFirstName() +"')";
        unDialogueBd.insertionBD(mysql);
    }

    public void remove(int id) throws MonException {
        String mysql = "DELETE from proprietaire WHERE id_proprietaire='" + id + "'";
        remove(mysql);
    }

    public Owner get(int id) throws MonException {
        String mysql = "select * from proprietaire where id_proprietaire=" + id;
        List<Owner> owners = getList(mysql);
        if (owners.isEmpty()) {
            return null;
        }
        else {
            return owners.get(0);
        }
    }

    public void edit(Owner owner, int id) throws MonException {
        String mysql;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "UPDATE proprietaire set nom_proprietaire='" + owner.getName().toUpperCase() + "',prenom_proprietaire='"
                + owner.getFirstName() + "' WHERE id_proprietaire="+ id;
        dialogueBd.insertionBD(mysql);
    }

}
