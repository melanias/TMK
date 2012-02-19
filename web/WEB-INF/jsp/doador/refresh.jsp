<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <span>
                    <a class="add-form" href="${linkTo[DoadorController].add}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Cadastrar Doador'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
    <c:when test="${not empty doadorList}">
                <table>
                    <thead>
                        <tr>
                            <th>CPF/CNPJ</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Celular</th>
                            <th>Data de Cadastro</th>
                            <th>Situação</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${doadorList}" var="d">
                    <tr>
                        <td width="12%"><c:if test="${empty d.cpf && empty d.cnpj}">${d.tipo.type}</c:if>${d.cpf}${d.cnpj}</td>
                        <td>${d.firstAndLastName}</td>
                        <td><c:if test="${empty d.email}">-</c:if>${d.email}</td>
                        <td width="10%"><c:if test="${empty d.telefone}">-</c:if>${d.telefone}</td>
                        <td width="10%"><c:if test="${empty d.celular}">-</c:if>${d.celular}</td>
                        <td width="12%"><fmt:formatDate pattern="dd/MM/yyyy" value="${d.data}" /></td>
                        <td width="10%">${d.status.status}</td>
                        <td width="81">
                            <a class="preview" href="${linkTo[DoadorController].view[d.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'680', headingText:'Doador'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[DoadorController].frmEdit[d.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Editar Doador'});">Editar</a>
                            <a class="delete" href="javascript:;" rel="${linkTo[DoadorController].delete[d.id]}">Excluir</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <p>Nenhum doador cadastrado até o momento.</p>
    </c:otherwise>
</c:choose>