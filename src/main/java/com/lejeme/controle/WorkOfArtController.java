package com.lejeme.controle;

import com.lejeme.dao.Service;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Oeuvrevente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/workofart")
public class WorkOfArtController {
    private String destinationPage = "";

    @RequestMapping(value = "list.htm")
    public ModelAndView listWorkOfArt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeOeuvresCRUD());
            request.setAttribute("title", "WorksOfArtList");
            request.setAttribute("contentTitle", "Works Of Art List");
            destinationPage = "list";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "add.htm")
    public ModelAndView addWorkOfArt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            proprietairesComboBox(request);
            destinationPage = "actionOwner";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insert.htm")
    public ModelAndView insertWorkOfArt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Oeuvrevente uneOeuvre = new Oeuvrevente();
            uneOeuvre.setTitreOeuvrevente(request.getParameter("titre"));
            uneOeuvre.setPrixOeuvrevente(Integer.parseInt(request.getParameter("prix")));
            uneOeuvre.getProprietaire().setNomProprietaire(request.getParameter("nomproprio"));

            Service unService = new Service();
            unService.insertOeuvre(uneOeuvre);

            request.setAttribute("flashMessage_success", "The Work of art '" + uneOeuvre.getTitreOeuvrevente() + "' has been added successfully");

            request.setAttribute("myEntities", unService.consulterListeOeuvresCRUD());
            request.setAttribute("title", "WorksOfArtList");
            request.setAttribute("contentTitle", "Works Of Art List");
            destinationPage = "list";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "{id}/remove.htm")
    public ModelAndView removeWorkOfArt(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            if(!unService.isOeuvreReserved(Integer.parseInt(String.valueOf(id))))
            {
                unService.supprimerOeuvre(Integer.parseInt(String.valueOf(id)));
                request.setAttribute("flashMessage_success", "The Work of art has been successfully removed");
            }
            else
            {
                request.setAttribute("flashMessage_error", "The Work of art is Reserved, delete the reservation first");
            }

            unService = new Service();
            request.setAttribute("myEntities", unService.consulterListeOeuvresCRUD());
            request.setAttribute("title", "WorksOfArtList");
            request.setAttribute("contentTitle", "Works Of Art List");

            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "{id}/edit.htm")
    public ModelAndView editWorkOfArt(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Service unService = new Service();
            request.setAttribute("monOeuvre", unService.consulterOeuvre(String.valueOf(id)));
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaire());
            request.setAttribute("edit", true);
            destinationPage = "actionOwner";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/update.htm")
    public ModelAndView updateWorkOfArt(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Oeuvrevente uneOeuvre = new Oeuvrevente();
            uneOeuvre.setTitreOeuvrevente(request.getParameter("titre"));
            uneOeuvre.getProprietaire().setNomProprietaire(request.getParameter("nomproprio"));
            uneOeuvre.setPrixOeuvrevente(Integer.parseInt(request.getParameter("prix")));

            Service unService = new Service();

            unService.editOeuvre(uneOeuvre, String.valueOf(id));

            request.setAttribute("flashMessage_success", "The Work of art '" + uneOeuvre.getTitreOeuvrevente() + "' has been modified successfully");

            request.setAttribute("myEntities", unService.consulterListeOeuvresCRUD());
            request.setAttribute("title", "WorksOfArtList");
            request.setAttribute("contentTitle", "Works Of Art List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    private void proprietairesComboBox(HttpServletRequest request){
        try {
            Service unService = new Service();
            request.setAttribute("mesProprietaires", unService.consulterListeProprietaire());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
