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
	<body class="mypages">
		<jsp:include page="WEB-INF/jsp/header.jsp" />
		<main class="search-main">
			<form action="SearchItemServlet" method="post">
				<p class="form-group">カテゴリから探す</p>
				<div class="radio-container">
					<label class="radio-button">
						<input type="radio" name="sex" value="1">
						<span> MEN </span>
					</label>
					<label class="radio-button">
						<input type="radio" name="sex" value="2">
						<span>WOMEN</span>
					</label>
				</div>
				<div class="radio-container">
					<label class="radio-button">
						<input type="radio" name="categoryId" value="3">
						<span>トップス</span>
					</label>
					<label class="radio-button">
						<input type="radio" name="categoryId" value="4">
						<span>ボトムス</span>
					</label>
					<label class="radio-button">
						<input type="radio" name="categoryId" value="5">
						<span>ワンピース</span>
					</label>
					<label class="radio-button">
						<input type="radio" name="categoryId" value="6">
						<span>グッズ</span>
					</label>
				</div>
				<input type="submit" value="カテゴリ検索">
				<div class="form-group">
					<label for="keyword">キーワードで探す</label>
					<input type="text" name="keyword" id="keyword" class="form-control" placeholder="どんな商品をお探しですが？">
				</div>
				<input type="submit" value="キーワード検索">
			</form>
			<% if (request.getAttribute("errorMessage") != null) { %>
				<p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
			<% } %>
		</main>
		<footer>
			<jsp:include page="WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>