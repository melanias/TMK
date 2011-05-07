<%@ include file="../index/adminHeader.jsp" %>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty rootList}">
                <table>
                    <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Celular</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${rootList}" var="root">
                    <tr>
                        <td><a href="<c:url value="/admin/root/${root.id}"/>">${root.cpf}</a></td>
                        <td>${root.nome}</td>
                        <td>${root.email}</td>
                        <td><c:if test="${empty root.telefone}">-</c:if>${root.telefone}</td>
                        <td><c:if test="${empty root.celular}">-</c:if>${root.celular}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhum administrador cadastrado até o momento.</p>
            </c:otherwise>
        </c:choose>
<%@ include file="../index/adminFooter.jsp" %>