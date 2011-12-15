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
    <c:when test="${not empty newsletter}">
        <table>
            <tr>
                <td><strong>Servidor SMTP:</strong></td>
                <td>${newsletter.hostname}</td>
            </tr>
            <tr>
                <td><strong>Porta SMTP:</strong></td>
                <td>${newsletter.smtpport}</td>
            </tr>
            <tr>
                <td><strong>Conta:</strong></td>
                <td>${newsletter.account}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Esta conta não existe ou foi excluída.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>