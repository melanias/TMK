<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Thales Imbruglia" />

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty smtp}">
        <table>
            <tr>
                <td><strong>Host:</strong></td>
                <td>${smtp.hostName}</td>
            </tr>
            <tr>
                <td><strong>Porta:</strong></td>
                <td>${smtp.port}</td>
            </tr>
            <tr>
                <td><strong>Conta:</strong></td>
                <td>${smtp.account}</td>
            </tr>
            <tr>
                <td><strong>TLS:</strong></td>
                <td>
                <c:choose>
                    <c:when test="${smtp.tls == true}">Sim</c:when>
                    <c:otherwise>Não</c:otherwise>
                </c:choose>
                </td>
            </tr>
            <tr>
                <td><strong>Situação:</strong></td>
                <td>${smtp.status.status}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Este servidor não existe ou foi excluído.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>