<%@ include file="../index/adminHeader.jsp" %>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty userList}">
                <table>
                    <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                            <th>Login</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Celular</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user">
                    <tr>
                        <td><a href="<c:url value="/admin/user/${user.id}"/>">${user.cpf}</a></td>
                        <td>${user.nome}</td>
                        <td>${user.login}</td>
                        <td>${user.email}</td>
                        <td><c:if test="${empty user.telefone}">-</c:if>${user.telefone}</td>
                        <td><c:if test="${empty user.celular}">-</c:if>${user.celular}</td>
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