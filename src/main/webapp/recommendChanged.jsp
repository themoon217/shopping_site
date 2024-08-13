<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>商品追加結果</title>
        <link rel="stylesheet" href="./css/owner.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/ownerHeader.jsp" />
        <main>
            <div class="result-container">
                <h1>おすすめ変更</h1>
                <div>
                    <p class="success-message">おすすめが変更されました</p>
                    <a href="RecommendationServlet" class="back-link">戻る</a>
                </div>
            </div>
        </main>
    </body>
</html>