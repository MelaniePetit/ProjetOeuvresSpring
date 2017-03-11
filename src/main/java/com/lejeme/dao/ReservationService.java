package com.lejeme.dao;

import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.CRUD.ReservationCRUDForm;
import com.lejeme.metier.Reservation;
import com.lejeme.persistance.DialogueBd;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReservationService extends Service{

    public void insert(Reservation reservation) throws MonException {
        String mysql;
        List<Object> rs1;
        List<Object> rs2;
        DialogueBd dialogueBd = DialogueBd.getInstance();

        mysql = "SELECT id_oeuvrevente FROM oeuvrevente WHERE titre_oeuvrevente = '" + reservation.getWorkOfArt().getTitle() + "'";
        rs1 = DialogueBd.lecture(mysql);
        mysql = "SELECT id_adherent FROM adherent WHERE nom_adherent = '" + reservation.getMember().getName() + "'";
        rs2 = DialogueBd.lecture(mysql);
        mysql = "INSERT INTO reservation (id_oeuvrevente, id_adherent, date_reservation, statut)  " + "values ('"
                + Integer.parseInt(rs1.get(0).toString()) + "','"
                + Integer.parseInt(rs2.get(0).toString()) + "','"
                + reservation.getDate() + "','"
                + "confirmee" + "')";
        dialogueBd.insertionBD(mysql);
        mysql = "UPDATE oeuvrevente SET etat_oeuvrevente = 'R' WHERE id_oeuvrevente='" + Integer.parseInt(rs1.get(0).toString()) +"'";

        dialogueBd.insertionBD(mysql);
    }

    public ReservationCRUDForm getListCRUD() throws MonException, ParseException {
        String mysql = "SELECT r.id_oeuvrevente, r.id_adherent, r.date_reservation, a.nom_adherent, a.prenom_adherent, o.titre_oeuvrevente " +
                "FROM reservation as r, adherent as a, oeuvrevente as o " +
                "WHERE r.id_oeuvrevente = o.id_oeuvrevente AND r.id_adherent = a.id_adherent";
        return new ReservationCRUDForm(getList(mysql));
    }

//    public List<Member> getList() throws MonException {
//        String mysql = "SELECT r.id_oeuvrevente, r.id_adherent, r.date_reservation, a.nom_adherent, a.prenom_adherent, o.titre_oeuvrevente " +
//                "FROM reservation as r, adherent as a, oeuvrevente as o " +
//                "WHERE r.id_oeuvrevente = o.id_oeuvrevente AND r.id_adherent = a.id_adherent";
//        return consulterListeAdherents(mysql);
//    }

    private List<Reservation> getList(String mysql) throws MonException, ParseException {
        List<Object> rs;
        List<Reservation> reservations = new ArrayList<>();
        int index = 0;
        rs = DialogueBd.lecture(mysql);
        while (index < rs.size()) {
            Reservation reservation = new Reservation();

            reservation.getWorkOfArt().setId(Integer.parseInt(rs.get(index).toString()));
            reservation.getMember().setId(Integer.parseInt(rs.get(index + 1).toString()));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(df.parse(rs.get(index+2).toString()).getTime());
            reservation.setDate(date);

            reservation.getMember().setName(rs.get(index+3).toString());
            reservation.getMember().setFirstName(rs.get(index+4).toString());
            reservation.getWorkOfArt().setTitle(rs.get(index+5).toString());

            index = index + 6;
            reservations.add(reservation);
        }
        return reservations;
    }

    public Reservation get(int id) throws MonException, ParseException {
        String mysql = "SELECT r.id_oeuvrevente, r.id_adherent, r.date_reservation, a.nom_adherent, a.prenom_adherent, o.titre_oeuvrevente FROM reservation as r, adherent as a, oeuvrevente as o WHERE r.id_oeuvrevente = o.id_oeuvrevente AND r.id_adherent = a.id_adherent AND r.id_oeuvrevente = " + id;
        List<Reservation> reservations = getList(mysql);
        if (reservations.isEmpty())
            return null;
        else {
            return reservations.get(0);
        }
    }

    public void edit(Reservation reservation, int id) throws MonException {
        String mysql;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "UPDATE `reservation` SET `date_reservation`='" + reservation.getDate() + "' WHERE `id_oeuvrevente` = " + id;
        dialogueBd.insertionBD(mysql);
    }

    public void remove(int id) throws MonException {
        String mysql = "DELETE from reservation WHERE id_oeuvrevente='" + id + "'";
        remove(mysql);
    }

    public void updateBeforeDelete(int id) throws MonException{
        String mysql;
        DialogueBd dialogueBd = DialogueBd.getInstance();
        mysql = "UPDATE oeuvrevente SET etat_oeuvrevente='L' WHERE id_oeuvrevente='" + id +"'";
        dialogueBd.insertionBD(mysql);
    }
}
