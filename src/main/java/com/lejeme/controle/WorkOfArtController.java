package com.lejeme.controle;

import com.lejeme.dao.OwnerService;
import com.lejeme.dao.WorkOfArtService;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.WorkOfArt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/worksofart/")
public class WorkOfArtController {
    private String destinationPage = "";

    @RequestMapping(value = "list.htm")
    public ModelAndView listWorkOfArt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            WorkOfArtService workOfArtService = new WorkOfArtService();
            request.setAttribute("myEntities", workOfArtService.getListCRUD());
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
            ownersComboBox(request);
            destinationPage = "actionWorkOfArt";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insert.htm")
    public ModelAndView insertWorkOfArt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            WorkOfArt workOfArt = new WorkOfArt();
            workOfArt.setTitle(request.getParameter("titre"));
            workOfArt.setPrice(Integer.parseInt(request.getParameter("prix")));
            workOfArt.getOwner().setName(request.getParameter("nomproprio"));

            WorkOfArtService workOfArtService = new WorkOfArtService();
            workOfArtService.insert(workOfArt);

            request.setAttribute("flashMessage_success", "The Work of art '" + workOfArt.getTitle() + "' has been added successfully");

            request.setAttribute("myEntities", workOfArtService.getListCRUD());
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
            WorkOfArtService workOfArtService = new WorkOfArtService();
            if(!workOfArtService.isReserved(Integer.parseInt(String.valueOf(id))))
            {
                workOfArtService.remove(id);
                request.setAttribute("flashMessage_success", "The Work of art has been successfully removed");
            }
            else
            {
                request.setAttribute("flashMessage_error", "The Work of art is Reserved, delete the reservation first");
            }

            request.setAttribute("myEntities", workOfArtService.getListCRUD());
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
            WorkOfArtService workOfArtService = new WorkOfArtService();
            OwnerService ownerService = new OwnerService();

            request.setAttribute("monOeuvre", workOfArtService.get(id));
            request.setAttribute("mesProprietaires", ownerService.getList());
            request.setAttribute("edit", true);
            destinationPage = "actionWorkOfArt";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/update.htm")
    public ModelAndView updateWorkOfArt(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            WorkOfArt workOfArt = new WorkOfArt();
            workOfArt.setTitle(request.getParameter("titre"));
            workOfArt.getOwner().setName(request.getParameter("nomproprio"));
            workOfArt.setPrice(Integer.parseInt(request.getParameter("prix")));

            WorkOfArtService workOfArtService = new WorkOfArtService();

            workOfArtService.edit(workOfArt, id);

            request.setAttribute("flashMessage_success", "The Work of art '" + workOfArt.getTitle() + "' has been modified successfully");

            request.setAttribute("myEntities", workOfArtService.getListCRUD());
            request.setAttribute("title", "WorksOfArtList");
            request.setAttribute("contentTitle", "Works Of Art List");
            destinationPage = "list";

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    private void ownersComboBox(HttpServletRequest request){
        try {
            OwnerService ownerService = new OwnerService();
            request.setAttribute("mesProprietaires", ownerService.getList());
        } catch (MonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
