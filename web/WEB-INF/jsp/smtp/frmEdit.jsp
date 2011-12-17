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
        <c:choose>
            <c:when test="${not empty smtp}">
                <form action="javascript:;" method="post">
                    <table>
                        <tr>
                            <td><label for="hostname">Host:</label></td>
                            <td><input type="text" name="smtp.hostName" value="${smtp.hostName}" id="hostname" size="30" maxlength="100" /></td>

                            <td><label for="port">Porta:</label></td>
                            <td><input type="text" name="smtp.port" value="${smtp.port}" id="port" size="5" maxlength="5" alt="porta" /></td>
                        </tr>
                        <tr>
                            <td><label for="account">Conta:</label></td>
                            <td><input type="text" name="smtp.account" value="${smtp.account}" id="account" size="30" maxlength="100" /></td>
                        </tr>
                        <tr>
                            <td><label for="password">Senha:</label></td>
                            <td><input type="password" name="smtp.password" value="" id="password" size="14" maxlength="50" /></td>
                        </tr>
                        <tr>
                            <td><label for="tls">TLS:</label></td>
                            <td><input type="checkbox" name="smtp.tls" value="true" id="tls" <c:if test="${smtp.tls}">checked="checked" </c:if>/></td>
                        </tr>
                        <tr>
                            <td><label for="status">Situação:</label></td>
                            <td>
                                <select name="smtp.status" id="status">
                                <c:forEach items="${status}" var="s">
                                    <option value="${s}"<c:if test="${smtp.status == s || (empty smtp.status && s == 'ATIVO')}"> selected="selected"</c:if>>${s.status}</option>
                                </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="hidden" name="smtp.id" value="${smtp.id}" /></td>
                            <td><input type="button" id="doAll" name="admin-smtp-edit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <p>Este servidor não existe ou foi excluído.</p>
            </c:otherwise>
        </c:choose>
        <script type="text/javascript">
            const URLBASE  = "<%= request.getContextPath()%>";
        </script>
    </body>
</html>