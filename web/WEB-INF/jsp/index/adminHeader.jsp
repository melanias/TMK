<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/sysadmin.css" />" media="screen" charset="utf-8" />

        <!-- JS -->
        <script type="text/javascript" src="<c:url value="/js/jquery.js" />" charset="utf-8"></script>
        <script type="text/javascript" src="<c:url value="/js/sysadmin.js" />" charset="utf-8"></script>

        <title>${title}</title>
    </head>
    <body>
        <div id="menu">
            <ul>
                <li>
                    <a href="#">Administradores</a>
                    <div>
                        <ul>
                            <li><a href="#">Grupo 1</a></li>
                            <li><a href="#">Grupo 1</a></li>
                            <li><a href="#">Grupo 1</a></li>
                            <li><a href="#">Grupo 1</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">Grupo 2</a></li>
                            <li><a href="#">Grupo 2</a></li>
                            <li><a href="#">Grupo 2</a></li>
                            <li><a href="#">Grupo 2</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a href="#">Empresas</a>
                    <div>
                        <ul>
                            <li><a href="#">Link 1</a></li>
                            <li><a href="#">Link 1</a></li>
                            <li><a href="#">Link 1</a></li>
                            <li><a href="#">Link 1 Link 1 Link 1 Link 1</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>

        <!-- Conte�do Principal -->
        <div id="main">
            <div class="content">