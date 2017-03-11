package com.lejeme.controle;

import com.lejeme.dao.MemberService;
import com.lejeme.dao.ReservationService;
import com.lejeme.dao.WorkOfArtService;
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
            ReservationService reservationService = new ReservationService();
            request.setAttribute("myEntities", reservationService.getListCRUD());
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
            membersComboBox(request);
            worksOfArtComboBox(request);
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
            Reservation reservation = new Reservation();
            reservation.getWorkOfArt().setTitle(request.getParameter("txttitre"));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());

            reservation.setDate(date);
            reservation.getMember().setName(request.getParameter("txtadherent"));

            ReservationService reservationService = new ReservationService();
            reservationService.insert(reservation);

            request.setAttribute("flashMessage_success", "The reservation has been successfully added");

            request.setAttribute("myEntities", reservationService.getListCRUD());
            request.setAttribute("title", "ReservationsList");
            request.setAttribute("contentTitle", "Reservations List");
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
            ReservationService reservationService = new ReservationService();
            reservationService.updateBeforeDelete(id);
            reservationService.remove(id);

            request.setAttribute("flashMessage_success", "The reservation has been successfully removed");

            request.setAttribute("myEntities", reservationService.getListCRUD());
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
            ReservationService reservationService = new ReservationService();
            MemberService memberService = new MemberService();
            WorkOfArtService workOfArtService = new WorkOfArtService();

            request.setAttribute("maReservation", reservationService.get(id));
            request.setAttribute("mesOeuvres", workOfArtService.getList());
            request.setAttribute("mesAdherents", memberService.getList());

            request.setAttribute("edit", true);
            destinationPage = "actionReservation";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/update.htm")
    public ModelAndView updateReservation(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Reservation reservation = new Reservation();
            reservation.getWorkOfArt().setTitle(request.getParameter("txttitre"));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(df.parse(request.getParameter("txtdate")).getTime());

            reservation.setDate(date);
            reservation.getMember().setName(request.getParameter("txtadherent"));
            ReservationService reservationService = new ReservationService();

            reservationService.edit(reservation, id);

            request.setAttribute("flashMessage_success", "The reservation has been successfully modified");

            request.setAttribute("myEntities", reservationService.getListCRUD());
            request.setAttribute("title", "ReservationsList");
            request.setAttribute("contentTitle", "Reservations List");
            destinationPage = "list";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    private void membersComboBox(HttpServletRequest request){
        try {
            MemberService memberService = new MemberService();
            request.setAttribute("mesAdherents", memberService.getList());
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    private void worksOfArtComboBox(HttpServletRequest request){
        try {
            WorkOfArtService workOfArtService = new WorkOfArtService();
            request.setAttribute("mesOeuvres", workOfArtService.getListAvailable());
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

}
