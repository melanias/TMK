<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/sysadmin.css" />" media="screen" charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/smoothness/smoothness.css" />" media="screen" charset="utf-8" />

        <!-- Font -->
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold" media="screen" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/sysadmin.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/meiomask.js" />" charset="utf-8"></script>

        <!-- jQuery UI -->
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.datepicker-pt-BR.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <div id="main">
            <div class="content">
                <h1>${title}</h1>
<c:if test="${not empty errors}">
                <!--
                <blockquote>
    <c:forEach items="${errors}" var="error">
                    <p>${error.message}</p>
    </c:forEach>
                </blockquote>
                -->
</c:if>
                <form action="javascript:;" method="post">
                    <fieldset>
                        <legend>Unidade (Matriz)</legend>
                        <table>
                            <tr>
                                <td><label for="cnpj">CNPJ:</label></td>
                                <td><input type="text" name="unidade.cnpj" value="" id="cnpj" size="18" maxlength="18" alt="cnpj" /></td>
                            </tr>
                            <tr>
                                <td><label for="razaoSocial">Razão Social:</label></td>
                                <td><input type="text" name="unidade.razaoSocial" value="" id="razaoSocial" size="50" maxlength="200" /></td>
                            </tr>
                            <tr>
                                <td><label for="nomeFantasia">Nome Fantasia:</label></td>
                                <td><input type="text" name="unidade.nomeFantasia" value="" id="nomeFantasia" size="50" maxlength="200" /></td>
                            </tr>
                            <tr>
                                <td><label for="site">Site:</label></td>
                                <td><input type="text" name="unidade.site" value="" id="site" size="50" maxlength="200" /></td>
                            </tr>
                            <tr>
                                <td><label for="email">E-mail:</label></td>
                                <td><input type="text" name="unidade.email" value="" id="email" size="35" maxlength="100" /></td>
                            </tr>
                            <tr>
                                <td><label for="telefone">Telefone:</label></td>
                                <td><input type="text" name="unidade.telefone" value="" id="telefone" size="14" maxlength="14" alt="phone" /></td>
                            </tr>
                            <tr>
                                <td><label for="fax">Fax:</label></td>
                                <td>
                                    <input type="text" name="unidade.fax" value="" id="fax" size="14" maxlength="14" alt="phone" />
                                    <input type="hidden" name="unidade.matriz" value="true" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend>Administrador</legend>
                        <table>
                            <tr>
                                <td><label for="nome">Nome:</label></td>
                                <td><input type="text" name="funcionario.nome" value="" id="nome" size="50" maxlength="200" /></td>

                                <td><label for="dataNascimento">Data de Nascimento:</label></td>
                                <td><input type="text" name="funcionario.nascimento" value="" id="dataNascimento" size="16" maxlength="10" alt="data" /></td>
                            </tr>
                            <tr>
                                <td><label for="rg">RG:</label></td>
                                <td><input type="text" name="funcionario.rg" value="" id="rg" size="20" maxlength="20" alt="rg" /></td>

                                <td><label for="dataAdmissao">Data de Admissão:</label></td>
                                <td><input type="text" name="funcionario.admissao" value="" id="dataAdmissao" size="16" maxlength="10" alt="data" /></td>
                            </tr>
                            <tr>
                                <td><label for="cpf">CPF:</label></td>
                                <td><input type="text" name="funcionario.cpf" value="" id="cpf" size="16" maxlength="14" alt="cpf" /></td>

                                <td><label for="phone">Telefone:</label></td>
                                <td><input type="text" name="funcionario.telefone" value="" id="phone" size="16" maxlength="14" alt="phone" /></td>
                            </tr>
                            <tr>
                                <td><label for="mail">E-mail:</label></td>
                                <td><input type="text" name="funcionario.email" value="" id="mail" size="35" maxlength="100" /></td>

                                <td><label for="cellphone">Celular:</label></td>
                                <td><input type="text" name="funcionario.celular" value="" id="cellphone" size="16" maxlength="14" alt="phone" /></td>
                            </tr>
                            <tr>
                                <td><label for="login">Login:</label></td>
                                <td><input type="text" name="funcionario.login" value="" id="login" size="16" maxlength="40" /></td>

                                <td><label for="senha">Senha:</label></td>
                                <td>
                                    <input type="password" name="funcionario.senha" value="" id="senha" size="16" />
                                    <input type="hidden" name="funcionario.perfil" value="ADMINISTRADOR" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                <button type="button" id="setup">Salvar</button>
            </div>
        </div>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>