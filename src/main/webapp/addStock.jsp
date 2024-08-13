<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Moon.With</title>
        <link rel="stylesheet" href="./css/owner.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <jsp:include page="WEB-INF/jsp/ownerHeader.jsp" />
        <main class="main-container">
            <form action="/submit-your-form-handler" method="post" enctype="multipart/form-data" class="product-form-container">
                商品A<br>
                <div class="color-indicator color-resize" style="background-color: red;"></div>
                <img src="./img/002M.jpg" alt="商品画像" class="product-image">
                <div class="form-group">
                    <label for="size-s">S:</label>
                    <input type="number" id="size-s" name="size-s" min="0" value="0">
                    
                    <label for="size-m">M:</label>
                    <input type="number" id="size-m" name="size-m" min="0" value="0">
                    
                    <label for="size-l">L:</label>
                    <input type="number" id="size-l" name="size-l" min="0" value="0">
                    
                    <label for="size-xl">XL:</label>
                    <input type="number" id="size-xl" name="size-xl" min="0" value="0">
                    
                    <input type="submit" value="在庫追加">
                </div>
            </form>
        </main>
    </body>
</html>