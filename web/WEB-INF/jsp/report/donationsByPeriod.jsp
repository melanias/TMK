<%@ include file="../index/header.jsp" %>
                <h1>${title}</h1>
                <form action="${linkTo[ReportController].pdf}" method="post">
                    <table>
                        <tr>
                            <td><label>Ano</label></td>
                            <td><label>Mês</label></td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <select name="year" id="year"<c:if test="${empty years}"> disabled="disabled"</c:if>>
                                    <option value="">--</option>
                                <c:forEach items="${years}" var="y">
                                    <option value="${y}"<c:if test="${year == y}"> selected="selected"</c:if>>${y}</option>
                                </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select name="months[]" id="months" multiple="multiple" size="3" disabled="disabled">
                                    <option value="">--</option>
                                </select>
                            </td>
                            <td valign="top">
                                <input type="submit" value="PDF" />
                                <img src="<c:url value="/images/loader.gif" />" id="busy" alt="Procurando..." title="Procurando..." />
                            </td>
                            <c:if test="${not empty errors}">
                            <td valign="top">
                                <c:forEach items="${errors}" var="error">
                                ${error.message}
                                </c:forEach>
                            </td>
                            </c:if>
                        </tr>
                    </table>
                </form>
<%@ include file="../index/footer.jsp" %>