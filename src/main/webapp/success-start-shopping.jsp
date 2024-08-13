<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Login" %>
<%
Login userID=(Login)session.getAttribute("userID");
%>
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
        <div class="check">
            <img src="./img/check.svg" alt="check">
        </div>
        <div class="Light-grey-square">
            <p class="large-text">Successfull!</p>
            <p class="small-text">You have succeesfully registerd in <br>
                our app and start working in it.</p>
            <div class="login-signup-btn2">
                <a href="Main"><button class="gold-btn">Start Shopping</button></a>
            </div>
        </div>
    </main>
</body>
</html>