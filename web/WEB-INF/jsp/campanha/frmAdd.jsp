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
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.datepicker-pt-BR.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="campanha">Nome*:</label></td>
                    <td colspan="3"><input type="text" name="campanha.nome" value="" id="campanha" size="50" maxlength="200" /></td>
                </tr>
                <tr>
                    <td><label for="custoPrevisto">Custo Previsto (R$):</label></td>
                    <td><input type="text" name="campanha.custoPrevisto" value="" id="custoPrevisto" size="18" maxlength="16" alt="valor" /></td>

                    <td><label for="custoReal">Custo Real (R$):</label></td>
                    <td><input type="text" name="campanha.custoReal" value="" id="custoReal" size="18" maxlength="16" alt="valor" /></td>
                </tr>
                <tr>
                    <td><label for="receitaEsperada">Receita Esperada (R$):</label></td>
                    <td><input type="text" name="campanha.receitaEsperada" value="" id="receitaEsperada" size="18" maxlength="16" alt="valor" /></td>

                    <td><label for="receitaReal">Receita Real (R$):</label></td>
                    <td><input type="text" name="campanha.receitaReal" value="" id="receitaReal" size="18" maxlength="16" alt="valor" /></td>
                </tr>
                <tr>
                    <td><label for="tipo">Tipo*:</label></td>
                    <td>
                        <select name="campanha.type" id="tipo">
                        <c:forEach items="${type}" var="c">
                            <option value="${c}">${c.type}</option>
                        </c:forEach>
                        </select>
                    </td>

                    <td><label for="dataInicial">Data Inicial*:</label></td>
                    <td><input type="text" name="campanha.dataInicial" value="" id="dataInicial" size="18" maxlength="10" alt="data" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><label for="status">Situação*:</label></td>
                    <td>
                        <select name="campanha.status" id="status">
                        <c:forEach items="${status}" var="s">
                            <option value="${s}"<c:if test="${campanha.status == s || (empty campanha.status && s == 'PLANEJANDO')}"> selected="selected"</c:if>>${s.status}</option>
                        </c:forEach>
                        </select>
                    </td>

                    <td><label for="dataFinal">Data Final*:</label></td>
                    <td><input type="text" name="campanha.dataFinal" value="" id="dataFinal" size="18" maxlength="10" alt="data" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><label for="objetivo">Objetivo:</label></td>
                    <td colspan="3"><textarea name="campanha.objetivo" id="objetivo" rows="4" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="campanha.funcionario.id" value="${employeeSession.id}" /></td>
                    <td><input type="button" id="doAll" name="add-campanha" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath()%>";
        </script>
    </body>
</html>