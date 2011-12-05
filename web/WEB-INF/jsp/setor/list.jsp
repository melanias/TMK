<%@ include file="../index/adminHeader.jsp" %>
                <span>
                    <a class="add-form" href="${linkTo[SetorController].frmAdd}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Cadastrar Setor'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty setorList}">
                <table>
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Fax</th>
                            <th>Responsável</th>
                            <th>Filial</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${setorList}" var="s">
                    <tr>
                        <td>${s.nome}</td>
                        <td><c:if test="${empty s.email}">-</c:if>${s.email}</td>
                        <td width="10%"><c:if test="${empty s.telefone}">-</c:if>${s.telefone}</td>
                        <td width="10%"><c:if test="${empty s.fax}">-</c:if>${s.fax}</td>
                        <td><c:if test="${empty s.responsavel.firstAndLastName}">-</c:if>${s.responsavel.firstAndLastName}</td>
                        <td>${s.filial.nome}</td>
                        <td width="53" >
                            <a class="preview" href="${linkTo[SetorController].view[s.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Setor'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[SetorController].frmEdit[s.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Editar Setor'});">Editar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhum setor cadastrado até o momento.</p>
            </c:otherwise>
</c:choose>
<%@ include file="../index/adminFooter.jsp" %>