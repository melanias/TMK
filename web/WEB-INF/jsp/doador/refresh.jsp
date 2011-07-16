<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <span>
                    <a class="add-form" href="<c:url value="/doador/add" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Cadastrar Doador'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty doadorList}">
                <table>
                    <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Celular</th>
                            <th>Data de Cadastro</th>
                            <th>Situa��o</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${doadorList}" var="d">
                    <tr>
                        <td width="10%">${d.cpf}</td>
                        <td>${d.firstAndLastName}</td>
                        <td>${d.email}</td>
                        <td width="10%"><c:if test="${empty d.telefone}">-</c:if>${d.telefone}</td>
                        <td width="10%"><c:if test="${empty d.celular}">-</c:if>${d.celular}</td>
                        <td width="12%"><fmt:formatDate pattern="dd/MM/yyyy" value="${d.data}" /></td>
                        <td width="10%">${d.status.status}</td>
                        <td width="53">
                            <a class="preview" href="<c:url value="/doador/view/${d.id}" />" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'680', headingText:'Doador'});">Visualizar</a>
                            <a class="edit-form" href="<c:url value="/doador/edit/${d.id}" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Editar Doador'});">Editar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhum doador cadastrado at� o momento.</p>
            </c:otherwise>
</c:choose>