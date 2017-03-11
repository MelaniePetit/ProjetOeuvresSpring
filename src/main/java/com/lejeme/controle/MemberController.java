package com.lejeme.controle;

import com.lejeme.dao.Service;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Adherent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/")
public class MemberController {
    private String destinationPage = "";

    @RequestMapping(value = "list.htm")
    public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());
            request.setAttribute("title", "MembersList");
            request.setAttribute("contentTitle", "Members List");
            destinationPage = "list";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "add.htm")
    public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            destinationPage = "actionMember";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insert.htm")
    public ModelAndView insertMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Adherent unAdherent = new Adherent();
            unAdherent.setNomAdherent(request.getParameter("nom"));
            unAdherent.setPrenomAdherent(request.getParameter("prenom"));
            unAdherent.setVilleAdherent(request.getParameter("ville"));
            Service unService = new Service();
            unService.insertAdherent(unAdherent);

            request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());
            request.setAttribute("title", "MembersList");
            request.setAttribute("contentTitle", "Members List");
            destinationPage = "list";

            request.setAttribute("flashMessage_success", "The Member " + unAdherent.getPrenomAdherent() + " " + unAdherent.getNomAdherent().toUpperCase() + " has been added successfully");
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/remove.htm")
    public ModelAndView removeMember(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            unService.supprimerAdherent(String.valueOf(id));
            request.setAttribute("flashMessage_success", "The Member has been successfully removed");

            unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());
            request.setAttribute("title", "MembersList");
            request.setAttribute("contentTitle", "Members List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/edit.htm")
    public ModelAndView editMember(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("monAdherent", unService.consulterAdherent(String.valueOf(id)));
            request.setAttribute("edit", true);
            destinationPage = "actionMember";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/update.htm")
    public ModelAndView updateMember(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Adherent unAdherent = new Adherent();
            unAdherent.setNomAdherent(request.getParameter("nom"));
            unAdherent.setPrenomAdherent(request.getParameter("prenom"));
            unAdherent.setVilleAdherent(request.getParameter("ville"));

            Service unService = new Service();
            unService.editAdherent(unAdherent, String.valueOf(id));

            request.setAttribute("flashMessage_success", "The Member " + unAdherent.getPrenomAdherent() + " " + unAdherent.getNomAdherent().toUpperCase() + " has been modified successfully");

            request.setAttribute("myEntities", unService.consulterListeAdherentsCRUD());
            request.setAttribute("title", "MembersList");
            request.setAttribute("contentTitle", "Members List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}
