package com.lejeme.controle;

import com.lejeme.dao.MemberService;
import com.lejeme.meserreurs.MonException;
import com.lejeme.metier.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
            MemberService memberService = new MemberService();
            request.setAttribute("myEntities", memberService.getListCRUD());
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
            Member member = new Member();
            member.setName(request.getParameter("nom"));
            member.setFirstName(request.getParameter("prenom"));
            member.setCity(request.getParameter("ville"));

            MemberService memberService = new MemberService();
            memberService.insert(member);

            request.setAttribute("myEntities", memberService.getListCRUD());
            request.setAttribute("title", "MembersList");
            request.setAttribute("contentTitle", "Members List");
            destinationPage = "list";

            request.setAttribute("flashMessage_success", "The Member " + member.getFirstName() + " " + member.getName().toUpperCase() + " has been added successfully");
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "{id}/remove.htm")
    public ModelAndView removeMember(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MemberService memberService = new MemberService();
            memberService.remove(id);

            request.setAttribute("flashMessage_success", "The Member has been successfully removed");

            request.setAttribute("myEntities", memberService.getListCRUD());
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
            MemberService memberService = new MemberService();
            request.setAttribute("monAdherent", memberService.get(id));
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
            Member member = new Member();
            member.setName(request.getParameter("nom"));
            member.setFirstName(request.getParameter("prenom"));
            member.setCity(request.getParameter("ville"));

            MemberService memberService = new MemberService();
            memberService.edit(member, id);

            request.setAttribute("flashMessage_success", "The Member " + member.getFirstName() + " " + member.getName().toUpperCase() + " has been modified successfully");

            request.setAttribute("myEntities", memberService.getListCRUD());
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
