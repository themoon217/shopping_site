<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moon.With</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body class="footer-fixed">
    <jsp:include page="WEB-INF/jsp/header.jsp" />
    <div class="pointup-main">
        <p class="point-text">Point追加</p>
        <form class="point-form" action="AddPointsServlet" method="post">
            <label for="point">
                <p class="point-label">追加ポイント</p>
            </label>
            <input type="text" id="points" name="point" class="form-control">
            <input type="submit" value="追加する" class="point-submit">
        </form>
        <c:if test="${not empty errorMessage}">
            <p style="color:red;">${errorMessage}</p>
        </c:if>
        <c:if test="${not empty successMessage}">
            <p style="color:green;">${successMessage}</p>
        </c:if>
    </div>
    <footer>
        <jsp:include page="WEB-INF/jsp/footer.jsp" />
    </footer>
</body>
</html>