<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>" media="screen" charset="utf-8" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/meiomask.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/login.js" />"></script>

        <title>${title}</title>
    </head>
    <body>
<c:if test="${not empty errors}">
        <div id="warning">
    <c:forEach items="${errors}" var="error">
            ${error.message}
    </c:forEach>
        </div>
</c:if>
        <!-- Formulário -->
        <div id="wrapper">
            <span>
                <h1>${title}</h1>
            </span>
            <div id="lt"></div>
            <div id="rt">
                <form action="<c:url value="/login" />" method="post">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td><label for="cpf">CPF:</label></td>
                        </tr>
                        <tr>
                            <td><input type="text" name="cpf" value="${cpf}" id="cpf" size="16" maxlength="14" alt="cpf" /></td>
                        </tr>
                        <tr>
                            <td><label for="senha">Senha:</label></td>
                        </tr>
                        <tr>
                            <td><input type="password" name="senha" value="" id="senha" size="16" /></td>
                        </tr>
                        <tr>
                        <tr>
                            <td>
                                <select name="sistemas" size="2"/>
                                <option value="Administrativo"/>Administrativo</option>
                                <option value="TMK">TMK</option></td>
                        </tr>
                            <td><input type="submit" value="Entrar" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>