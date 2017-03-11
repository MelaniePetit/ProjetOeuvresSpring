<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 25/02/2017
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Erreur" contentTitle="Error 404">
    <jsp:attribute name="content_tag">
        <div class="balloon-container">
            <div class="balloons">
                <div class="balloon"><span>!</span></div>
                <div class="balloon"><span>404</span></div>
            </div>
        </div>
        <div class="flex-center-center">
            <img src="images/404.png" class="col-xs-12 col-sm-7 col-md-5 col-lg-4" <%--style="display: block; margin-left: auto; margin-right: auto; max-width: 100%; height: auto;"--%>>
        </div>
        <div class="flex-center-center">
            <h4 class="text-center">The page you try to attempt does not exist !</h4>
        </div>
    </jsp:attribute>
</t:layout>
