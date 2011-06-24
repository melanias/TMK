<%@ include file="../index/adminHeader.jsp" %>
                <h1>${title}</h1>
<c:if test="${not empty errors}">
                <blockquote>
    <c:forEach items="${errors}" var="error">
                    <p>${error.message}</p>
    </c:forEach>
                </blockquote>
</c:if>
                <form action="<c:url value="/admin/user/novo" />" method="post">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td><label for="cpf">CPF:</label></td>
                            <td><input type="text" name="user.cpf" value="${user.cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>
                        </tr>
                        <tr>
                            <td><label for="nome">Nome:</label></td>
                            <td><input type="text" name="user.nome" value="${user.nome}" id="nome" size="50" maxlength="200" /></td>
                        </tr>
                        <tr>
                            <td><label for="email">E-mail:</label></td>
                            <td><input type="text" name="user.email" value="${user.email}" id="email" size="35" maxlength="100" /></td>
                        </tr>
                        <tr>
                            <td><label for="telefone">Telefone:</label></td>
                            <td><input type="text" name="user.telefone" value="${user.telefone}" id="telefone" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="celular">Celular:</label></td>
                            <td><input type="text" name="user.celular" value="${user.celular}" id="celular" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="senha">Senha:</label></td>
                            <td><input type="password" name="user.senha" value="" id="senha" size="14" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
<%@ include file="../index/adminFooter.jsp" %>