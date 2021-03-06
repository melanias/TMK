<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <script type="text/javascript" src="<c:url value="/js/aappe.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/meiomask.js" />" charset="utf-8"></script>

        <!-- jQuery UI -->
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.js" />" charset="utf-8"></script>

        <!-- TinyMCE -->
        <script type="text/javascript" src="<c:url value="/js/tinymce/tiny.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/tinymce/jquery.tinymce.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty servidor}">
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="texto">Texto:</label></td>
                    <td><textarea name="mensagem.texto" id="texto" rows="4" cols="50">${mensagem.texto}</textarea></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="mensagem.funcionario.id" value="${employeeSession.id}" /></td>
                    <td><input type="button" id="doAll" name="add-mensagem" value="Enviar" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <p>Nenhum servidor SMTP cadastrado e/ou ativo.</p>
    </c:otherwise>
</c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>