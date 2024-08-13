<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Moon.With</title>
        <link rel="stylesheet" href="./css/owner.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/ownerHeader.jsp" />
        <main>
            <h2> 在庫一覧</h2>
            <table border="1">
                <tr>
                    <th>商品名</th>
                    <th>商品画像</th>
                    <th>色</th>
                    <th>S</th>
                    <th>M</th>
                    <th>L</th>
                    <th>XL</th>
                    <th>追加</th>
                </tr>
                <c:forEach var="checkStock" items="${checkStockData}">
                    <tr>
                        <td><c:out value="${checkStock.itemName}" /></td>
                        <td><img src="./img/${checkStock.imgPath}" alt=""></td>
                        <td style="background-color: ${checkStock.color};"></td>
                        <td><c:out value="${checkStock.s}" /></td>
                        <td><c:out value="${checkStock.m}" /></td>
                        <td><c:out value="${checkStock.l}" /></td>
                        <td><c:out value="${checkStock.xl}" /></td>
                        <td>
                            <a href="AddStock?itemID=${checkStock.itemID}&color=${checkStock.colorID}">
                                <span class="material-symbols-outlined black-icon">
                                    add_circle
                                </span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
    </body>
</html>