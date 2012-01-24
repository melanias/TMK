<%@ include file="../index/adminHeader.jsp" %>
                <span>
                    <a class="add-form" href="${linkTo[FuncionarioController].frmAdd}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Cadastrar Funcionário'});">Cadastrar</a>
                    <a class="pdf" href="${linkTo[FuncionarioController].pdf}" title="PDF">PDF</a>
                </span>
                <h1>${title}</h1>
<c:choose>
    <c:when test="${not empty funcionarioList}">
                <table>
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Perfil</th>
                            <th>Unidade</th>
                            <th>Setor</th>
                            <th>Situação</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${funcionarioList}" var="f">
                    <tr>
                        <td>${f.firstAndLastName}</td>
                        <td width="10%">${f.perfil.userRole}</td>
                        <td><c:if test="${empty f.unidade}">-</c:if>${f.unidade.razaoSocial}<c:if test="${f.unidade.matriz == true}"> - Matriz</c:if></td>
                        <td><c:if test="${empty f.setor}">-</c:if>${f.setor.sigla}</td>
                        <td width="10%">${f.status.status}</td>
                        <td width="53">
                            <a class="preview" href="${linkTo[FuncionarioController].view[f.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'680', headingText:'Funcionário'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[FuncionarioController].frmEdit[f.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'680', headingText:'Editar Funcionário'});">Editar</a>
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
<%@ include file="../index/adminFooter.jsp" %>