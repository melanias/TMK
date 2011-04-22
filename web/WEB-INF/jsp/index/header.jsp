<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/aappe.css" />" media="screen" charset="utf-8" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/aappe.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <div id="header">
            <div class="content">
                <h1><a href="<c:url value="/" />" title="AAPPE" accesskey="a">AAPPE</a></h1>
                <c:set var="rightNow" value="<%=new java.util.Date()%>" />
                <span><fmt:formatDate type="date" dateStyle="full" value="${rightNow}" /></span>
                <%@ include file="menu.jsp" %>
            </div>
        </div>

        <!-- Conteúdo Principal -->
        <div id="main">
            <div class="content">