<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<a href="https://www.facebook.com/v2.10/dialog/oauth?client_id=799295533586280&redirect_uri=https://facebook-login-test-borsch.herokuapp.com/facebook">login</a>

<br />
<c:forEach var="pair" items="${map}">
    ${pair.key} -> ${pair.value}
    <Br />
</c:forEach>
</body>
</html>
