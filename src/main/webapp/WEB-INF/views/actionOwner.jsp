<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:form title="${edit ? 'EditOwner' : 'AddOwner'}" contentTitle="${edit ? 'Edit Owner' : 'Add Owner'}" typeOfEntity="member" method="post" edit="${edit ? 'true' : 'false'}" test="verifFormOwner(this)">
    <jsp:attribute name="form_tag">
        <c:if test="${edit}">
            <t:input value="${edit ? monProprio.idProprietaire : ''}" type="number" name="id" id="id" placeholder="Id"  readonly="readonly" label="Id"/>
        </c:if>
        <t:input value="${edit ? monProprio.nomProprietaire : ''}"
                 type="text" name="nom"
                 id="nom"
                 placeholder="LastName"
                 onblur="verifNom(this)"
                 required="required"
                 label="Last name"/>
        <t:input value="${edit ? monProprio.prenomProprietaire : ''}"
                 type="text"
                 name="prenom"
                 id="prenom"
                 placeholder="Firstname"
                 onblur="verifPrenom(this)"
                 required="required"
                 label="First name"/>
    </jsp:attribute>
</t:form>
