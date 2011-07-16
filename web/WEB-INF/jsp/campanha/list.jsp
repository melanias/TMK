<%@ include file="../index/header.jsp" %>
                <span>
                    <a class="add-form" href="<c:url value="/campanha/add" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Cadastrar Campanha'});">Cadastrar</a>
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
                            <th>Cadastrada por</th>
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
                        <td>${c.funcionario.firstAndLastName}</td>
                        <td width="53" >
                            <a class="preview" href="<c:url value="/campanha/view/${c.id}" />" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Campanha'});">Visualizar</a>
                            <a class="edit-form" href="<c:url value="/campanha/edit/${c.id}" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Editar Campanha'});">Editar</a>
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