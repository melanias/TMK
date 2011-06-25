<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty funcionario}">
        <table>
            <tr>
                <td><strong>Nome:</strong></td>
                <td>${funcionario.nome}</td>

                <td><strong>Data de Nascimento:</strong></td>
                <td><c:if test="${empty funcionario.nascimento}">-</c:if><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.nascimento}" /></td>
            </tr>
            <tr>
                <td><strong>RG:</strong></td>
                <td>${funcionario.rg}</td>

                <td><strong>CPF:</strong></td>
                <td>${funcionario.cpf}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td>${funcionario.email}</td>

                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty funcionario.telefone}">-</c:if>${funcionario.telefone}</td>
            </tr>
            <tr>
                <td><strong>Perfil:</strong></td>
                <td>${funcionario.perfil.userRole}</td>

                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty funcionario.celular}">-</c:if>${funcionario.celular}</td>
            </tr>
            <tr>
                <td><strong>Data de Admissão:</strong></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.admissao}" /></td>

                <td><strong>Data de Demissão</strong></td>
                <td><c:if test="${empty funcionario.demissao}">-</c:if><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.demissao}" /></td>
            </tr>
            <tr>
                <td><strong>Situação:</strong></td>
                <td>${funcionario.status.status}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Filial:</strong></td>
                <td>${funcionario.filial.nome}</td>

                <td><strong>Telefone:</strong></td>
                <td>${funcionario.filial.telefone}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty funcionario.filial.email}">-</c:if>${funcionario.filial.email}</td>

                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty funcionario.filial.fax}">-</c:if>${funcionario.filial.fax}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Setor:</strong></td>
                <td>${funcionario.setor.nome}</td>

                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty funcionario.setor.telefone}">-</c:if>${funcionario.setor.telefone}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty funcionario.setor.email}">-</c:if>${funcionario.setor.email}</td>

                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty funcionario.setor.fax}">-</c:if>${funcionario.setor.fax}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Este funcionário não existe ou foi excluído.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>