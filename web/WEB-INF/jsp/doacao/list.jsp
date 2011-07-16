<%@ include file="../index/header.jsp" %>
                <span>
                    <a class="add-form" href="<c:url value="/doacao/add" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Cadastrar Doa��o'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty doacaoList}">
                <table>
                    <thead>
                        <tr>
                            <th>Data da Liga��o</th>
                            <th>Operador</th>
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
                        <td>${d.operador.firstAndLastName}</td>
                        <td>${d.doador.firstAndLastName}</td>
                        <td><fmt:formatNumber type="currency" value="${d.valor}" minFractionDigits="2" maxFractionDigits="2" /></td>
                        <td width="14%"><fmt:formatDate pattern="dd/MM/yyyy" value="${d.recebimento}" /></td>
                        <td>${d.representante.firstAndLastName}</td>
                        <td width="53" >
                            <a class="preview" href="<c:url value="/doacao/view/${d.id}" />" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Doa��o'});">Visualizar</a>
                            <a class="edit-form" href="<c:url value="/doacao/edit/${d.id}" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Editar Doa��o'});">Editar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhuma doa��o cadastrada at� o momento.</p>
            </c:otherwise>
</c:choose>
<%@ include file="../index/footer.jsp" %>