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
            <p class="medium-text">Sing Up</p>
            <p class="usual-text">Create an new account</p>
            <form action="LoginServlet" method="post">
                <label class="usual-text">Username</label>
                <input type="text" class="registerInput" name="userID"><br>
                <label class="usual-text">Password</label>
                <input type="password" class="registerInput" name="pass">
            
                <div class="login-signup-btn">
                    <button type="submit" class="gold-btn2">Log in</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>