package com.lejeme.controle;

import com.lejeme.dao.Service;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Proprietaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/owner/")
public class OwnerController {
    private String destinationPage = "";

    @RequestMapping(value = "list.htm")
    public ModelAndView listOwner(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeProprietairesCRUD());
            request.setAttribute("title", "OwnersList");
            request.setAttribute("contentTitle", "Owners List");
            destinationPage = "list";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "add.htm")
    public ModelAndView addOwner(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            destinationPage = "actionOwner";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insert.htm")
    public ModelAndView insertOwner(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Proprietaire unProprio = new Proprietaire();
            unProprio.setNomProprietaire(request.getParameter("nom"));
            unProprio.setPrenomProprietaire(request.getParameter("prenom"));
            Service unService = new Service();
            unService.insertProprietaire(unProprio);

            request.setAttribute("flashMessage_success", "The Owner called " + unProprio.getPrenomProprietaire() + " " + unProprio.getNomProprietaire().toUpperCase() + " has been added successfully");

            request.setAttribute("myEntities", unService.consulterListeProprietairesCRUD());
            request.setAttribute("title", "OwnersList");
            request.setAttribute("contentTitle", "Owners List");

            destinationPage = "list";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/remove.htm")
    public ModelAndView removeOwner(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            unService.supprimerProprietaire(String.valueOf(id));
            request.setAttribute("flashMessage_success", "The owner has been removed successfully");

            unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeProprietairesCRUD());
            request.setAttribute("title", "OwnersList");
            request.setAttribute("contentTitle", "Owners List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/edit.htm")
    public ModelAndView editOwner(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("monProprio", unService.consulterProprietaire(String.valueOf(id)));
            request.setAttribute("edit", true);
            destinationPage = "actionOwner";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/update.htm")
    public ModelAndView updateOwner(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Proprietaire unProprio = new Proprietaire();
            unProprio.setNomProprietaire(request.getParameter("nom"));
            unProprio.setPrenomProprietaire(request.getParameter("prenom"));

            Service unService = new Service();
            unService.editProprietaire(unProprio, String.valueOf(id));

            request.setAttribute("flashMessage_success", "The Owner called " + unProprio.getPrenomProprietaire() + " " + unProprio.getNomProprietaire().toUpperCase() + " has been modified successfully");

            request.setAttribute("myEntities", unService.consulterListeProprietairesCRUD());
            request.setAttribute("title", "OwnersList");
            request.setAttribute("contentTitle", "Owners List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}
