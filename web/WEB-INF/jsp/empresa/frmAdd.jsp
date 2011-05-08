<%@ include file="../index/adminHeader.jsp" %>
                <h1>${title}</h1>
<c:if test="${not empty errors}">
                <blockquote>
    <c:forEach items="${errors}" var="error">
                    <p>${error.message}</p>
    </c:forEach>
                </blockquote>
</c:if>
                <form action="<c:url value="/admin/empresa/nova" />" method="post">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td><label for="cnpj">CNPJ:</label></td>
                            <td><input type="text" name="empresa.cnpj" value="${empresa.cnpj}" id="cnpj" size="18" maxlength="18" alt="cnpj" /></td>
                        </tr>
                        <tr>
                            <td><label for="razaoSocial">Razão Social:</label></td>
                            <td><input type="text" name="empresa.razaoSocial" value="${empresa.razaoSocial}" id="razaoSocial" size="50" maxlength="200" /></td>
                        </tr>
                        <tr>
                            <td><label for="nomeFantasia">Nome Fantasia:</label></td>
                            <td><input type="text" name="empresa.nomeFantasia" value="${empresa.nomeFantasia}" id="nomeFantasia" size="50" maxlength="200" /></td>
                        </tr>
                        <tr>
                            <td><label for="site">Site:</label></td>
                            <td><input type="text" name="empresa.site" value="${empresa.site}" id="site" size="50" maxlength="200" /></td>
                        </tr>
                        <tr>
                            <td><label for="email">E-mail:</label></td>
                            <td><input type="text" name="empresa.email" value="${empresa.email}" id="email" size="35" maxlength="100" /></td>
                        </tr>
                        <tr>
                            <td><label for="fone">Telefone:</label></td>
                            <td><input type="text" name="empresa.telefone" value="${empresa.telefone}" id="fone" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="fax">Fax:</label></td>
                            <td><input type="text" name="empresa.fax" value="${empresa.fax}" id="fax" size="14" maxlength="14" alt="phone" /></td>
                        </tr>
                        <tr>
                            <td><label for="situacao">Situação:</label></td>
                            <td>
                                <select name="empresa.status" id="situacao">
                                <c:forEach items="${status}" var="s">
                                    <option value="${s}"<c:if test="${empresa.status == s || (empty empresa.status && s == 'ATIVO')}"> selected="selected"</c:if>>${s.status}</option>
                                </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <hr />
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td><label for="cep">CEP:</label></td>
                            <td>
                                <input type="text" name="empresa.endereco.cep" value="${empresa.endereco.cep}" id="cep" size="9" maxlength="9" alt="cep" />
                                <img src="/TMK/images/loader.gif" id="busy" alt="Procurando..." title="Procurando..." />
                            </td>
                        </tr>
                        <tr>
                            <td><label for="logradouro">Logradouro:</label></td>
                            <td><input type="text" name="empresa.endereco.logradouro" value="${empresa.endereco.logradouro}" id="logradouro" size="50" maxlength="200" readonly="readonly" /></td>
                            <td><label for="numero">Número:</label></td>
                            <td><input type="text" name="empresa.endereco.numero" value="<c:if test="${empresa.endereco.numero > 0}">${empresa.endereco.numero}</c:if>" id="numero" size="10" maxlength="10" alt="numero" /></td>
                        </tr>
                        <tr>
                            <td><label for="complemento">Complemento:</label></td>
                            <td><input type="text" name="empresa.endereco.complemento" value="${empresa.endereco.complemento}" id="complemento" size="50" maxlength="150" /></td>
                            <td><label for="uf">Estado:</label></td>
                            <td><input type="text" name="empresa.endereco.uf" value="${empresa.endereco.uf}" id="uf" size="2" maxlength="2" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td><label for="bairro">Bairro:</label></td>
                            <td><input type="text" name="empresa.endereco.bairro" value="${empresa.endereco.bairro}" id="bairro" size="50" maxlength="100" readonly="readonly" /></td>
                            <td><label for="cidade">Cidade:</label></td>
                            <td><input type="text" name="empresa.endereco.cidade" value="${empresa.endereco.cidade}" id="cidade" size="35" maxlength="100" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Salvar" /></td>
                        </tr>
                    </table>
                </form>
<%@ include file="../index/adminFooter.jsp" %>