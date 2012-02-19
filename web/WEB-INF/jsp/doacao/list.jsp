<%@ include file="../index/header.jsp" %>
                <span>
                    <a class="add-form" href="${linkTo[DoacaoController].frmAdd}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'450', headingText:'Cadastrar Doação'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty doacaoList}">
                <table>
                    <thead>
                        <tr>
                            <th>Data da Ligação</th>
                            <th>Funcionário</th>
                            <th>Doador</th>
                            <th>Valor</th>
                            <th>Data de Recebimento</th>
                            <th>Representante</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${doacaoList}" var="d">
                    <tr>
                        <td width="11%"><fmt:formatDate pattern="dd/MM/yyyy" value="${d.ligacao}" /></td>
                        <td>${d.funcionario.firstAndLastName}</td>
                        <td>${d.doador.firstAndLastName}</td>
                        <td><fmt:formatNumber type="currency" value="${d.valor}" minFractionDigits="2" maxFractionDigits="2" /></td>
                        <td width="14%"><fmt:formatDate pattern="dd/MM/yyyy" value="${d.recebimento}" /></td>
                        <td>${d.representante.firstAndLastName}</td>
                        <td width="81">
                            <a class="preview" href="${linkTo[DoacaoController].view[d.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Doação'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[DoacaoController].frmEdit[d.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'450', headingText:'Editar Doação'});">Editar</a>
                            <a class="delete" href="javascript:;" rel="${linkTo[DoacaoController].delete[d.id]}">Excluir</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhuma doação cadastrada até o momento.</p>
            </c:otherwise>
</c:choose>
<%@ include file="../index/footer.jsp" %>