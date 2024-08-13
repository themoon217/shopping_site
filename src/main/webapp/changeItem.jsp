<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String[] colorList = {
        "white", "lightgray", "silver", "gray", "black", "linen", "ivory", "aliceblue",
        "lavender", "lightsteelblue", "steelblue", "midnightblue", "blue", "lightblue",
        "darkslategray", "green", "darkseagreen", "lime", "olive", "darkkhaki", "beige",
        "tan", "khaki", "yellow", "orange", "darkgoldenrod", "brown", "coral",
        "lightpink", "thistle", "purple", "indigo"
    };

    String[] colorData = request.getParameterValues("colorData");
    if (colorData == null) {
        colorData = new String[0];
    }
    
    // メッセージの取得
    String message = (String) request.getAttribute("message");
%>

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
        <main>
            <!-- 色変更フォーム 
            <form action="/submit-your-form-handler" method="post">
                <p>色変更</p>
                <div class="colorList">
                    <% for (String color : colorList) { %>
                        <% String checked = ""; %>
                        <% for (String clr : colorData) { %>
                            <% if (clr != null && clr.equals(color)) { %>
                                <% checked = "checked"; %>
                            <% } %>
                        <% } %>
                        <div class="color">
                            <label for="<%= color %>">
                                <div class="pallet" style="background-color: <%= color %>;"></div>
                            </label>
                            <input type="checkbox" id="<%= color %>" name="colorData" value="<%= color %>" <%= checked %>>
                        </div>
                    <% } %>
                </div>
                <input type="hidden" name="itemID" value="${itemID}">
                <input type="submit" value="色変更">
            </form>-->

            <!-- 値段変更フォーム -->
            <div class="container">
                <h1>商品情報更新</h1>
    
                <div class="form-container">
                    <div class="form-item">
                        <h2>値段変更</h2>
                        <form action="UpdateItemPriceServlet" method="post">
                            <input type="hidden" name="itemID" value="${itemID}">
                            <label for="price">新しい値段:</label>
                            <input type="number" id="price" name="price" value="${itemData.price}" min="0" step="10" required>
                            <input type="submit" value="値段変更">
                        </form>
                    </div>
                </div>
    
                <div class="form-container">
                    <div class="form-item">
                        <h2>画像追加</h2>
                        <form action="AddSubImg" method="post" enctype="multipart/form-data">
                            <label for="pict">追加する画像:</label>
                            <input type="file" id="pict" name="pict" class="custom-file-upload" required>
                            <input type="hidden" name="itemID" value="${itemID}">
                            <input type="submit" value="画像追加">
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>