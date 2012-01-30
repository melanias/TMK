<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/aappe.css" />" media="screen" charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/js/highslide/highslide.css" />" media="screen" charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/smoothness/smoothness.css" />" media="screen" charset="utf-8" />

        <!-- Font -->
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold" media="screen" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/aappe.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/meiomask.js" />" charset="utf-8"></script>

        <!-- jQuery UI -->
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.datepicker-pt-BR.js" />" charset="utf-8"></script>

        <!-- Highslide -->
        <script type="text/javascript" src="<c:url value="/js/highslide/highslide-full.min.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/highslide/highslide-configuration.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <div id="Module" class="corner-bottom-right-6 shadow-3"><a href="${linkTo[IndexController].admin}">ADMIN</a></div>
        <div id="Module2" class="corner-bottom-right-6 shadow-3"><a href="${linkTo[IndexController].index}">TMK</a></div>
        <div id="Version" class="corner-top-left-6 shadow-3">Vers�o 0.0.3</div>
        <div id="header">
            <div class="content">
                <h1><a href="${linkTo[IndexController].index}" accesskey="i"><img src="<c:url value="/images/logo.png" />" title="AAPPE" alt="AAPPE" /></a></h1>

                <div id="info">
                    <p class="data"><fmt:formatDate type="date" dateStyle="full" value="<%=new java.util.Date()%>" /></p>
                    <p class="greetings">Ol�, <strong>${employeeSession.nome}</strong>! <a href="${linkTo[LoginController].logout}">(&nbsp;Sair&nbsp;)</a></p>
                </div>
                <%@ include file="menu.jsp" %>
            </div>
        </div>

        <!-- Conte�do Principal -->
        <div id="main">
            <div class="content">