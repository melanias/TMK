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
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="cnpj">CNPJ:</label></td>
                    <td><input type="text" name="filial.cnpj" value="" id="cnpj" size="18" maxlength="18" alt="cnpj" /></td>
                </tr>
                <tr>
                    <td><label for="nome">Nome:</label></td>
                    <td><input type="text" name="filial.nome" value="" id="nome" size="50" maxlength="200" /></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="filial.email" value="" id="email" size="35" maxlength="100" /></td>
                </tr>
                <tr>
                    <td><label for="fone">Telefone:</label></td>
                    <td><input type="text" name="filial.telefone" value="" id="fone" size="14" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="fax">Fax:</label></td>
                    <td><input type="text" name="filial.fax" value="" id="fax" size="14" maxlength="14" alt="phone" /></td>
                </tr>
            </table>
            <hr />
            <table>
                <tr>
                    <td><label for="cep">CEP:</label></td>
                    <td>
                        <input type="text" name="filial.endereco.cep" value="" id="cep" size="9" maxlength="9" alt="cep" />
                        <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                    </td>
                </tr>
                <tr>
                    <td><label for="logradouro">Logradouro:</label></td>
                    <td><input type="text" name="filial.endereco.logradouro" value="" id="logradouro" size="50" maxlength="200" /></td>

                    <td><label for="numero">Número:</label></td>
                    <td><input type="text" name="filial.endereco.numero" value="" id="numero" size="10" maxlength="10" alt="numero" /></td>
                </tr>
                <tr>
                    <td><label for="complemento">Complemento:</label></td>
                    <td><input type="text" name="filial.endereco.complemento" value="" id="complemento" size="50" maxlength="150" /></td>

                    <td><label for="uf">Estado:</label></td>
                    <td><input type="text" name="filial.endereco.uf" value="" id="uf" size="2" maxlength="2" /></td>
                </tr>
                <tr>
                    <td><label for="bairro">Bairro:</label></td>
                    <td><input type="text" name="filial.endereco.bairro" value="" id="bairro" size="50" maxlength="100" /></td>

                    <td><label for="cidade">Cidade:</label></td>
                    <td><input type="text" name="filial.endereco.cidade" value="" id="cidade" size="30" maxlength="100" /></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="filial.empresa.id" value="${employeeSession.empresa.id}" /></td>
                    <td><input type="button" id="doAll" name="admin-filial-add" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>