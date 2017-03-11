package com.lejeme.controle;

import com.lejeme.dao.Service;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/reservations/")
public class ReservationController {
    private String destinationPage = "";

    @RequestMapping(value = "list.htm")
    public ModelAndView listReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeReservationCRUD());
            request.setAttribute("title", "ReservationsList");
            request.setAttribute("contentTitle", "Reservations List");
            destinationPage = "list";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "add.htm")
    public ModelAndView addReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            adherentsComboBox(request);
            oeuvresComboBox(request);
            destinationPage = "actionReservation";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insert.htm")
    public ModelAndView insertReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Reservation uneResa = new Reservation();
            uneResa.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
            uneResa.setDate(date);
            uneResa.getAdherent().setNomAdherent(request.getParameter("txtadherent"));

            Service unService = new Service();
            unService.insertReservation(uneResa);

            request.setAttribute("flashMessage_success", "The reservation has been successfully added");
            destinationPage = "list";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/remove.htm")
    public ModelAndView removeReservation(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            unService.updateOeuvreBeforeDeleteReservation(String.valueOf(id));
            unService.supprimerReservation(String.valueOf(id));
            request.setAttribute("flashMessage_success", "The reservation has been successfully removed");

            unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeReservationCRUD());
            request.setAttribute("title", "ReservationsList");
            request.setAttribute("contentTitle", "Reservations List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/edit.htm")
    public ModelAndView editReservation(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("maReservation", unService.consulterReservation(String.valueOf(id)));
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvres());
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());

            request.setAttribute("edit", true);
            destinationPage = "actionOwner";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/update.htm")
    public ModelAndView updateReservation(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Reservation uneReservation = new Reservation();
            uneReservation.getOeuvrevente().setTitreOeuvrevente(request.getParameter("txttitre"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());
            uneReservation.setDate(date);
            uneReservation.getAdherent().setNomAdherent(request.getParameter("txtadherent"));
            Service unService = new Service();

            unService.editReservation(uneReservation, String.valueOf(id));

            request.setAttribute("flashMessage_success", "The reservation has been successfully modified");

            request.setAttribute("myEntities", unService.consulterListeReservationCRUD());
            request.setAttribute("title", "ReservationsList");
            request.setAttribute("contentTitle", "Reservations List");
            destinationPage = "list";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    private void adherentsComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void oeuvresComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesOeuvres", unService.consulterListeOeuvresDisponibles());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
