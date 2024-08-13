<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <h1>ログイン</h1><hr>
        <form action="RegisterServlet">
            ユーザー名<input type="text" class="registerInput"><br>
            パスワード<input type="text" class="registerInput"><br>
            <input type="submit" value="ログイン">
        </form>
    </main>
    <jsp:include page="WEB-INF/jsp/footer.jsp" />
</body>
</html>