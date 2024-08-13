<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String[] colorList = {
        "white", "lightgray", "silver", "gray", "black", "linen", "ivory", "aliceblue",
        "lavender", "lightsteelblue", "steelblue", "midnightblue", "blue", "lightblue",
        "darkslategray", "green", "darkseagreen", "lime", "olive", "darkkhaki", "beige",
        "tan", "khaki", "yellow", "orange", "darkgoldenrod", "brown", "coral",
        "lightpink", "thistle", "purple", "indigo"
	};
    String[] categories1 = {"メンズ", "レディース"};
    String[] categories2 = {"トップス", "ボトムス", "ワンピース", "グッズ"};
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
            <form action="AddItem" method="post" enctype="multipart/form-data">
                <div class="form-container">
                    <div class="form-item">
                        <label for="itemName">商品名:</label>
                        <input type="text" id="itemName" name="itemName">
                    </div>
                    <div class="form-item">
                        <label for="price">価格:</label>
                        <input type="number" id="price" name="price" value="3000" min="0" step="10" required>
                    </div>
                    <div class="form-item">
                        <label for="fileInput" class="custom-file-upload">
                            画像を選択
                        </label>
                        <input id="fileInput" type="file" name="pict" style="display: none;" />
                    </div>
                </div>
                <h2>色登録</h2>
                <div class="colorList">
                    <% for (String color : colorList) { %>
                        <div class="color">
                            <label for="<%= color %>"><div class="pallet" style="background-color: <%= color %>;"></div></label>
                            <input type="checkbox" id="<%= color %>" name="color" value="<%= color %>">
                        </div>
                    <% } %>
                </div>
                <h2>カテゴリ登録</h2>
                <div class="main-container">
                    <div class="conteiner">
                        <% for (String category : categories1) { %>
                            <div class="category">
                                <input type="radio" id="<%= category %>" name="category1" value="<%= category %>">
                                <label for="<%= category %>"><%= category %></label>
                            </div>
                        <% } %>
                    </div>
                    <div class="conteiner">
                        <% for (String category : categories2) { %>
                            <div class="category">
                                <input type="radio" id="<%= category %>" name="category2" value="<%= category %>">
                                <label for="<%= category %>"><%= category %></label>
                            </div>
                        <% } %>
                    </div>
                    <input type="submit" value="商品追加">
                </div>
                
            </form>
            <%-- <form action="Main" method="post" enctype="multipart/form-data">
                追加する画像:<input type="file" name="pict"><br>
            </form> --%>
        </main>
    </body>
</html>