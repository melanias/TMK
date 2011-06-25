<%@ include file="../index/header.jsp" %>
                <span>
                    <a class="add-form" href="<c:url value="/funcionario/add" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Cadastrar Funcionário'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty funcionarioList}">
                <table>
                    <thead>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                            <th>Perfil</th>
                            <th>Data de Admissão</th>
                            <th>Data de Demissão</th>
                            <th>Filial</th>
                            <th>Setor</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${funcionarioList}" var="f">
                    <tr>
                        <td width="10%">${f.cpf}</td>
                        <td>${f.firstAndLastName}</td>
                        <td width="10%">${f.perfil.userRole}</td>
                        <td width="11%"><fmt:formatDate pattern="dd/MM/yyyy" value="${f.admissao}" /></td>
                        <td width="11%"><fmt:formatDate pattern="dd/MM/yyyy" value="${f.demissao}" /></td>
                        <td>${f.filial.nome}</td>
                        <td>${f.setor.nome}</td>
                        <td width="53" >
                            <a class="preview" href="<c:url value="/funcionario/view/${f.id}" />" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'680', headingText:'Funcionário'});">Visualizar</a>
                            <a class="edit-form" href="<c:url value="/funcionario/edit/${f.id}" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Editar Funcionário'});">Editar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhum funcionário cadastrado até o momento.</p>
            </c:otherwise>
</c:choose>
<%@ include file="../index/footer.jsp" %>