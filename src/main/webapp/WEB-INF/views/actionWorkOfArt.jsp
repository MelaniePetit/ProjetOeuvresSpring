<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<t:form title="${edit ? 'EditWorkOfArt' : 'AddWorkOfArt'}" contentTitle="${edit ? 'Edit Work Of Art' : 'Add Work Of Art'}" typeOfEntity="work of art" method="post" edit="${edit ? 'true' : 'false'}" test="verifFormOeuvre(this)">
    <jsp:attribute name="form_tag">
        <c:if test="${edit}">
            <t:input type="number" name="id" value="${monOeuvre.idOeuvrevente}" id="id" placeholder="Id" onblur="verifId(this)" readonly="readonly" label="Id"/>
        </c:if>
        <t:input type="text" name="titre" value="${edit ? monOeuvre.titreOeuvrevente : ''}" id="titre" placeholder="Title" onblur="verifTitre(this)" required="required" label="Title"/>
        <t:input type="number" name="prix" value="${edit ? monOeuvre.prixOeuvrevente : ''}" id="prix" placeholder="Price" onblur="verifPrix(this)" required="required" label="Price"/>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Owner</label>
            <div class="col-sm-5">
                <select class="form-control " name="nomproprio" onChange="combo(this, 'theinput')" >
                    <c:forEach items="${mesProprietaires}" var="item">
                        <option ${edit ? (item.nomProprietaire == monOeuvre.proprietaire.nomProprietaire ? 'selected="selected"' : '') : ''}>${item.nomProprietaire}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </jsp:attribute>
</t:form>