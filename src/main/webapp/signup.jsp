<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moon.With</title>
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>
    <main>
        <div class="logo2">
			<a href="login-or-signup.jsp">
                <img src="./img/logo.svg" alt="logo2">
			</a>
        </div>
        <div class="Light-grey-square">
            <p class="medium-text">Welcom!</p>
            <p class="usual-text">please login or  sign up to continue our app</p>
            
            <form action="RegisterServlet" method="post">
                <label class="usual-text">Username</label>
                <input type="text" class="registerInput" name="userID"><br>
                <label class="usual-text">Password</label>
                <input type="password" class="registerInput" name="pass">
                <label class="usual-text">本名　(※公開されることはありません)</label>
                <input type="text" class="registerInput" name="name">
            
            	<div class="login-signup-btn">
						<button type="submit" class="gray-btn2">Sign up</button>
            	</div>
            </form>
                   <%-- エラーメッセージの表示 --%>
    <p style="color:red;">
        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
    </p>
        </div>
    </main>
</body>
</html>