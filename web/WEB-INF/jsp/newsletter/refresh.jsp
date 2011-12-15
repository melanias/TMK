<%@ include file="../index/adminHeader.jsp" %>
                <span>
                    <a class="add-form" href="${linkTo[NewsletterController].frmAdd}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Cadastrar Setor'});">Cadastrar</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty newsletterList}">
                <table>
                    <thead>
                        <tr>
                            <th>Servidor SMTP</th>
                            <th>Porta SMTP</th>
                            <th>Conta</th>
                            <th>Senha</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${newsletterList}" var="n">
                    <tr>
                        <td>${n.hostname}</td>
                        <td><c:if test="${empty n.smtpport}">-</c:if>${n.smtpport}</td>
                        <td width="10%"><c:if test="${empty n.account}">-</c:if>${n.account}</td>
                        <td width="10%"><c:if test="${empty n.password}">-</c:if>${n.password}</td>
                        <td width="53" >
                            <a class="preview" href="${linkTo[newsletterController].view[s.id]}" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Setor'});">Visualizar</a>
                            <a class="edit-form" href="${linkTo[newsletterController].frmEdit[s.id]}" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'384', headingText:'Editar Setor'});">Editar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhuma conta cadastrada até o momento.</p>
            </c:otherwise>
</c:choose>
<%@ include file="../index/adminFooter.jsp" %>