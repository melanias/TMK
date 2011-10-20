<%@page import="br.org.aappe.erp.bean.Funcionario"%>
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
        <div id="Module" class="corner-bottom-right-6 shadow-3">TMK</div>
        <div id="Version" class="corner-top-left-6 shadow-3">Versão 0.0.3</div>
        <div id="header">
            <div class="content">
                <h1><a href="<c:url value="/" />" accesskey="i"><img src="<c:url value="/images/logo.png" />" title="AAPPE" alt="AAPPE" /></a></h1>
                <c:set var="rightNow" value="<%=new java.util.Date()%>" />
                <!--Aqui em baixo poderia ficar o login e o botão sair do sistema. Ao invez da data! Oq axa?! Abaixo ta nojento o codigo + é ideia! koaekae-->
                <span>Olá, <%= session.getAttribute("funcionario.login") %>  Fulano de Tal <a href="logout">( Sair )</a></span> <br/><br/><br/><br/><br/><br/>
                <span><fmt:formatDate type="date" dateStyle="full" value="${rightNow}" /></span><br/>
                <%@ include file="menu.jsp" %>
            </div>
        </div>

        <!-- Conteúdo Principal -->
        <div id="main">
            <div class="content">