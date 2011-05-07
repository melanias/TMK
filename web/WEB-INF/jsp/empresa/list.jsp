<%@ include file="../index/adminHeader.jsp" %>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty empresaList}">
                <table>
                    <thead>
                        <tr>
                            <th>CNPJ</th>
                            <th>Raz�o Social</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Fax</th>
                            <th>Data de Cadastro</th>
                            <th>Situa��o</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${empresaList}" var="e">
                    <tr>
                        <td><a href="<c:url value="/admin/empresa/${e.id}"/>">${e.cnpj}</a></td>
                        <td>${e.razaoSocial}</td>
                        <td>${e.email}</td>
                        <td width="10%">${e.telefone}</td>
                        <td width="10%"><c:if test="${empty e.fax}">-</c:if>${e.fax}</td>
                        <td width="12%"><fmt:formatDate pattern="dd/MM/yyyy" value="${e.data}"/></td>
                        <td>${e.status.status}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhuma empresa cadastrada at� o momento.</p>
            </c:otherwise>
        </c:choose>
<%@ include file="../index/adminFooter.jsp" %>