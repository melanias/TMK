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
        <script type="text/javascript" src="<c:url value="/js/sysadmin.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/meiomask.js" />" charset="utf-8"></script>

        <!-- jQuery UI -->
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.datepicker-pt-BR.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty funcionario}">
        <form action="javascript:;" method="post">
            <table>
                <tr>
                    <td><label for="nome">Nome*:</label></td>
                    <td><input type="text" name="funcionario.nome" value="${funcionario.nome}" id="nome" size="50" maxlength="200" /></td>

                    <td><label for="dataNascimento">Data de Nascimento:</label></td>
                    <td><input type="text" name="funcionario.nascimento" value="<fmt:formatDate value="${funcionario.nascimento}" type="date" pattern="dd/MM/yyyy"/>" id="dataNascimento" size="16" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><label for="rg">RG*:</label></td>
                    <td><input type="text" name="funcionario.rg" value="${funcionario.rg}" id="rg" size="20" maxlength="20" alt="rg" /></td>

                    <td><label for="dataAdmissao">Data de Admissão:</label></td>
                    <td><input type="text" name="funcionario.admissao" value="<fmt:formatDate value="${funcionario.admissao}" type="date" pattern="dd/MM/yyyy"/>" id="dataAdmissao" size="16" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><label for="cpf">CPF*:</label></td>
                    <td><input type="text" name="funcionario.cpf" value="${funcionario.cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>

                    <td><label for="dataDemissao">Data de Demissão:</label></td>
                    <td><input type="text" name="funcionario.demissao" value="<fmt:formatDate value="${funcionario.demissao}" type="date" pattern="dd/MM/yyyy"/>" id="dataDemissao" size="16" maxlength="10" alt="data" /></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="funcionario.email" value="${funcionario.email}" id="email" size="35" maxlength="100" /></td>

                    <td><label for="unidade">Unidade*:</label></td>
                    <td>
                        <select name="funcionario.unidade.id" id="unidade">
                            <option value="">--</option>
                        <c:forEach items="${unidades}" var="unidade">
                            <option value="${unidade.id}"<c:if test="${funcionario.unidade.id == unidade.id}"> selected="selected"</c:if>>${unidade.razaoSocial}<c:if test="${unidade.matriz}"> - Matriz</c:if></option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="fone">Telefone*:</label></td>
                    <td><input type="text" name="funcionario.telefone" value="${funcionario.telefone}" id="fone" size="18" maxlength="14" alt="phone" /></td>

                    <td><label for="setor">Setor:</label></td>
                    <td>
                        <select name="funcionario.setor.id" id="setor">
                            <option value="">--</option>
                        <c:forEach items="${funcionario.unidade.setores}" var="setor">
                            <option value="${setor.id}"<c:if test="${funcionario.setor.id == setor.id}"> selected="selected"</c:if>>${setor.nome}</option>
                        </c:forEach>
                        </select>
                        <img src="/TMK/images/loader.gif" id="loading" alt="Carregando..." title="Carregando..." />
                    </td>
                </tr>
                <tr>
                    <td><label for="celular">Celular:</label></td>
                    <td><input type="text" name="funcionario.celular" value="${funcionario.celular}" id="celular" size="18" maxlength="14" alt="phone" /></td>

                    <td><label for="perfil">Perfil*:</label></td>
                    <td>
                        <select name="funcionario.perfil" id="perfil">
                        <c:forEach items="${roles}" var="r">
                            <option value="${r}"<c:if test="${funcionario.perfil == r}"> selected="selected"</c:if>>${r.userRole}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="login">Login*:</label></td>
                    <td><input type="text" name="funcionario.login" value="${funcionario.login}" id="login" size="16" maxlength="40" /></td>

                    <td><label for="status">Situação*:</label></td>
                    <td>
                        <select name="funcionario.status" id="status">
                        <c:forEach items="${status}" var="s">
                            <option value="${s}"<c:if test="${funcionario.status == s || (empty funcionario.status && s == 'ATIVO')}"> selected="selected"</c:if>>${s.status}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="senha">Senha*:</label></td>
                    <td><input type="password" name="funcionario.senha" value="" id="senha" size="16" /></td>

                    <td><label for="checkPass">Repetir senha*:</label></td>
                    <td><input type="password" name="funcionario.checkPass" value="" id="checkPass" size="16" /></td>
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
                    <td><input type="text" name="funcionario.endereco.logradouro" value="${funcionario.endereco.logradouro}" id="logradouro" size="50" maxlength="200" /></td>

                    <td><label for="numero">Número:</label></td>
                    <td><input type="text" name="funcionario.endereco.numero" value="${funcionario.endereco.numero}" id="numero" size="10" maxlength="10" alt="numero" /></td>
                </tr>
                <tr>
                    <td><label for="complemento">Complemento:</label></td>
                    <td><input type="text" name="funcionario.endereco.complemento" value="${funcionario.endereco.complemento}" id="complemento" size="50" maxlength="150" /></td>

                    <td><label for="uf">Estado:</label></td>
                    <td><input type="text" name="funcionario.endereco.uf" value="${funcionario.endereco.uf}" id="uf" size="2" maxlength="2" /></td>
                </tr>
                <tr>
                    <td><label for="bairro">Bairro:</label></td>
                    <td><input type="text" name="funcionario.endereco.bairro" value="${funcionario.endereco.bairro}" id="bairro" size="50" maxlength="100" /></td>

                    <td><label for="cidade">Cidade:</label></td>
                    <td><input type="text" name="funcionario.endereco.cidade" value="${funcionario.endereco.cidade}" id="cidade" size="30" maxlength="100" /></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="funcionario.id" value="${funcionario.id}" /></td>
                    <td><input type="button" id="doAll" name="admin-funcionario-edit" value="Salvar" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <p>Este funcionário não existe ou foi excluído.</p>
    </c:otherwise>
</c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath()%>";
        </script>
    </body>
</html>