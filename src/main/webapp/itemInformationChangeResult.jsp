<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>商品追加結果</title>
        <link rel="stylesheet" href="./css/owner.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/ownerHeader.jsp" />
        <main>
            <div class="result-container">
                <h1>商品追加結果</h1>
                <c:choose>
                    <c:when test="${param.success == 'true'}">
                        <p class="success-message">商品が正常に追加されました。</p>
                    </c:when>
                    <c:otherwise>
                        <p class="error-message">商品追加に失敗しました。再度お試しください。</p>
                    </c:otherwise>
                </c:choose>
                <a href="ItemInformationChange" class="back-link">戻る</a>
            </div>
        </main>
    </body>
    
</html>