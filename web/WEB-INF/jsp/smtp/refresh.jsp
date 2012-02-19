<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                <span>
                    <a class="add-form" href="${linkTo[SmtpController].frmAdd}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'400', headingText:'Cadastrar SMTP'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty smtpList}">
                <table>
                    <thead>
                        <tr>
                            <th>Host</th>
                            <th>Porta</th>
                            <th>Conta</th>
                            <th>TLS</th>
                            <th>Situação</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${smtpList}" var="smtp">
                    <tr>
                        <td>${smtp.hostName}</td>
                        <td width="6%">${smtp.port}</td>
                        <td>${smtp.account}</td>
                        <td>
                            <c:choose>
                                <c:when test="${smtp.tls == true}">Sim</c:when>
                                <c:otherwise>Não</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${smtp.status.status}</td>
                        <td width="81">
                            <a class="preview" href="${linkTo[SmtpController].view[smtp.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'400', headingText:'SMTP'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[SmtpController].frmEdit[smtp.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'400', headingText:'Editar SMTP'});">Editar</a>
                            <a class="delete" href="javascript:;" rel="${linkTo[SmtpController].delete[smtp.id]}">Excluir</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhum servidor cadastrado até o momento.</p>
            </c:otherwise>
</c:choose>