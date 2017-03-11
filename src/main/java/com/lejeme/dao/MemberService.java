package com.lejeme.dao;

import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Member;
import com.lejeme.metier.CRUD.MemberCRUDForm;
import com.lejeme.persistance.DialogueBd;

import java.util.ArrayList;
import java.util.List;

public class MemberService extends Service{

    public void insert(Member member) throws MonException {
        String mysql;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent) values ('"
                + member.getName().toUpperCase()+"', '" + member.getFirstName()
                + "', '" + member.getCity() + "')";
        dialogueBd.insertionBD(mysql);
    }

    public Member get(int id) throws MonException {
        String mysql = "select * from adherent where id_adherent=" + id;
        List<Member> members = getList(mysql);
        if (members.isEmpty())
            return null;
        else {
            return members.get(0);
        }
    }

    public MemberCRUDForm getListCRUD() throws MonException {
        String mysql = "select * from adherent";
        return new MemberCRUDForm(getList(mysql));
    }

    public List<Member> getList() throws MonException {
        String mysql = "select * from adherent";
        return getList(mysql);
    }

    public void edit(Member member, int id) throws MonException {
        String mysql;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "UPDATE adherent set nom_adherent='" + member.getName().toUpperCase() + "',prenom_adherent='" + member.getFirstName() +
                "',ville_adherent='" + member.getCity() + "' WHERE id_adherent="+ id;
        dialogueBd.insertionBD(mysql);
    }

    public void remove(int id) throws MonException {
        String mysql = "DELETE from adherent WHERE id_adherent='" + id + "'";
        remove(mysql);
    }

    private List<Member> getList(String mysql) throws MonException {
        List<Object> rs;
        List<Member> members = new ArrayList<>();
        int index = 0;
        rs = DialogueBd.lecture(mysql);
        while (index < rs.size()) {
            Member member = new Member();
            member.setId(Integer.parseInt(rs.get(index).toString()));
            member.setName(rs.get(index + 1).toString());
            member.setFirstName(rs.get(index + 2).toString());
            member.setCity(rs.get(index + 3).toString());
            index = index + 4;
            members.add(member);
        }
        return members;
    }
}
