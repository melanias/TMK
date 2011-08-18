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
                    <td><label for="nome">Nome:</label></td>
                    <td><input type="text" name="funcionario.nome" value="" id="nome" size="50" maxlength="200" /></td>

                    <td><label for="senha">Senha:</label></td>
                    <td><input type="password" name="funcionario.senha" value="" id="senha" size="16" /></td>
                </tr>
                <tr>
                    <td><label for="rg">RG:</label></td>
                    <td><input type="text" name="funcionario.rg" value="" id="rg" size="16" maxlength="13" alt="rg" /></td>

                    <td><label for="cpf">CPF:</label></td>
                    <td><input type="text" name="funcionario.cpf" value="" id="cpf" size="16" maxlength="14" alt="cpf" /></td>
                </tr>
                <tr>
                    <td><label for="dataNascimento">Data de Nascimento:</label></td>
                    <td><input type="text" name="funcionario.nascimento" value="" id="dataNascimento" size="16" maxlength="10" alt="data" /></td>

                    <td><label for="filial">Filial:</label></td>
                    <td>
                        <select name="funcionario.filial.id" id="filial">
                        <c:if test="${empty filiais}">
                            <option value="">--</option>
                        </c:if>
                        <c:forEach items="${filiais}" var="filial">
                            <option value="${filial.id}">${filial.nome}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="funcionario.email" value="" id="email" size="35" maxlength="100" /></td>

                    <td><label for="setor">Setor:</label></td>
                    <td>
                        <select name="funcionario.setor.id" id="setor">
                        <c:if test="${empty setores}">
                            <option value="">--</option>
                        </c:if>
                        <c:forEach items="${setores}" var="setor">
                            <option value="${setor.id}">${setor.sigla}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="fone">Telefone:</label></td>
                    <td><input type="text" name="funcionario.telefone" value="" id="fone" size="16" maxlength="14" alt="phone" /></td>

                    <td><label for="celular">Celular:</label></td>
                    <td><input type="text" name="funcionario.celular" value="" id="celular" size="16" maxlength="14" alt="phone" /></td>
                </tr>
                <tr>
                    <td><label for="dataAdmissao">Data de Admissão:</label></td>
                    <td><input type="text" name="funcionario.admissao" value="" id="dataAdmissao" size="16" maxlength="10" alt="data" /></td>

                    <td><label for="perfil">Perfil:</label></td>
                    <td>
                        <select name="funcionario.perfil" id="perfil">
                        <c:forEach items="${roles}" var="r">
                            <option value="${r}">${r.userRole}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="dataDemissao">Data de Demissão:</label></td>
                    <td><input type="text" name="funcionario.demissao" value="" id="dataDemissao" size="16" maxlength="10" alt="data" /></td>

                    <td><label for="status">Situação:</label></td>
                    <td>
                        <select name="funcionario.status" id="status">
                        <c:forEach items="${status}" var="s">
                            <option value="${s}"<c:if test="${funcionario.status == s || (empty funcionario.status && s == 'ATIVO')}"> selected="selected"</c:if>>${s.status}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <hr />
            <table>
                <tr>
                    <td><label for="cep">CEP:</label></td>
                    <td>
                        <input type="text" name="funcionario.endereco.cep" value="" id="cep" size="9" maxlength="9" alt="cep" />
                        <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                    </td>
                </tr>
                <tr>
                    <td><label for="logradouro">Logradouro:</label></td>
                    <td><input type="text" name="funcionario.endereco.logradouro" value="" id="logradouro" size="50" maxlength="200" readonly="readonly" /></td>
                    <td><label for="numero">Número:</label></td>
                    <td><input type="text" name="funcionario.endereco.numero" value="" id="numero" size="10" maxlength="10" alt="numero" /></td>
                </tr>
                <tr>
                    <td><label for="complemento">Complemento:</label></td>
                    <td><input type="text" name="funcionario.endereco.complemento" value="" id="complemento" size="50" maxlength="150" /></td>
                    <td><label for="uf">Estado:</label></td>
                    <td><input type="text" name="funcionario.endereco.uf" value="" id="uf" size="2" maxlength="2" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><label for="bairro">Bairro:</label></td>
                    <td><input type="text" name="funcionario.endereco.bairro" value="" id="bairro" size="50" maxlength="100" readonly="readonly" /></td>
                    <td><label for="cidade">Cidade:</label></td>
                    <td><input type="text" name="funcionario.endereco.cidade" value="" id="cidade" size="35" maxlength="100" readonly="readonly" /></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="funcionario.empresa.id" value="${employeeSession.empresa.id}" /></td>
                    <td><input type="button" id="doAll" name="add-funcionario" value="Salvar" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath() %>";
        </script>
    </body>
</html>