<%@ include file="../index/adminHeader.jsp" %>
                <h1>${title}</h1>
<c:if test="${not empty errors}">
                <blockquote>
    <c:forEach items="${errors}" var="error">
                    <p>${error.message}</p>
    </c:forEach>
                </blockquote>
</c:if>
                <form action="<c:url value="/admin/root/${root.id}" />" method="post">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td><label for="cpf">CPF:</label></td>
                            <td><input type="text" name="root.cpf" value="${root.cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>
                        </tr>
                        <tr>
                            <td><label for="nome">Nome:</label></td>
                            <td><input type="text" name="root.nome" value="${root.nome}" id="nome" size="50" maxlength="200" /></td>
                        </tr>
                        <tr>
                            <td><label for="email">E-mail:</label></td>
                            <td><input type="text" name="root.email" value="${root.email}" id="email" size="35" maxlength="100" /></td>
                        </tr>
                        <tr>
                            <td><label for="telefone">Telefone:</label></td>
                            <td><input type="text" name="root.telefone" value="${root.telefone}" id="telefone" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="celular">Celular:</label></td>
                            <td><input type="text" name="root.celular" value="${root.celular}" id="celular" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <!--
                        <tr>
                            <td><label for="senha">Senha:</label></td>
                            <td><input type="password" name="root.senha" value="" id="senha" size="14" /></td>
                        </tr>
                        -->
                        <tr>
                            <td></td>
                            <td><button type="submit" name="_method" value="PUT">Salvar</button></td>
                        </tr>
                    </table>
                </form>
<%@ include file="../index/adminFooter.jsp" %>