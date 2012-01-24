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
    <c:when test="${not empty unidade}">
        <table>
            <tr>
                <td><strong>CNPJ:</strong></td>
                <td>${unidade.cnpj}</td>
            </tr>
            <tr>
                <td><strong>Razão social:</strong></td>
                <td>${unidade.razaoSocial}</td>
            </tr>
            <tr>
                <td><strong>Nome fantasia:</strong></td>
                <td><c:if test="${empty unidade.nomeFantasia}">-</c:if>${unidade.nomeFantasia}</td>
            </tr>
            <tr>
                <td><strong>Site:</strong></td>
                <td><c:if test="${empty unidade.site}">-</c:if>${unidade.site}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty unidade.email}">-</c:if>${unidade.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty unidade.telefone}">-</c:if>${unidade.telefone}</td>
            </tr>
            <tr>
                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty unidade.fax}">-</c:if>${unidade.fax}</td>
            </tr>
            <tr>
                <td><strong>Matriz:</strong></td>
                <td>
                <c:choose>
                    <c:when test="${unidade.matriz == true}">Sim</c:when>
                    <c:otherwise>Não</c:otherwise>
                </c:choose>
                </td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Logradouro:</strong></td>
                <td><c:if test="${empty unidade.endereco.logradouro}">-</c:if>${unidade.endereco.logradouro}</td>
                <td><strong>Número:</strong></td>
                <td><c:if test="${empty unidade.endereco.numero}">-</c:if>${unidade.endereco.numero}</td>
            </tr>
            <tr>
                <td><strong>Complemento:</strong></td>
                <td><c:if test="${empty unidade.endereco.complemento}">-</c:if>${unidade.endereco.complemento}</td>
                <td><strong>Estado:</strong></td>
                <td><c:if test="${empty unidade.endereco.uf}">-</c:if>${unidade.endereco.uf}</td>
            </tr>
            <tr>
                <td><strong>Bairro:</strong></td>
                <td><c:if test="${empty unidade.endereco.bairro}">-</c:if>${unidade.endereco.bairro}</td>
                <td><strong>Cidade:</strong></td>
                <td><c:if test="${empty unidade.endereco.cidade}">-</c:if>${unidade.endereco.cidade}</td>
            </tr>
            <tr>
                <td><strong>CEP:</strong></td>
                <td><c:if test="${empty unidade.endereco.cep}">-</c:if>${unidade.endereco.cep}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Esta unidade não existe ou foi excluída.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>