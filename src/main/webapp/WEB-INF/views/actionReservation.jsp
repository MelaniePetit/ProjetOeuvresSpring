<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 25/02/2017
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:form title="${edit ? 'EditReservation' : 'AddReservation'}" contentTitle="${edit ? 'Edit Reservation' : 'Add Reservation'}" typeOfEntity="reservation" method="post" edit="${edit ? 'true' : ''}">
    <jsp:attribute name="form_tag">

        <c:if test="${edit}">
            <t:input type="number" name="txtid" value="${maReservation.id}" id="id" placeholder="Id" onblur="verifId(this)" readonly="readonly" label="Id"/>
        </c:if>

        <div class="form-group flex-center-center">
           <label class="control-label col-sm-2" >Work of art available</label>
           <div class="col-sm-5">
               <select class="form-control " name="txttitre" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')" ${edit ? ' disabled' : ''}>
                    <c:forEach items="${mesOeuvres}" var="item">
                        <option ${edit ? (item.id == maReservation.workOfArt.id ? 'selected="selected"' : '') : ''}>${item.title}</option>
                    </c:forEach>
               </select>
           </div>
        </div>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Date of reservation</label>
            <div class="col-sm-5">
                <div class="input-group date">
                    <input value="${maReservation.date}" id="date" name="txtdate" type="text" class="form-control" required><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
            </div>
        </div>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Adhérent</label>
            <div class="col-sm-5">
                <select class="form-control " name="txtadherent" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')" ${edit ? ' disabled' : ''}>
                    <c:forEach items="${mesAdherents}" var="item">
                        <option ${edit ? (item.id == maReservation.member.id ? 'selected="selected"' : '') : ''}>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="text/javascript" src="/resources/lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
        <script>
            $('.input-group.date').datepicker({
                format: "yyyy-mm-dd",
                todayHighlight: true
            });
        </script>
    </jsp:attribute>

</t:form>