<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<html>
<body>
    <h2>获取name值：${requestScope.name}  ${name}</h2>
    <p>
        <c:forEach items="${users}" var="user">
            ${user.name}--${user.age}--${user.bir} <br>
        </c:forEach>
    </p>

    <p>this is hhh， hello dfsa</p> fsfagf
</body>
</html>
