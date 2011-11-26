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
                        <legend>Empresa</legend>
                        <table>
                            <tr>
                                <td><label for="cnpj">CNPJ:</label></td>
                                <td><input type="text" name="empresa.cnpj" value="${empresa.cnpj}" id="cnpj" size="18" maxlength="18" alt="cnpj" /></td>
                            </tr>
                            <tr>
                                <td><label for="razaoSocial">Razão Social:</label></td>
                                <td><input type="text" name="empresa.razaoSocial" value="${empresa.razaoSocial}" id="razaoSocial" size="50" maxlength="200" /></td>
                            </tr>
                            <tr>
                                <td><label for="nomeFantasia">Nome Fantasia:</label></td>
                                <td><input type="text" name="empresa.nomeFantasia" value="${empresa.nomeFantasia}" id="nomeFantasia" size="50" maxlength="200" /></td>
                            </tr>
                            <tr>
                                <td><label for="site">Site:</label></td>
                                <td><input type="text" name="empresa.site" value="${empresa.site}" id="site" size="50" maxlength="200" /></td>
                            </tr>
                            <tr>
                                <td><label for="email">E-mail:</label></td>
                                <td><input type="text" name="empresa.email" value="${empresa.email}" id="email" size="35" maxlength="100" /></td>
                            </tr>
                            <tr>
                                <td><label for="telefone">Telefone:</label></td>
                                <td><input type="text" name="empresa.telefone" value="${empresa.telefone}" id="telefone" size="14" maxlength="14" alt="phone" /></td>
                            </tr>
                            <tr>
                                <td><label for="fax">Fax:</label></td>
                                <td>
                                    <input type="text" name="empresa.fax" value="${empresa.fax}" id="fax" size="14" maxlength="14" alt="phone" />
                                    <input type="hidden" name="empresa.status" value="ATIVO" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend>Responsável</legend>
                        <table>
                            <tr>
                                <td><label for="nome">Nome:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].nome" value="${empresa.funcionarios[0].nome}" id="nome" size="50" maxlength="200" /></td>

                                <td><label for="dataNascimento">Data de Nascimento:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].nascimento" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.funcionarios[0].nascimento}" />" id="dataNascimento" size="16" maxlength="10" alt="data" /></td>
                            </tr>
                            <tr>
                                <td><label for="rg">RG:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].rg" value="${empresa.funcionarios[0].rg}" id="rg" size="20" maxlength="20" alt="rg" /></td>

                                <td><label for="dataAdmissao">Data de Admissão:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].admissao" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.funcionarios[0].admissao}" />" id="dataAdmissao" size="16" maxlength="10" alt="data" /></td>
                            </tr>
                            <tr>
                                <td><label for="cpf">CPF:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].cpf" value="${empresa.funcionarios[0].cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>

                                <td><label for="phone">Telefone:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].telefone" value="${empresa.funcionarios[0].telefone}" id="phone" size="16" maxlength="14" alt="phone" /></td>
                            </tr>
                            <tr>
                                <td><label for="mail">E-mail:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].email" value="${empresa.funcionarios[0].email}" id="mail" size="35" maxlength="100" /></td>

                                <td><label for="cellphone">Celular:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].celular" value="${empresa.funcionarios[0].celular}" id="cellphone" size="16" maxlength="14" alt="phone" /></td>
                            </tr>
                            <tr>
                                <td><label for="login">Login:</label></td>
                                <td><input type="text" name="empresa.funcionarios[0].login" value="${empresa.funcionarios[0].login}" id="login" size="16" maxlength="40" /></td>

                                <td><label for="senha">Senha:</label></td>
                                <td>
                                    <input type="password" name="empresa.funcionarios[0].senha" value="" id="senha" size="16" />
                                    <input type="hidden" name="empresa.funcionarios[0].perfil" value="ADMINISTRADOR" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                <button type="button" id="setup">Salvar</button>
            </div>
        </div>
    </body>
</html>