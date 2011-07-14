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
        <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.datepicker-pt-BR.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <div id="menu">
            <ul>
                <li>
                    <a href="#">Administradores</a>
                    <div>
                        <ul>
                            <li><a href="<c:url value="/admin/user" />">Listar</a></li>
                            <li><a href="<c:url value="/admin/user/novo" />">Cadastrar</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a href="#">Empresas</a>
                    <div>
                        <ul>
                            <li><a href="<c:url value="/admin/empresa" />">Listar</a></li>
                            <li><a href="<c:url value="/admin/empresa/nova" />">Cadastrar</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>

        <!-- Conte�do Principal -->
        <div id="main">
            <div class="content">