<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/aappe.css" />" media="screen" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/aappe.js" />"></script>

        <title>${title}</title>
    </head>
    <body>
        <div id="header">
            <div id="content">
                <h1><a href="<c:url value="/" />" title="AAPPE" accesskey="a">AAPPE</a></h1>
                <%@ include file="menu.jsp" %>
            </div>
        </div>

        <!-- Conteúdo Principal -->
        <div id="main">