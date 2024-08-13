<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Moon.With</title>
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body class="into-cart-main">
        <jsp:include page="WEB-INF/jsp/header.jsp" />
        <main class="cart-page-main">
            <div class="cart-content">
                <img src="./img/cart.svg" alt="cart">
                <p class="cart-page-large-text">カートに追加しました</p>
                <a href="CartList" class="cart-page-link">カートを見る</a>
            </div>
        </main>
        <footer>
            <jsp:include page="WEB-INF/jsp/footer.jsp" />
        </footer>
    </body>
</html>