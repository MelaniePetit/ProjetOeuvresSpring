<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="form_tag" fragment="true" %>
<%@attribute name="javascripts_tag" fragment="true" %>
<%@attribute name="contentTitle"%>
<%@attribute name="title"%>
<%--<%@attribute name="action"%>--%>
<%@attribute name="edit"%>
<%@attribute name="method"%>
<%@attribute name="typeOfEntity"%>
<%@ attribute name="test" %>

<t:layout title="${title}" contentTitle="${contentTitle}">

    <jsp:attribute name="stylesheet_tag">
        <link rel="stylesheet" href="<c:url value="/resources/lib/bootstrap-datepicker/css/bootstrap-datepicker.min.css"/>"/>
    </jsp:attribute>

    <jsp:attribute name="content_tag">
        <div class="form">
            <div class="alert alert-danger" id="erreur" style="display: none">
                <button type="button" class="close">x</button>
                <p>Please, fill in all the fields correctly</p>
            </div>

            <div class="well">
                <form  class="form-horizontal" name='identification' method="${method}" action="${edit ? 'update.htm' : 'insert.htm'}" onsubmit="return ${test}">
                    <jsp:invoke fragment="form_tag"/>

                    <!-- Boutons Ajouter -->
                    <div class="form-group flex-center-center">
                        <button type="submit" class="btn btn-info">${edit=='true' ? "Edit " : "Add "}${typeOfEntity}</button>
                        <a class="btn btn-default" href="/index.htm">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <jsp:invoke fragment="javascripts_tag"/>
    </jsp:attribute>

</t:layout>
