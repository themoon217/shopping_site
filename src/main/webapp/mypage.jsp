<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My page</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" type="text/css" href="https://coco-factory.jp/ugokuweb/wp-content/themes/ugokuweb/data/reset.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/style2.css">
</head>

<body class="mypages">
	<jsp:include page="WEB-INF/jsp/header.jsp" />
    <main class="mypabe-main">
        <h1 class="board-text2">マイページ</h1>
        <nav class="mypage">
            <ul>
                <li><a href="accountchange.jsp">アカウント変更</a></li>
                <li class="has-child"><a href="#">ポイントを貯める</a>
                    <ul>
                        <li><a href="pointup.jsp">pointup</a></li>
                        <!--  <li><a href="#">ゲーム2</a></li>
                        <li><a href="#">ゲーム3</a></li>
                        <li><a href="#">ゲーム4</a></li>
                        <li><a href="#">ゲーム5</a></li>-->
                    </ul>
                </li>
                <li><a href="logout.jsp">ログアウト</a></li>
                <li><a href="userdelete.jsp">退会</a></li>
            </ul>
        </nav>
    </main>
    <footer>
        <jsp:include page="WEB-INF/jsp/footer.jsp" />
    </footer>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://coco-factory.jp/ugokuweb/wp-content/themes/ugokuweb/data/5-1-1/js/5-1-1.js"></script>
    </body>
</html>