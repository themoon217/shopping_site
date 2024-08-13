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
		<div class="index-main">
			<div class="points-container">
				<div class="points-heading">現在のポイント</div>
				<p class="points-amount">${point}</p>
			</div>
			<div class="bord">
				<div class="text-container">
					<p class="board-text">洗練されたデザイン　　　</p>
					<p class="board-text2">日常に華やかさをプラス</p>
				</div>
				<div class="slideShow scrollUp">
					<div class="slider">
						<img class="slide" src="img/021S_01.jpg" alt="">
						<img class="slide" src="img/011S_01.jpg" alt="">
						<img class="slide" src="img/139S_02.jpg" alt="">
						<img class="slide" src="img/103M.jpg" alt="">
						<img class="slide" src="img/035S_01.jpg" alt="">
						<img class="slide" src="img/166S_01.jpg" alt="">
						<img class="slide" src="img/188S_02.jpg" alt="">
					</div>
					<img class="left arrow" src="img/left.png" alt="">
					<img class="right arrow" src="img/right.png" alt="">
				</div>
			</div>
			<div class="recommendation">
				<p class="striking-text1">おすすめ商品</p>
				<p class="striking-text2">人気のアイテムが勢ぞろい！今すぐチェック！</p>
			</div>
			<div class="bord">
				<c:forEach var="recom" items="${recomList}">
					<div class="item">
						<a href="Item?id=${recom.itemID}">
							<img class="bordImg" src="img/${recom.imgPath}" alt=""><br>
						</a>
						<div class="item-texts">
							<p class="item-text"><c:out value="${recom.name}" /></p>
							<p>￥<c:out value="${recom.price}" /></p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<footer>
			<jsp:include page="WEB-INF/jsp/footer.jsp" />
		</footer>
		<script src="script/script.js"></script>
		<script src="script/script.js"></script>
	</body>
</html>