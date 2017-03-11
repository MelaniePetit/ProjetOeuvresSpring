<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:form title="${edit ? 'EditMember' : 'AddMember'}" contentTitle="${edit ? 'Edit Member' : 'Add Member'}" typeOfEntity="member" method="post" edit="${edit ? 'true' : 'false'}" test="verifFormAdherent(this)">
    <jsp:attribute name="form_tag">
        <c:if test="${edit}">
            <t:input value="${edit ? monAdherent.id : ''}" type="number" name="id" id="id" placeholder="Id" onblur="verifId(this)" readonly="readonly" label="Id"/>
        </c:if>
        <t:input value="${edit ? monAdherent.name : ''}" type="text" name="nom" id="nom" placeholder="LastName" onblur="verifNom(this)" required="required" label="Last name"/>
        <t:input value="${edit ? monAdherent.firstName : ''}" type="text" name="prenom" id="prenom" placeholder="Firstname" onblur="verifPrenom(this)" required="required" label="First name"/>
        <t:input value="${edit ? monAdherent.city : ''}" type="text" name="ville" id="ville" placeholder="City" onblur="verifVille(this)" required="required" label="City"/>
    </jsp:attribute>
</t:form>
