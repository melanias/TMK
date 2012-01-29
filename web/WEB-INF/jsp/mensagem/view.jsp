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
    <c:when test="${not empty mensagem}">
        <table>
            <tr>
                <td><strong>Data de Envio:</strong></td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${mensagem.data}" /></td>

                <td><strong>Tipo de Envio:</strong></td>
                <td>${mensagem.tipo.type}</td>
            </tr>
            <tr>
                <td><strong>Mensagem:</strong></td>
                <td colspan="3">${mensagem.texto}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Funcion�rio:</strong></td>
                <td>${mensagem.funcionario.nome}</td>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty mensagem.funcionario.email}">-</c:if>${mensagem.funcionario.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty mensagem.funcionario.telefone}">-</c:if>${mensagem.funcionario.telefone}</td>
                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty mensagem.funcionario.celular}">-</c:if>${mensagem.funcionario.celular}</td>
            </tr>
            <tr>
                <td><strong>Perfil:</strong></td>
                <td>${mensagem.funcionario.perfil.userRole}</td>
                <td><strong>Situa��o:</strong></td>
                <td>${mensagem.funcionario.status.status}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Esta mensagem n�o existe ou foi exclu�da.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>