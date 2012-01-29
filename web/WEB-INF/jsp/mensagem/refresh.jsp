<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <span>
                    <a class="add-form" href="${linkTo[MensagemController].add}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'640', height:'280', headingText:'Enviar Mensagem'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
    <c:when test="${not empty mensagemList}">
                <table>
                    <thead>
                        <tr>
                            <th>Data de Envio</th>
                            <th>Tipo de Envio</th>
                            <th>Texto</th>
                            <th>Funcionário</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${mensagemList}" var="m">
                    <tr>
                        <td width="10%"><fmt:formatDate pattern="dd/MM/yyyy" value="${m.data}" /></td>
                        <td width="10%">${m.tipo.type}</td>
                        <td>${m.texto}</td>
                        <td>${m.funcionario.firstAndLastName}</td>
                        <td width="15">
                            <a class="preview" href="${linkTo[MensagemController].view[m.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Mensagem'});">Visualizar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <p>Nenhuma mensagem cadastrada até o momento.</p>
    </c:otherwise>
</c:choose>