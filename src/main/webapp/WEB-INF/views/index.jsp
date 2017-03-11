<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:base_layout title="ArtZone | Home">

    <jsp:attribute name="body_tag">
        <div id="fullScreen" class="flex-center-center-wrap">
            <h2 class="menu-label text-center"></h2>
            <img src="<c:url value='/resources/images/ArtZone.png'/>" class="col-xs-12 col-sm-8 col-md-6 col-lg-5">
            <jsp:include page="/WEB-INF/views/includes/circle_menu_layout.jsp"/>
        </div>
        <div id="overlay"></div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="application/javascript" src="<c:url value='/resources/js/circle-menu.js'/>"></script>
    </jsp:attribute>

</t:base_layout>

