<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty setor}">
        <table>
            <tr>
                <td><strong>Nome:</strong></td>
                <td>${setor.nome}</td>
            </tr>
            <tr>
                <td><strong>Sigla:</strong></td>
                <td>${setor.sigla}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty setor.email}">-</c:if>${setor.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty setor.telefone}">-</c:if>${setor.telefone}</td>
            </tr>
            <tr>
                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty setor.fax}">-</c:if>${setor.fax}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Filial:</strong></td>
                <td><c:if test="${empty setor.filial.nome}">-</c:if>${setor.filial.nome}</td>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty setor.filial.email}">-</c:if>${setor.filial.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td>${setor.filial.telefone}</td>
                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty setor.filial.fax}">-</c:if>${setor.filial.fax}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Responsável:</strong></td>
                <td><c:if test="${empty setor.responsavel.nome}">-</c:if>${setor.responsavel.nome}</td>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty setor.responsavel.email}">-</c:if>${setor.responsavel.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty setor.responsavel.telefone}">-</c:if>${setor.responsavel.telefone}</td>
                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty setor.responsavel.celular}">-</c:if>${setor.responsavel.celular}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Este setor não existe ou foi excluído.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>