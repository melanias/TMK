<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${empty integerList}"><option value="">--</option></c:when>
    <c:otherwise>
        <c:forEach items="${integerList}" var="month">
            <c:forEach items="${months}" var="m">
                <c:if test="${month == m.index}"><option value="${month}">${m.month}</option></c:if>
            </c:forEach>
        </c:forEach>
    </c:otherwise>
</c:choose>