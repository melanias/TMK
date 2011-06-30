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

        <title>${title}</title>
    </head>
    <body>
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="nome">Nome:</label></td>
                    <td colspan="3"><input type="text" name="campanha.nome" id="nome" value="${campanha.nome}" size="50" maxlength="200" /></td>
                </tr>
                <tr>
                    <td><label for="dataInicial">Data Inicial:</label></td>
                    <td><input type="text" name="campanha.dataInicial" id="dataInicial" value="<fmt:formatDate value="${campanha.dataInicial}" type="date" pattern="dd/MM/yyyy"/>" size="16" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><label for="dataFinal">Data Final:</label></td>
                    <td><input type="text" name="campanha.dataFinal" id="dataFinal" value="<fmt:formatDate value="${campanha.dataFinal}" type="date" pattern="dd/MM/yyyy"/>" size="16" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="campanha.funcionario.id" value="${employeeSession.id}" /></td>
                    <td><input type="button" id="doAll" name="add-campanha" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>