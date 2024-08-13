<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <body>
        <jsp:include page="WEB-INF/jsp/header.jsp" />
        <main class="search-results-main">
            <!-- <h1>検索結果</h1> -->
            <c:if test="${not empty errorMessage}">
                <p>${errorMessage}</p>
            </c:if>
            <c:if test="${not empty items}">
                <ul class="image-list">
                    <c:forEach var="item" items="${items}">
                        <li>
                            <a href="Item?id=${item.id}">
                                <img src="./img/${item.imgPath}" alt="${item.name}" class="item-image">
                                <p class="item-name">${item.name}</p>
                            </a>
                            <div class="color-box">
                                <c:forEach var="clr" items="${item.colors}">
                                    <div class="color" > 
                                        <div class="pallet" style="background-color: ${clr};"></div>
                                    </div>
                                </c:forEach> 
                            </div>
                            <p class="price">${item.price}円</p>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${empty items}">
                <p>該当する商品はありません。</p>
            </c:if>
        </main>
        <footer>
            <jsp:include page="WEB-INF/jsp/footer.jsp" />
        </footer>
    </body>
</html>