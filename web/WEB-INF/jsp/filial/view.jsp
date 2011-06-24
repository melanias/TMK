<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
        <meta name="author" content="Phelipe Melanias" />

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/iframe.css" />" media="screen" charset="utf-8" />

        <title>${title}</title>
    </head>
    <body>
<c:choose>
    <c:when test="${not empty filial}">
        <table>
            <tr>
                <td><strong>CNPJ:</strong></td>
                <td>${filial.cnpj}</td>
            </tr>
            <tr>
                <td><strong>Nome:</strong></td>
                <td>${filial.nome}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty filial.email}">-</c:if>${filial.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td>${filial.telefone}</td>
            </tr>
            <tr>
                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty filial.fax}">-</c:if>${filial.fax}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Logradouro:</strong></td>
                <td><c:if test="${empty filial.endereco.logradouro}">-</c:if>${filial.endereco.logradouro}</td>
                <td><strong>Número:</strong></td>
                <td><c:if test="${empty filial.endereco.numero}">-</c:if>${filial.endereco.numero}</td>
            </tr>
            <tr>
                <td><strong>Complemento:</strong></td>
                <td><c:if test="${empty filial.endereco.complemento}">-</c:if>${filial.endereco.complemento}</td>
                <td><strong>Estado:</strong></td>
                <td><c:if test="${empty filial.endereco.uf}">-</c:if>${filial.endereco.uf}</td>
            </tr>
            <tr>
                <td><strong>Bairro:</strong></td>
                <td><c:if test="${empty filial.endereco.bairro}">-</c:if>${filial.endereco.bairro}</td>
                <td><strong>Cidade:</strong></td>
                <td><c:if test="${empty filial.endereco.cidade}">-</c:if>${filial.endereco.cidade}</td>
            </tr>
            <tr>
                <td><strong>CEP:</strong></td>
                <td><c:if test="${empty filial.endereco.cep}">-</c:if>${filial.endereco.cep}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Esta filial não existe ou foi excluída.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>