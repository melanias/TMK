<%@ include file="../index/adminHeader.jsp" %>
                <span>
                    <a class="add-form" href="${linkTo[UnidadeController].frmAdd}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'640', headingText:'Cadastrar Unidade'});">Cadastrar</a>
                    <a class="pdf" href="${linkTo[UnidadeController].pdf}" title="PDF">PDF</a>
                </span>
                <h1>${title}</h1>
<c:choose>
    <c:when test="${not empty unidadeList}">
                <table>
                    <thead>
                        <tr>
                            <th>CNPJ</th>
                            <th>Razão Social</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Fax</th>
                            <th>Data de Cadastro</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${unidadeList}" var="u">
                        <tr>
                            <td width="12%">${u.cnpj}</td>
                            <td>${u.razaoSocial}<c:if test="${u.matriz == true}"> - Matriz</c:if></td>
                            <td><c:if test="${empty u.email}">-</c:if>${u.email}</td>
                            <td width="10%"><c:if test="${empty u.telefone}">-</c:if>${u.telefone}</td>
                            <td width="10%"><c:if test="${empty u.fax}">-</c:if>${u.fax}</td>
                            <td width="12%"><fmt:formatDate pattern="dd/MM/yyyy" value="${u.data}" /></td>
                            <td width="81">
                                <a class="preview" href="${linkTo[UnidadeController].view[u.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Unidade'});">Visualizar</a>
                                <a class="edit-form" href="${linkTo[UnidadeController].frmEdit[u.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'640', headingText:'Editar Unidade'});">Editar</a>
                                <a class="delete" href="javascript:;" rel="${linkTo[UnidadeController].delete[u.id]}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <p>Nenhuma unidade cadastrada até o momento.</p>
    </c:otherwise>
</c:choose>
<%@ include file="../index/adminFooter.jsp" %>