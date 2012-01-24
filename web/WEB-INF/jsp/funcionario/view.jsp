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
    <c:when test="${not empty funcionario}">
        <table>
            <tr>
                <td><strong>Nome:</strong></td>
                <td>${funcionario.nome}</td>

                <td><strong>Data de Nascimento:</strong></td>
                <td><c:if test="${empty funcionario.nascimento}">-</c:if><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.nascimento}" /></td>
            </tr>
            <tr>
                <td><strong>RG:</strong></td>
                <td><c:if test="${empty funcionario.rg}">-</c:if>${funcionario.rg}</td>

                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty funcionario.telefone}">-</c:if>${funcionario.telefone}</td>
            </tr>
            <tr>
                <td><strong>CPF:</strong></td>
                <td><c:if test="${empty funcionario.cpf}">-</c:if>${funcionario.cpf}</td>

                <td><strong>Celular:</strong></td>
                <td><c:if test="${empty funcionario.celular}">-</c:if>${funcionario.celular}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty funcionario.email}">-</c:if>${funcionario.email}</td>

                <td><strong>Data de Admissão:</strong></td>
                <td><c:if test="${empty funcionario.admissao}">-</c:if><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.admissao}" /></td>
            </tr>
            <tr>
                <td><strong>Perfil:</strong></td>
                <td>${funcionario.perfil.userRole}</td>

                <td><strong>Data de Demissão:</strong></td>
                <td><c:if test="${empty funcionario.demissao}">-</c:if><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.demissao}" /></td>
            </tr>
            <tr>
                <td><strong>Situação:</strong></td>
                <td>${funcionario.status.status}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Logradouro:</strong></td>
                <td><c:if test="${empty funcionario.endereco.logradouro}">-</c:if>${funcionario.endereco.logradouro}</td>
                <td><strong>Número:</strong></td>
                <td><c:if test="${empty funcionario.endereco.numero}">-</c:if>${funcionario.endereco.numero}</td>
            </tr>
            <tr>
                <td><strong>Complemento:</strong></td>
                <td><c:if test="${empty funcionario.endereco.complemento}">-</c:if>${funcionario.endereco.complemento}</td>
                <td><strong>Estado:</strong></td>
                <td><c:if test="${empty funcionario.endereco.uf}">-</c:if>${funcionario.endereco.uf}</td>
            </tr>
            <tr>
                <td><strong>Bairro:</strong></td>
                <td><c:if test="${empty funcionario.endereco.bairro}">-</c:if>${funcionario.endereco.bairro}</td>
                <td><strong>Cidade:</strong></td>
                <td><c:if test="${empty funcionario.endereco.cidade}">-</c:if>${funcionario.endereco.cidade}</td>
            </tr>
            <tr>
                <td><strong>CEP:</strong></td>
                <td><c:if test="${empty funcionario.endereco.cep}">-</c:if>${funcionario.endereco.cep}</td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Unidade:</strong></td>
                <td><c:if test="${empty funcionario.unidade.razaoSocial}">-</c:if>${funcionario.unidade.razaoSocial}</td>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty funcionario.unidade.email}">-</c:if>${funcionario.unidade.email}</td>
            </tr>
            <tr>
                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty funcionario.unidade.telefone}">-</c:if>${funcionario.unidade.telefone}</td>
                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty funcionario.unidade.fax}">-</c:if>${funcionario.unidade.fax}</td>
            </tr>
            <tr>
                <td><strong>Matriz:</strong></td>
                <td>
                <c:choose>
                    <c:when test="${funcionario.unidade.matriz == true}">Sim</c:when>
                    <c:otherwise>Não</c:otherwise>
                </c:choose>
                </td>
            </tr>
        </table>
        <hr />
        <table>
            <tr>
                <td><strong>Setor:</strong></td>
                <td><c:if test="${empty funcionario.setor.nome}">-</c:if>${funcionario.setor.nome}</td>

                <td><strong>Telefone:</strong></td>
                <td><c:if test="${empty funcionario.setor.telefone}">-</c:if>${funcionario.setor.telefone}</td>
            </tr>
            <tr>
                <td><strong>E-mail:</strong></td>
                <td><c:if test="${empty funcionario.setor.email}">-</c:if>${funcionario.setor.email}</td>

                <td><strong>Fax:</strong></td>
                <td><c:if test="${empty funcionario.setor.fax}">-</c:if>${funcionario.setor.fax}</td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <p>Este funcionário não existe ou foi excluído.</p>
    </c:otherwise>
</c:choose>
    </body>
</html>