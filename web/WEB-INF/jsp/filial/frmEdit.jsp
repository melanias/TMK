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
<c:choose>
    <c:when test="${not empty filial}">
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="cnpj">CNPJ:</label></td>
                    <td><input type="text" name="filial.cnpj" value="${filial.cnpj}" id="cnpj" size="18" maxlength="18" alt="cnpj" /></td>
                </tr>
                <tr>
                    <td><label for="nome">Nome:</label></td>
                    <td><input type="text" name="filial.nome" value="${filial.nome}" id="nome" size="50" maxlength="200" /></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="filial.email" value="${filial.email}" id="email" size="35" maxlength="100" /></td>
                </tr>
                <tr>
                    <td><label for="fone">Telefone:</label></td>
                    <td><input type="text" name="filial.telefone" value="${filial.telefone}" id="fone" size="14" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="fax">Fax:</label></td>
                    <td><input type="text" name="filial.fax" value="${filial.fax}" id="fax" size="14" maxlength="14" alt="phone" /></td>
                </tr>
            </table>
            <hr />
            <table>
                <tr>
                    <td><label for="cep">CEP:</label></td>
                    <td>
                        <input type="text" name="filial.endereco.cep" value="${filial.endereco.cep}" id="cep" size="9" maxlength="9" alt="cep" />
                        <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                    </td>
                </tr>
                <tr>
                    <td><label for="logradouro">Logradouro:</label></td>
                    <td><input type="text" name="filial.endereco.logradouro" value="${filial.endereco.logradouro}" id="logradouro" size="50" maxlength="200" readonly="readonly" /></td>

                    <td><label for="numero">Número:</label></td>
                    <td><input type="text" name="filial.endereco.numero" value="${filial.endereco.numero}" id="numero" size="10" maxlength="10" alt="numero" /></td>
                </tr>
                <tr>
                    <td><label for="complemento">Complemento:</label></td>
                    <td><input type="text" name="filial.endereco.complemento" value="${filial.endereco.complemento}" id="complemento" size="50" maxlength="150" /></td>

                    <td><label for="uf">Estado:</label></td>
                    <td><input type="text" name="filial.endereco.uf" value="${filial.endereco.uf}" id="uf" size="2" maxlength="2" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><label for="bairro">Bairro:</label></td>
                    <td><input type="text" name="filial.endereco.bairro" value="${filial.endereco.bairro}" id="bairro" size="50" maxlength="100" readonly="readonly" /></td>

                    <td><label for="cidade">Cidade:</label></td>
                    <td><input type="text" name="filial.endereco.cidade" value="${filial.endereco.cidade}" id="cidade" size="35" maxlength="100" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="filial.id" value="${filial.id}" />
                        <input type="hidden" name="filial.empresa.id" value="${filial.empresa.id}" />
                    </td>
                    <td><input type="button" id="doAll" name="edit-filial" value="Salvar" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <p>Esta filial não existe ou foi excluída.</p>
    </c:otherwise>
</c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>