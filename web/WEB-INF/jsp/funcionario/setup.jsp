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
                <blockquote>
    <c:forEach items="${errors}" var="error">
                    <p>${error.message}</p>
    </c:forEach>
                </blockquote>
</c:if>
                <form action="<c:url value="/setup" />" method="post">
                    <table>
                        <tr>
                            <td><label for="nome">Nome:</label></td>
                            <td><input type="text" name="funcionario.nome" value="${funcionario.nome}" id="nome" size="50" maxlength="200" /></td>

                            <td><label for="dataNascimento">Data de Nascimento:</label></td>
                            <td><input type="text" name="funcionario.nascimento" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.nascimento}" />" id="dataNascimento" size="16" maxlength="10" alt="data" /></td>
                        </tr>
                        <tr>
                            <td><label for="rg">RG:</label></td>
                            <td><input type="text" name="funcionario.rg" value="${funcionario.rg}" id="rg" size="16" maxlength="13" alt="rg" /></td>

                            <td><label for="dataAdmissao">Data de Admiss�o:</label></td>
                            <td><input type="text" name="funcionario.admissao" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.admissao}" />" id="dataAdmissao" size="16" maxlength="10" alt="data" /></td>
                        </tr>
                        <tr>
                            <td><label for="cpf">CPF:</label></td>
                            <td><input type="text" name="funcionario.cpf" value="${funcionario.cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>

                            <td><label for="fone">Telefone:</label></td>
                            <td><input type="text" name="funcionario.telefone" value="${funcionario.telefone}" id="fone" size="16" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="email">E-mail:</label></td>
                            <td><input type="text" name="funcionario.email" value="${funcionario.email}" id="email" size="35" maxlength="100" /></td>

                            <td><label for="celular">Celular:</label></td>
                            <td><input type="text" name="funcionario.celular" value="${funcionario.celular}" id="celular" size="16" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="login">Login:</label></td>
                            <td><input type="text" name="funcionario.login" value="${funcionario.login}" id="login" size="16" maxlength="40" /></td>

                            <td><label for="senha">Senha:</label></td>
                            <td><input type="password" name="funcionario.senha" value="" id="senha" size="16" /></td>
                        </tr>
                    </table>
                    <hr />
                    <table>
                        <tr>
                            <td><label for="cep">CEP:</label></td>
                            <td>
                                <input type="text" name="funcionario.endereco.cep" value="${funcionario.endereco.cep}" id="cep" size="9" maxlength="9" alt="cep" />
                                <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                            </td>
                        </tr>
                        <tr>
                            <td><label for="logradouro">Logradouro:</label></td>
                            <td><input type="text" name="funcionario.endereco.logradouro" value="${funcionario.endereco.logradouro}" id="logradouro" size="50" maxlength="200" readonly="readonly" /></td>
                            <td><label for="numero">N�mero:</label></td>
                            <td><input type="text" name="funcionario.endereco.numero" value="${funcionario.endereco.numero}" id="numero" size="10" maxlength="10" alt="numero" /></td>
                        </tr>
                        <tr>
                            <td><label for="complemento">Complemento:</label></td>
                            <td><input type="text" name="funcionario.endereco.complemento" value="${funcionario.endereco.complemento}" id="complemento" size="50" maxlength="150" /></td>
                            <td><label for="uf">Estado:</label></td>
                            <td><input type="text" name="funcionario.endereco.uf" value="${funcionario.endereco.uf}" id="uf" size="2" maxlength="2" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td><label for="bairro">Bairro:</label></td>
                            <td><input type="text" name="funcionario.endereco.bairro" value="${funcionario.endereco.bairro}" id="bairro" size="50" maxlength="100" readonly="readonly" /></td>
                            <td><label for="cidade">Cidade:</label></td>
                            <td><input type="text" name="funcionario.endereco.cidade" value="${funcionario.endereco.cidade}" id="cidade" size="35" maxlength="100" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td><input type="hidden" name="funcionario.perfil" value="GERENTE" /></td>
                            <td><input type="submit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>