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
                    <td><label for="tipo">Tipo:</label></td>
                    <td>
                    <c:forEach items="${types}" var="tipo">
                        <input type="radio" name="doador.tipo" value="${tipo}" <c:if test="${tipo == 'FISICA'}">id="tipo" checked="checked" </c:if>/>&nbsp;<span>${tipo.type}</span>&nbsp;&nbsp;&nbsp;
                    </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><label for="nome">Nome:</label></td>
                    <td><input type="text" name="doador.nome" value="" id="nome" size="50" maxlength="200" /></td>

                    <td><label for="dataNascimento">Data de Nascimento:</label></td>
                    <td><input type="text" name="doador.nascimento" value="" id="dataNascimento" size="18" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><label for="rg">RG:</label></td>
                    <td><input type="text" name="doador.rg" value="" id="rg" size="20" maxlength="20" alt="rg" /></td>

                    <td><label for="cpfoucnpj">CPF:</label></td>
                    <td><input type="text" name="doador.cpf" value="" id="cpfoucnpj" size="18" maxlength="14" alt="cpf" /></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="doador.email" value="" id="email" size="35" maxlength="100" /></td>

                    <td><label for="fone">Telefone:</label></td>
                    <td><input type="text" name="doador.telefone" value="" id="fone" size="18" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="status">Situação:</label></td>
                    <td>
                        <select name="doador.status" id="status">
                        <c:forEach items="${status}" var="s">
                            <option value="${s}"<c:if test="${doador.status == s || (empty doador.status && s == 'NOVO')}"> selected="selected"</c:if>>${s.status}</option>
                        </c:forEach>
                        </select>
                    </td>

                    <td><label for="celular">Celular:</label></td>
                    <td><input type="text" name="doador.celular" value="" id="celular" size="18" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="observacao">Observação:</label></td>
                    <td colspan="3"><textarea name="doador.observacao" id="observacao" rows="4" cols="50"></textarea></td>
                </tr>
            </table>
            <hr />
            <table>
                <tr>
                    <td><label for="cep">CEP:</label></td>
                    <td>
                        <input type="text" name="doador.endereco.cep" value="" id="cep" size="9" maxlength="9" alt="cep" />
                        <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                    </td>
                </tr>
                <tr>
                    <td><label for="logradouro">Logradouro:</label></td>
                    <td><input type="text" name="doador.endereco.logradouro" value="" id="logradouro" size="50" maxlength="200" /></td>

                    <td><label for="numero">Número:</label></td>
                    <td><input type="text" name="doador.endereco.numero" value="" id="numero" size="10" maxlength="10" alt="numero" /></td>
                </tr>
                <tr>
                    <td><label for="complemento">Complemento:</label></td>
                    <td><input type="text" name="doador.endereco.complemento" value="" id="complemento" size="50" maxlength="150" /></td>

                    <td><label for="uf">Estado:</label></td>
                    <td><input type="text" name="doador.endereco.uf" value="" id="uf" size="2" maxlength="2" /></td>
                </tr>
                <tr>
                    <td><label for="bairro">Bairro:</label></td>
                    <td><input type="text" name="doador.endereco.bairro" value="" id="bairro" size="50" maxlength="100" /></td>

                    <td><label for="cidade">Cidade:</label></td>
                    <td><input type="text" name="doador.endereco.cidade" value="" id="cidade" size="30" maxlength="100" /></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="doador.unidade.id" value="${employeeSession.unidade.id}" /></td>
                    <td><input type="button" id="doAll" name="add-doador" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>