<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <blockquote>
    <c:forEach items="${errors}" var="error">
                    <p>${error.message}</p>
    </c:forEach>
                </blockquote>
</c:if>
                <form action="<c:url value="/primeiro-usuario" />" method="post">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td><label for="cpf">CPF:</label></td>
                            <td><input type="text" name="user.cpf" value="${user.cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>
                        </tr>
                        <tr>
                            <td><label for="nome">Nome:</label></td>
                            <td><input type="text" name="user.nome" value="${user.nome}" id="nome" size="50" maxlength="200" /></td>
                        </tr>
                        <tr>
                            <td><label for="email">E-mail:</label></td>
                            <td><input type="text" name="user.email" value="${user.email}" id="email" size="35" maxlength="100" /></td>
                        </tr>
                        <tr>
                            <td><label for="telefone">Telefone:</label></td>
                            <td><input type="text" name="user.telefone" value="${user.telefone}" id="telefone" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="celular">Celular:</label></td>
                            <td><input type="text" name="user.celular" value="${user.celular}" id="celular" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="login">Login:</label></td>
                            <td><input type="text" name="user.login" value="${user.login}" id="login" size="40" maxlength="40" /></td>
                        </tr>
                        <tr>
                            <td><label for="senha">Senha:</label></td>
                            <td><input type="password" name="user.senha" value="" id="senha" size="14" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>