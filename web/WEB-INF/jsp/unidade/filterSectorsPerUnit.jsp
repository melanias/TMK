<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <option value="">--</option>
<c:forEach items="${setorList}" var="setor">
    <option value="${setor.id}">${setor.nome}</option>
</c:forEach>