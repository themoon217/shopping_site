<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Moon.With</title>
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>

    <body class="mypages">
        <jsp:include page="WEB-INF/jsp/header.jsp" />
        <main class="mypabe-main">
            <h1 class="board-text2">アカウント変更</h1>
            <form class="account-form" action="AccountChangeServlet" method="post">
                <div class="form-group">
                    <label for="newPass">新しいパスワード:</label>
                    <input type="password" id="newPass" name="pass" class="form-control">
                </div>
                <div class="form-group">
                    <label for="newName">新しい名前:</label>
                    <input type="text" id="newName" name="name" class="form-control">
                </div>
                <input type="submit" value="変更">
            </form>
            <c:if test="${not empty errorMessage}">
                <p style="color:red;">${errorMessage}</p>
            </c:if>
            <c:if test="${not empty successMessage}">
                <p style="color:green;">${successMessage}</p>
            </c:if>
        </main>
    </body>
    <footer>
        <jsp:include page="WEB-INF/jsp/footer.jsp" />
    </footer>
</html>