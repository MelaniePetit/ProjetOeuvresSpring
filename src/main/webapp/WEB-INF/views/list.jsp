<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="${title}" contentTitle="${contentTitle}">
    <jsp:attribute name="stylesheet_tag">
        <link rel="stylesheet"  href="webjars/datatables/1.10.13/css/dataTables.bootstrap.min.css"/>
    </jsp:attribute>

    <jsp:attribute name="content_tag">
        <c:choose>
            <c:when test="${myEntities.resultsSize() > 0}">
                <t:table entities="${myEntities}"/>
            </c:when>
            <c:otherwise>
                <h4 class="text-center"> No data available </h4>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="application/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
        <script type="application/javascript" src="webjars/datatables/1.10.13/js/dataTables.bootstrap.min.js"></script>
        <script>
            $(document).ready(function() {
                $('#DataTable').DataTable(
                    {
                        columnDefs: [
                            { orderable: false, targets: -1 }
                        ]
                    }
                );
            } );
        </script>
    </jsp:attribute>
</t:layout>
