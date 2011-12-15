<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/iframe.css" />" media="screen" charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/smoothness/smoothness.css" />" media="screen" charset="utf-8" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/sysadmin.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/meiomask.js" />" charset="utf-8"></script>

        <!-- jQuery UI -->
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty newsletter}">
                <form action="javascript:;" method="post">
                    <table>
                        <tr>
                            <td><label for="hostname">Servidor SMTP:</label></td>
                            <td><input type="text" name="newsletter.hostname" value="${newsletter.hostname}" id="hostname" size="14" maxlength="14" alt="hostname"/></td>
                        </tr>
                        <tr>
                            <td><label for="smtpport">Porta SMTP:</label></td>
                            <td><input type="text" name="newsletter.smtpport" value="${newsletter.smtpport}" id="smtpport" size="3" maxlength="3" alt="smtport"/></td>
                        </tr>
                        <tr>
                            <td><label for="account">Conta:</label></td>
                            <td><input type="text" name="newsletter.account" value="${newsletter.account}" id="account" size="14" maxlength="100" alt="account"/></td>
                        </tr>
                        <tr>
                            <td><label for="password">Senha:</label></td>
                            <td><input type="password" name="newsletter.password" value="${newsletter.password}" id="password" size="14" maxlength="32" alt="password"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="button" id="doAll" name="admin-newsletter-add" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <p>Esta Conta não existe ou foi excluída.</p>
            </c:otherwise>
        </c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath()%>";
        </script>
    </body>
</html>