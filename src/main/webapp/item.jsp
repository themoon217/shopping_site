<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Review" %>

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
            <h2 class="category-name">
                <c:forEach var="category" items="${categoryData.categoryList}">
                    <c:out value="${category}" />
                </c:forEach>
            </h2>
            <section class="item-main">
                <div class="itemData">
                    <!-- サブイメージ -->
                    <div class="subImg">
                        <img class="subimage" src="img/${itemData.imgPath}" alt="">
                        <c:forEach var="simg" items="${subimg.imgList}">
                            <img class="subimage" src="img/${simg}" alt="">
                        </c:forEach>
                    </div>
                    <!-- メインイメージ -->
                    <div id="preview" class="mainImg">
                        <img src="img/${itemData.imgPath}" alt="">
                    </div>
                    <div class="info">
                        <div class="content">
                            <!-- 商品名 -->
                            <p class="product-name">${itemData.name}</p>
                            <!-- 価格 -->
                            <h2>￥${itemData.price}</h2>
                            <!-- ☆評価 -->
                            <p id="star-rating">
                                <fmt:formatNumber value="${itemData.evaluat}" maxFractionDigits="1" /> (${itemData.comments})
                            </p>
                        </div>
                        <div class="item-form">
                            <form action="IntoCart?id=${itemID}" method="post" class="custom-form">
                                <!-- カラーリスト -->
                                <div class="colorList">
                                    <% int i = 0; %>
                                    <c:forEach var="clr" items="${colorData.colorList}">
                                        <div class="color custom-radio">
                                            <label>
                                                <div class="pallet item-pallet" style="background-color: ${clr}; cursor: pointer;"></div>
                                                <input type="radio" name="color" value="${clr}" <%= i==0 ? "checked" : "" %> style="display: none;">
                                            </label>
                                        </div>
                                        <% i++; %>
                                    </c:forEach>                            </div>
                                <!-- サイズ -->
                                <div class="size">
                                    <label class="size-option">
                                        <input type="radio" name="size" value="S" checked>
                                        <span>S</span>
                                    </label>
                                    <label class="size-option">
                                        <input type="radio" name="size" value="M">
                                        <span>M</span>
                                    </label>
                                    <label class="size-option">
                                        <input type="radio" name="size" value="L">
                                        <span>L</span>
                                    </label>
                                    <label class="size-option">
                                        <input type="radio" name="size" value="XL">
                                        <span>XL</span>
                                    </label>
                                </div>
                                <!-- 数量 -->
                                <div class="buy">
                                    <div class="quantity-controls">
                                        <button type="button" class="quantity-btn" id="decrease">−</button>
                                        <input type="text" value="1" name="number" class="number-input">
                                        <button type="button" class="quantity-btn" id="increase">+</button>
                                    </div>
                                    <input type="submit" value="カートに入れる">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <section class="item-reviews">
                <div class="item-text">
                    <p>お客様のレビュー一覧</p>
                </div>
                <c:if test="${not empty reviews}">
                    <c:forEach var="review" items="${reviews}">
                        <div class="review-details">
                            <div class="item-rating">
                                <!-- ☆評価 -->
                                <div class="stars" data-rating="${review.evaluation}"></div>
                            </div>
                            <div class="review-text">
                                <!-- レビュー内容 -->
                                <p id="review">${review.comment}</p>
                                <div class="review-text2">
                                    <p>投稿者：</p>
                                    <p>${review.userId}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty reviews}">
                    <p>レビューはまだありません。</p>
                </c:if>
            </section>
            <!-- 余裕があれば作成するよ！
            <div class="connectionItems">
                <h2>関連商品</h2>
                <div class="bord2">
                    <div class="connectionItem">
                        <img src="img/147M.jpg" alt=""><br>
                        <div>
                            <p>ブロードシャツ/ストライプ/ボタンダウン</p>
                            <p>¥2,990</p>
                        </div>
                    </div>
                    <div class="connectionItem">
                        <img src="img/163M.jpg" alt=""><br>
                        <div>
                            <p>タックワイドパンツ</p>
                            <p>¥3,990</p>
                        </div>
                    </div>
                    <div class="connectionItem">
                        <img src="img/151M.jpg" alt=""><br>
                        <div>
                            <p>リラックスアンクルジーンズ/ワイドフィット</p>
                            <p>¥2,990</p>
                        </div>
                    </div>
                </div>
            </div> -->

        </main>
        <jsp:include page="WEB-INF/jsp/footer.jsp" />
        <script>
            // JavaScriptを使ってサムネイル画像にクリックイベントを追加する
            document.addEventListener("DOMContentLoaded", function() {
                const subImages = document.querySelectorAll(".subimage");

                subImages.forEach(function(image) {
                    image.addEventListener("click", function() {
                        const imageUrl = this.src;
                        const previewImage = document.querySelector("#preview img");
                        previewImage.src = imageUrl;
                    });
                });
            });

            //評価を★で表示する➀
            document.addEventListener('DOMContentLoaded', function() {
                var starRatingElement = document.getElementById('star-rating');
                var evaluat = ${itemData.evaluat}; // itemData.evaluatの評価値をJavaScriptの変数に代入
                // 星の表現と評価の数値を生成する関数
                function generateStarRating() {
                    var starRatingHtml = '';
                    // ★の数を計算
                    var fullStars = Math.floor(evaluat);
                    var emptyStars = 5 - fullStars; // 残りの☆の数
                // ★を追加
                    for (var i = 0; i < fullStars; i++) {
                        starRatingHtml += '★';
                    }
                    // ☆を追加
                    for (var j = 0; j < emptyStars; j++) {
                        starRatingHtml += '☆';
                    }
                    // 評価の数値を追加
                    starRatingHtml += ' ' + evaluat.toFixed(1);
                    return starRatingHtml;
                }
                // ページ読み込み後に実行
                starRatingElement.textContent = generateStarRating();
            });
            document.addEventListener('DOMContentLoaded', function() {
                const starsElements = document.querySelectorAll('.stars');
                starsElements.forEach(starsElement => {
                    const rating = parseInt(starsElement.getAttribute('data-rating'));
                    
                    // 星の数を生成
                    let starsHTML = '';
                    for (let i = 1; i <= 5; i++) {
                        if (i <= rating) {
                            starsHTML += '<span class="filled">★</span>';
                        } else {
                            starsHTML += '<span>★</span>';
                        }
                    }
                    // 星のHTMLを要素にセット
                    starsElement.innerHTML = starsHTML;
                });
            });
        </script>
        <script src="script/quantity-color-selection.js"></script>
    </body>
</html>