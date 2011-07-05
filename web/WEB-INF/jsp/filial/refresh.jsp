<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <span>
                    <a class="add-form" href="<c:url value="/filial/add" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'640', headingText:'Cadastrar Filial'});">Cadastrar</a>
                    <a class="pdf" href="<c:url value="/filial/pdf" />" title="PDF">PDF</a>
                </span>
                <h1>${title}</h1>
<c:choose>
            <c:when test="${not empty filialList}">
                <table>
                    <thead>
                        <tr>
                            <th>CNPJ</th>
                            <th>Nome</th>
                            <th>E-mail</th>
                            <th>Telefone</th>
                            <th>Fax</th>
                            <th>Data de Cadastro</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${filialList}" var="f">
                    <tr>
                        <td>${f.cnpj}</td>
                        <td>${f.nome}</td>
                        <td><c:if test="${empty f.email}">-</c:if>${f.email}</td>
                        <td width="10%">${f.telefone}</td>
                        <td width="10%"><c:if test="${empty f.fax}">-</c:if>${f.fax}</td>
                        <td width="12%"><fmt:formatDate pattern="dd/MM/yyyy" value="${f.data}"/></td>
                        <td width="53" >
                            <a class="preview" href="<c:url value="/filial/view/${f.id}" />" onclick="return hs.htmlExpand(this, {objectType:'ajax', align:'center', width:'640', headingText:'Filial'});">Visualizar</a>
                            <a class="edit-form" href="<c:url value="/filial/edit/${f.id}" />" onclick="return hs.htmlExpand(this, {objectType:'iframe', align:'center', width:'640', headingText:'Editar Filial'});">Editar</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhuma filial cadastrada até o momento.</p>
            </c:otherwise>
</c:choose>