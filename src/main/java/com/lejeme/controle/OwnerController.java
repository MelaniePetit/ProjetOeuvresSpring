package com.lejeme.controle;

import com.lejeme.dao.OwnerService;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/owners/")
public class OwnerController {
    private String destinationPage = "";

    @RequestMapping(value = "list.htm")
    public ModelAndView listOwner(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            OwnerService ownerService = new OwnerService();
            request.setAttribute("myEntities", ownerService.getListCRUD());
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
            Owner owner = new Owner();
            owner.setName(request.getParameter("nom"));
            owner.setFirstName(request.getParameter("prenom"));

            OwnerService ownerService = new OwnerService();
            ownerService.insert(owner);

            request.setAttribute("flashMessage_success", "The Owner called " + owner.getFirstName() + " " + owner.getName().toUpperCase() + " has been added successfully");

            request.setAttribute("myEntities", ownerService.getListCRUD());
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
            OwnerService ownerService = new OwnerService();
            ownerService.remove(id);
            request.setAttribute("flashMessage_success", "The owner has been removed successfully");

            request.setAttribute("myEntities", ownerService.getListCRUD());
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
            OwnerService ownerService = new OwnerService();
            request.setAttribute("monProprio", ownerService.get(id));
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
            Owner owner = new Owner();
            owner.setName(request.getParameter("nom"));
            owner.setFirstName(request.getParameter("prenom"));

            OwnerService ownerService = new OwnerService();
            ownerService.edit(owner, id);

            request.setAttribute("flashMessage_success", "The Owner called " + owner.getFirstName() + " " + owner.getName().toUpperCase() + " has been modified successfully");

            request.setAttribute("myEntities", ownerService.getListCRUD());
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
