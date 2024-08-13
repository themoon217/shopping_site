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
            <div class="admin-welcome">
                <h1>商品削除</h1>
                <p>こちらのフォームから商品を削除できます。 <br>削除した商品は復元できませんので、ご注意ください。</p><br>
                <form action="ItemDelete" method="post">
                    <label for="itemName">商品選択</label>
                    <input type="text" id="itemName" name="name" list="item">
                    <datalist id="item">
                        <c:forEach var="item" items="${itemList}">
                            <option value="${item.name}"></option>
                        </c:forEach>
                    </datalist>
                    <input type="submit" value="商品削除">
                </form>
            </div>
        </main>
    </body>
</html>