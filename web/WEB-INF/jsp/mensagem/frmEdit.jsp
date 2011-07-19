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

        <!-- CKEditor -->
        <script type="text/javascript" src="<c:url value="/js/ckeditor/ckeditor.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/ckeditor/adapters/jquery.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty campanha}">
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="tipo">Tipo de Envio:</label></td>
                    <td>
                        <c:forEach items="${types}" var="tipo">
                            <input type="radio" name="mensagem.tipo" value="${tipo}" id="tipo" />&nbsp;${tipo.type}&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><label for="texto">Mensagem:</label></td>
                    <td><textarea name="mensagem.texto" id="texto" rows="5" cols="50">${mensagem.texto}</textarea></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="mensagem.id" value="${mensagem.id}" />
                        <input type="hidden" name="mensagem.funcionario.id" value="${mensagem.funcionario.id}" />
                    </td>
                    <td><input type="button" id="doAll" name="edit-mensagem" value="Salvar" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <p>Esta mensagem não existe ou foi excluída.</p>
    </c:otherwise>
</c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>