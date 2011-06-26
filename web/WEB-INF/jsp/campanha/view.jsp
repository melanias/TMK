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
    <c:when test="${not empty campanha}">
        <table>
            <tr>
                <td><strong>Nome:</strong></td>
                <td>${campanha.nome}</td>
            </tr>
            <tr>
                <td><strong>Data inicial:</strong></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${campanha.dataInicial}" /></td>

                <td><strong>Data final:</strong></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${campanha.dataFinal}" /></td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Funcionário:</strong></td>
                <td>${campanha.funcionario.nome}</td>
                <td><strong>E-mail:</strong></td>
                <td>${campanha.funcionario.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty campanha.funcionario.telefone}">-</c:if>${campanha.funcionario.telefone}</td>
                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty campanha.funcionario.celular}">-</c:if>${campanha.funcionario.celular}</td>
            </tr>
            <tr>
                <td><strong>Perfil:</strong></td>
                <td>${campanha.funcionario.perfil.userRole}</td>
                <td><strong>Situação:</strong></td>
                <td>${campanha.funcionario.status.status}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Esta campanha não existe ou foi excluída.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>