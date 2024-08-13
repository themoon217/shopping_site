<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <main class="history">
            <h3>これまでに購入した商品</h3>
            <div class="purchaseHistory-main">
                <c:if test="${not empty purchaseHistory}">
                    <c:forEach var="history" items="${purchaseHistory}">
                        <div class="item-section">
                            <div class="cartItem">
                                <a href="Item?id=${history.itemId}">
                                    <img src="img/${history.imgPath}" alt="${history.itemName}">
                                </a>
                                <div class="cartInfo">
                                    <a href="Item?id=${history.itemId}">
                                        <p class="cart-item-name">${history.itemName}</p>
                                    </a>
                                    <p>￥${history.price}</p>
                                    <p>サイズ: &nbsp;&nbsp;&nbsp; ${history.sizeName}</p>
                                    <p class="color-container">
                                        カラー: &nbsp;&nbsp;&nbsp;
                                        <span class="pallet colorList cart-color" style="background-color:${history.colorName};"></span>
                                    </p>
                                    <p>注文日: <span class="order-date">${history.date}</span></p>
                                </div>
                                <div class="cart-review">
                                    <a href="review.jsp?id=${history.itemId}">レビューの投稿 </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty purchaseHistory}">
                    <p>購入履歴がありません。</p>
                </c:if>
            </div>
        </main>
        <footer>
            <jsp:include page="WEB-INF/jsp/footer.jsp" />
        </footer>
        <script>
            document.addEventListener("DOMContentLoaded", function() {
            // すべての注文日要素を取得
            var dateElements = document.querySelectorAll('.order-date');
            // 各要素に対して日付をフォーマット
            dateElements.forEach(function(element) {
                var dateStr = element.textContent;
                var date = new Date(dateStr);
                if (!isNaN(date)) { // 有効な日付かどうか確認
                    var formattedDate = date.toISOString().split('T')[0]; // YYYY-MM-DD 形式
                    element.textContent = formattedDate;
                    }
                });
            });
        </script>
    </body>
</html>