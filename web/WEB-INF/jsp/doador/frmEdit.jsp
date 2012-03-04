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
        <c:choose>
            <c:when test="${not empty doador}">
                <form action="javascript:;" method="post">
                    <table>
                        <tr>
                            <td><label for="tipo">Tipo*:</label></td>
                            <td>
                            <c:forEach items="${types}" var="tipo">
                                <input type="radio" name="doador.tipo" value="${tipo}" <c:if test="${tipo == doador.tipo}">id="tipo" checked="checked" </c:if>/>&nbsp;<span>${tipo.type}</span>&nbsp;&nbsp;&nbsp;
                            </c:forEach>
                            </td>

                            <td><label for="dataCadastro">Data de Cadastro:</label></td>
                            <td><input type="text" name="doador.data" value="<fmt:formatDate value="${doador.data}" type="date" pattern="dd/MM/yyyy" />" id="dataCadastro" size="18" maxlength="10" alt="data" /></td>
                        </tr>
                        <tr>
                            <td><label for="nome">Nome*:</label></td>
                            <td><input type="text" name="doador.nome" value="${doador.nome}" id="nome" size="50" maxlength="200" /></td>

                            <td><label for="dataNascimento">Data de Nascimento:</label></td>
                            <td><input type="text" name="doador.nascimento" id="dataNascimento" value="<fmt:formatDate value="${doador.nascimento}" type="date" pattern="dd/MM/yyyy"/>" size="18" maxlength="10" alt="data" <c:if test="${doador.tipo == 'JURIDICA'}">disabled="disabled" </c:if>/></td>
                        </tr>
                        <tr>
                            <td><label for="rg">RG:</label></td>
                            <td><input type="text" name="doador.rg" value="${doador.rg}" id="rg" size="20" maxlength="20" alt="rg" <c:if test="${doador.tipo == 'JURIDICA'}">disabled="disabled" </c:if>/></td>
                        <c:choose>
                            <c:when test="${doador.tipo == 'FISICA'}">
                            <td><label for="cpfoucnpj">CPF:</label></td>
                            <td><input type="text" name="doador.cpf" value="${doador.cpf}" id="cpfoucnpj" size="18" maxlength="14" alt="cpf" /></td>
                            </c:when>
                            <c:otherwise>
                            <td><label for="cpfoucnpj">CNPJ:</label></td>
                            <td><input type="text" name="doador.cnpj" value="${doador.cnpj}" id="cpfoucnpj" size="18" maxlength="18" alt="cnpj" /></td>
                            </c:otherwise>
                        </c:choose>
                        </tr>
                        <tr>
                            <td><label for="email">E-mail:</label></td>
                            <td><input type="text" name="doador.email" value="${doador.email}" id="email" size="35" maxlength="100" /></td>

                            <td><label for="fone">Telefone*:</label></td>
                            <td><input type="text" name="doador.telefone" value="${doador.telefone}" id="fone" size="18" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="status">Situação*:</label></td>
                            <td>
                                <select name="doador.status" id="status">
                                <c:forEach items="${status}" var="s">
                                    <option value="${s}"<c:if test="${doador.status == s}"> selected="selected"</c:if>>${s.status}</option>
                                </c:forEach>
                                </select>
                            </td>

                            <td><label for="celular">Celular*:</label></td>
                            <td><input type="text" name="doador.celular" value="${doador.celular}" id="celular" size="18" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="observacao">Observação:</label></td>
                            <td colspan="3"><textarea name="doador.observacao" id="observacao" rows="4" cols="50">${doador.observacao}</textarea></td>
                        </tr>
                    </table>
                    <hr />
                    <table>
                        <tr>
                            <td><label for="cep">CEP:</label></td>
                            <td>
                                <input type="text" name="doador.endereco.cep" value="${doador.endereco.cep}" id="cep" size="9" maxlength="9" alt="cep" />
                                <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                            </td>
                        </tr>
                        <tr>
                            <td><label for="logradouro">Logradouro*:</label></td>
                            <td><input type="text" name="doador.endereco.logradouro" value="${doador.endereco.logradouro}" id="logradouro" size="50" maxlength="200" /></td>

                            <td><label for="numero">Número:</label></td>
                            <td><input type="text" name="doador.endereco.numero" value="${doador.endereco.numero}" id="numero" size="10" maxlength="10" alt="numero" /></td>
                        </tr>
                        <tr>
                            <td><label for="complemento">Complemento:</label></td>
                            <td><input type="text" name="doador.endereco.complemento" value="${doador.endereco.complemento}" id="complemento" size="50" maxlength="150" /></td>

                            <td><label for="uf">Estado*:</label></td>
                            <td><input type="text" name="doador.endereco.uf" value="${doador.endereco.uf}" id="uf" size="2" maxlength="2" /></td>
                        </tr>
                        <tr>
                            <td><label for="bairro">Bairro*:</label></td>
                            <td><input type="text" name="doador.endereco.bairro" value="${doador.endereco.bairro}" id="bairro" size="50" maxlength="100" /></td>

                            <td><label for="cidade">Cidade*:</label></td>
                            <td><input type="text" name="doador.endereco.cidade" value="${doador.endereco.cidade}" id="cidade" size="30" maxlength="100" /></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="hidden" name="doador.id" value="${doador.id}" />
                                <input type="hidden" name="doador.unidade.id" value="${doador.unidade.id}" />
                            </td>
                            <td><input type="button" id="doAll" name="edit-doador" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <p>Este doador não existe ou foi excluído.</p>
            </c:otherwise>
        </c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath()%>";
        </script>
    </body>
</html>