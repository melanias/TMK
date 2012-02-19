<%@ include file="../index/header.jsp" %>
                <span>
                    <a class="add-form" href="${linkTo[CampanhaController].add}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'550', headingText:'Cadastrar Campanha'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
    <c:when test="${not empty campanhaList}">
                <table>
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Data Inicial</th>
                            <th>Data Final</th>
                            <th>Data de Cadastro</th>
                            <th>Tipo</th>
                            <th>Situação</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${campanhaList}" var="c">
                    <tr>
                        <td>${c.nome}</td>
                        <td width="10%"><fmt:formatDate pattern="dd/MM/yyyy" value="${c.dataInicial}" /></td>
                        <td width="10%"><fmt:formatDate pattern="dd/MM/yyyy" value="${c.dataFinal}" /></td>
                        <td width="12%"><fmt:formatDate pattern="dd/MM/yyyy" value="${c.data}" /></td>
                        <td width="10%">${c.type.type}</td>
                        <td width="10%">${c.status.status}</td>
                        <td width="81">
                            <a class="preview" href="${linkTo[CampanhaController].view[c.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Campanha'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[CampanhaController].frmEdit[c.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'550', headingText:'Editar Campanha'});">Editar</a>
                            <a class="delete" href="javascript:;" rel="${linkTo[CampanhaController].delete[c.id]}">Excluir</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
    </c:when>
    <c:otherwise>
                <p>Nenhuma campanha cadastrada até o momento.</p>
    </c:otherwise>
</c:choose>
<%@ include file="../index/footer.jsp" %>