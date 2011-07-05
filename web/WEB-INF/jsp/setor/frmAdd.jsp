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
                    <td><input type="text" name="setor.nome" value="" id="nome" size="50" maxlength="200" /></td>
                </tr>
                <tr>
                    <td><label for="sigla">Sigla:</label></td>
                    <td><input type="text" name="setor.sigla" value="" id="sigla" size="20" maxlength="20" /></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="setor.email" value="" id="email" size="35" maxlength="100" /></td>
                </tr>
                <tr>
                    <td><label for="fone">Telefone:</label></td>
                    <td><input type="text" name="setor.telefone" value="" id="fone" size="14" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="fax">Fax:</label></td>
                    <td><input type="text" name="setor.fax" value="" id="fax" size="14" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="filial">Filial:</label></td>
                    <td>
                        <select name="setor.filial.id" id="filial">
                        <c:if test="${empty filiais}">
                            <option value="">--</option>
                        </c:if>
                        <c:forEach items="${filiais}" var="filial">
                            <option value="${filial.id}">${filial.nome}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="responsavel">Responsável:</label></td>
                    <td>
                        <select name="setor.responsavel.id" id="responsavel">
                            <option value="0">--</option>
                        <c:forEach items="${funcionarios}" var="funcionario">
                            <option value="${funcionario.id}">${funcionario.firstAndLastName}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="button" id="doAll" name="add-setor" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>