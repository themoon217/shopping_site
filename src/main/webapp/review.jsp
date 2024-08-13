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
<body>
    <jsp:include page="WEB-INF/jsp/header.jsp" />
    <main>
        <h2>レビューを投稿する</h2>
        <form action="ReviewServlet" method="post">
            <div class="review-container">
                <p>評価を選択してください</p>
                <div class="rating">
                    <input type="radio" id="star5" name="evaluation" value="5">
                    <label for="star5">★</label>
                    <input type="radio" id="star4" name="evaluation" value="4">
                    <label for="star4">★</label>
                    <input type="radio" id="star3" name="evaluation" value="3">
                    <label for="star3">★</label>
                    <input type="radio" id="star2" name="evaluation" value="2">
                    <label for="star2">★</label>
                    <input type="radio" id="star1" name="evaluation" value="1">
                    <label for="star1">★</label>
                </div>
                <p>本文</p>
                <textarea name="review" id="review" class="textarea" placeholder="購入商品の感想を教えてください。"></textarea>
                <input type="hidden" name="itemID" value="${param.id}"> <!-- 隠しフィールド -->
                <input type="submit" value="レビューを投稿する">
                <a href="PurchaseHistoryServlet" class="back-link">前の画面に戻る</a>
            </div>
        </form>
        </div>
        
            <c:if test="${not empty errorMsg}">
                <p class="error-message">${errorMsg}</p>
            </c:if>
            <c:if test="${not empty successMsg}">
                <p class="success-message">${successMsg}</p>
            </c:if>
    </main>
    <jsp:include page="WEB-INF/jsp/footer.jsp" />
</body>
</html>