<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header class="ownerHeader">
	<a href="Main">
        <img class="logo" src="img/MoonWithLogo.png" alt="MoonWithLogo">
    </a>
    <nav>
        <a href="addItem.jsp">
            <span class="material-symbols-outlined">
                add_circle
            </span>
            <span class="rsphidden">
                新商品追加
            </span>
        </a>
        <a href="ItemInformationChange">
            <span class="material-symbols-outlined">
                edit
            </span> 
            <span class="rsphidden">
                商品情報変更
            </span>
        </a>
        <a href="./ItemDelete">
            <span class="material-symbols-outlined">
                delete
            </span>
            <span class="rsphidden">
                商品削除
            </span>
        </a>
        <a href="CheckStock">
            <span class="material-symbols-outlined">
                inventory
            </span>
            <span class="rsphidden">
                在庫確認・追加
            </span>
        </a>
        <a href="./RecommendationServlet">
            <span class="material-symbols-outlined">
                star
            </span>
            <span class="rsphidden">
                おすすめ商品設定
            </span>
        </a>
        
    </nav>
</header>
