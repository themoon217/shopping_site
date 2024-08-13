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
                <h1>おすすめ商品設定</h1>
                <p>ここでは、おすすめ商品を追加したり削除したりできます。<br> 以下のフォームから操作を行ってください。</p>
    
                <div class="form-section">
                    <h2>おすすめ商品追加</h2>
                    <p>リストから追加したい商品を選んでください。</p>
                    <form action="RecommendationServlet" method="post">
                        <label for="notname">商品選択:</label>
                        <input type="text" id="notname" name="notname" list="notitem">
                        <datalist id="notitem">
                            <c:forEach var="notitem" items="${notRecommItem}">
                                <option value="${notitem.name}"></option>
                            </c:forEach>
                        </datalist>
                        <input type="submit" value="おすすめ追加">
                    </form>
                </div>
    
                <div class="form-section">
                    <h2>おすすめ商品削除</h2>
                    <p>リストから削除したい商品を選んでください。</p>
                    <form action="NotRecommendationServlet" method="post">
                        <label for="name">商品選択:</label>
                        <input type="text" id="name" name="name" list="item">
                        <datalist id="item">
                            <c:forEach var="item" items="${recommItem}">
                                <option value="${item.name}"></option>
                            </c:forEach>
                        </datalist>
                        <input type="submit" value="おすすめ削除">
                    </form>
                </div>
            </div>
        </main>
    </body>    
</html>