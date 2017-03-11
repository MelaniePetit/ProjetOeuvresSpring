<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@tag description="Base Template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="title"%>
<%@attribute name="stylesheet_tag" fragment="true" %>
<%@attribute name="body_tag" fragment="true" %>
<%@attribute name="javascripts_tag" fragment="true" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <%--<meta http-equiv="refresh" content="0;URL=javascript:fermer();">--%>
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Leckerli+One" rel="stylesheet">
        <link rel="stylesheet"  href="<c:url value="/resources/lib/bootstrap/css/bootstrap.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/resources/lib/font-awesome-4.7.0/css/font-awesome.css"/>"/>
        <jsp:invoke fragment="stylesheet_tag"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
        <title>${title}</title>
        <script type="application/javascript" src="<c:url value="/resources/lib/jquery/jquery-1.11.2.min.js"/>"></script>
    </head>

    <body>
        <jsp:invoke fragment="body_tag"/>
        <script type="application/javascript" src="<c:url value="/resources/js/formControleur.js"/>"></script>
        <script type="application/javascript" src="<c:url value="/resources/lib/bootstrap/js/bootstrap.js"/>"></script>
        <script type="application/javascript" src="<c:url value="/resources/js/menu.js"/>"></script>
        <jsp:invoke fragment="javascripts_tag"/>
    </body>

</html>