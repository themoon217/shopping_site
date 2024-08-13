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
<meta charset="UTF-8">
<title>Insert title here</title>
	<body>
		<jsp:include page="WEB-INF/jsp/header.jsp" />
		<main>
			<h3>MEN 人気ランキング TOP3</h3>
			<div class="searchResultItems">
				<c:forEach var="rankingItem" items="${menTopItems}">
				<c:set var="rank" value="${menRankMap[rankingItem]}"/>
				<div class="searchResultItem">
					<span class="material-symbols-outlined" style="color: gold; text-shadow: 1px 2px 3px black;">
						social_Leaderboard
					</span>
					No. ${rank}
					<a href="Item?id=${rankingItem.item.id}">
						<img src="img/${rankingItem.item.imgPath}" alt=""><br>
					</a>
					<p class="item-text">${rankingItem.item.name}</p>
					<div style="display: flex; justify-content: space-between;">
						<h2>¥ ${rankingItem.item.price}</h2>
					</div>
				</div>
				</c:forEach>
			</div>
			<h3>WOMEN 人気ランキング TOP3</h3>
			<div class="searchResultItems">
				<c:forEach var="rankingItem" items="${womenTopItems}">
				<c:set var="rank" value="${womenRankMap[rankingItem]}"/>
				<div class="searchResultItem">
					<span class="material-symbols-outlined" style="color: gold; text-shadow: 1px 2px 3px black;">
						social_Leaderboard
					</span>
					No. ${rank}
					<a href="Item?id=${rankingItem.item.id}">
						<img src="img/${rankingItem.item.imgPath}" alt=""><br>
					</a>
					<p class="item-text">${rankingItem.item.name}</p>
					<div style="display: flex; justify-content: space-between;">
						<h2>¥ ${rankingItem.item.price}</h2>
					</div>
				</div>
				</c:forEach>
			</div>

			<!-- 評価と色を追加するときの参考データとして残してます！
			<div class="searchResultItems">
				<div class="searchResultItem">
					<span class="material-symbols-outlined" style="color: gold;text-shadow: 1px 2px 3px black;">
						social_Leaderboard
					</span>No.1
					<a href="item.jsp">
						<img src="img/105M.jpg" alt=""><br>
					</a>
					<p class="item-text">コットンリラックスVネックTシャツ/5部袖</p>
					<p>★4.4 (21)</p>
					<div class="colorList">
						<div class="pallet" style="background-color: white;"></div>
						<div class="pallet" style="background-color: black;"></div>
						<div class="pallet" style="background-color: beige;"></div>
						<div class="pallet" style="background-color: gray;"></div>
					</div>
					<div style="display: flex; justify-content: space-between;">
						<h2>¥1,990</h2>
					</div>
				</div>
			</div> -->
		</main>
		<footer>
			<jsp:include page="WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>