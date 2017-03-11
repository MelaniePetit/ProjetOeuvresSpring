<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="entities" type="com.lejeme.metier.CRUD.AbstractCRUDForm" %>

<table id="DataTable" class="table-custom table table-bordered table-hover data-table">
    <thead>
        <tr>
            <c:forEach var="col" items="${entities.getColumns()}">
                <th><c:out value="${col}"/></th>
            </c:forEach>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="row" begin="0" end="${entities.resultsSize() - 1}">
            <tr>
                <c:forEach var="column" begin="0" end="${entities.getNumberOfFields() - 1}">
                    <td>${entities.getDataAt(row, column)}</td>
                </c:forEach>

                <td>
                    <a href="${entities.getId(row)}/${entities.getEditController()}" type="button" class="btn btn-primary "><i class="fa fa-pencil"></i></a>
                    <a type="button" class="btn btn-danger" data-toggle="modal" data-target=".deleteModal_${entities.getId(row)}"><i class="fa fa-times"></i></a>

                        <%--Delete Modal--%>
                    <div class="modal fade deleteModal_${entities.getId(row)}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">Deleting ${entities.getTypeName()}...</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Please note, this will <strong>permanently</strong> remove the work of art!
                                        The effect is irremediable. Are you sure to perform this action? </p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <a href="/${entities.getTypeName()}/${entities.getId(row)}/${entities.getDeleteController()}">
                                        <button type="button" class="btn btn-danger">Delete</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--End Of Modal--%>
                </td>

            </tr>
        </c:forEach>



    </tbody>
</table>