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
    <c:when test="${not empty doacao}">
        <table>
            <tr>
                <td><strong>Doador:</strong></td>
                <td>${doacao.doador.nome}</td>
            </tr>
            <tr>
                <td><strong>Valor:</strong></td>
                <td><fmt:formatNumber type="currency" value="${doacao.valor}" minFractionDigits="2" maxFractionDigits="2" /></td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty doacao.doador.telefone}">-</c:if>${doacao.doador.telefone}</td>

                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty doacao.doador.celular}">-</c:if>${doacao.doador.celular}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty doacao.doador.email}">-</c:if>${doacao.doador.email}</td>

                <td><strong>Situa��o:</strong></td>
                <td>${doacao.doador.status.status}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Operador:</strong></td>
                <td>${doacao.operador.nome}</td>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty doacao.operador.email}">-</c:if>${doacao.operador.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty doacao.operador.telefone}">-</c:if>${doacao.operador.telefone}</td>
                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty doacao.operador.celular}">-</c:if>${doacao.operador.celular}</td>
            </tr>
            <tr>
                <td><strong>Perfil:</strong></td>
                <td>${doacao.operador.perfil.userRole}</td>
                <td><strong>Situa��o:</strong></td>
                <td>${doacao.operador.status.status}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Representante:</strong></td>
                <td>${doacao.representante.nome}</td>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty doacao.representante.email}">-</c:if>${doacao.representante.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty doacao.representante.telefone}">-</c:if>${doacao.representante.telefone}</td>
                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty doacao.representante.celular}">-</c:if>${doacao.representante.celular}</td>
            </tr>
            <tr>
                <td><strong>Perfil:</strong></td>
                <td>${doacao.representante.perfil.userRole}</td>
                <td><strong>Situa��o:</strong></td>
                <td>${doacao.representante.status.status}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Esta doa��o n�o existe ou foi exclu�da.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>