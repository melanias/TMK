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
    <c:when test="${not empty doador}">
        <table>
            <tr>
                <td><strong>Nome:</strong></td>
                <td>${doador.nome}</td>

                <td><strong>Data de Nascimento:</strong></td>
                <td><c:if test="${empty doador.nascimento}">-</c:if><fmt:formatDate pattern="dd/MM/yyyy" value="${doador.nascimento}" /></td>
            </tr>
            <tr>
                <td><strong>RG:</strong></td>
                <td>${doador.rg}</td>

                <td><strong>CPF:</strong></td>
                <td>${doador.cpf}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td>${doador.email}</td>

                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty doador.telefone}">-</c:if>${doador.telefone}</td>
            </tr>
            <tr>
                <td><strong>Situação:</strong></td>
                <td>${doador.status.status}</td>

                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty doador.celular}">-</c:if>${doador.celular}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Logradouro:</strong></td>
                <td>${doador.endereco.logradouro}</td>

                <td><strong>Número:</strong></td>
                <td><c:if test="${empty doador.endereco.numero}">-</c:if>${doador.endereco.numero}</td>
            </tr>
            <tr>
                <td><strong>Complemento:</strong></td>
                <td><c:if test="${empty doador.endereco.complemento}">-</c:if>${doador.endereco.complemento}</td>

                <td><strong>Estado:</strong></td>
                <td>${doador.endereco.uf}</td>
            </tr>
            <tr>
                <td><strong>Bairro:</strong></td>
                <td>${doador.endereco.bairro}</td>

                <td><strong>Cidade:</strong></td>
                <td>${doador.endereco.cidade}</td>
            </tr>
            <tr>
                <td><strong>CEP:</strong></td>
                <td>${doador.endereco.cep}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Este doador não existe ou foi excluído.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>