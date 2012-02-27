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
                    <td><label for="donor">Doador*:</label></td>
                    <td><input type="text" value="" id="donor" size="50" maxlength="200" /></td>
                </tr>
                <tr>
                    <td><label for="campanha">Campanha:</label></td>
                    <td>
                        <select name="doacao.campanha.id" id="campanha">
                            <option value="">--</option>
                        <c:forEach items="${campanhas}" var="campanha">
                            <option value="${campanha.id}">${campanha.nome}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="valor">Valor (R$)*:</label></td>
                    <td><input type="text" name="doacao.valor" value="" id="valor" size="18" maxlength="16" alt="valor" /></td>
                </tr>
                <tr>
                    <td><label for="representante">Representante*:</label></td>
                    <td>
                        <select name="doacao.representante.id" id="representante">
                            <option value="">--</option>
                        <c:forEach items="${funcionarios}" var="funcionario">
                            <c:if test="${funcionario.perfil == 'REPRESENTANTE'}">
                            <option value="${funcionario.id}">${funcionario.firstAndLastName}</option>
                            </c:if>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="dataLigacao">Data da Ligação:</label></td>
                    <td><input type="text" name="doacao.ligacao" value="" id="dataLigacao" size="18" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><label for="dataRecebimento">Data de Recebimento*:</label></td>
                    <td><input type="text" name="doacao.recebimento" value="" id="dataRecebimento" size="18" maxlength="10" alt="data" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><label for="isPaga">Doação paga:</label></td>
                    <td><input type="checkbox" name="doacao.paga" value="true" id="isPaga" /></td>
                </tr>
                <tr>
                    <td><label for="descricao">Descrição:</label></td>
                    <td><textarea name="doacao.descricao" id="descricao" rows="4" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="doacao.doador.id" value="" id="doador" />
                        <input type="hidden" name="doacao.funcionario.id" value="${employeeSession.id}" />
                    </td>
                    <td><input type="button" id="doAll" name="add-doacao" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath()%>";
        </script>
    </body>
</html>