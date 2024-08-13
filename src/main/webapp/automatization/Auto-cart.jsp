<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head><meta charset="UTF-8"></head>

<body>

<!-- 説明のある自動化されたコードはページの下になります、
上からは説明が記載されてないコードになります。 -->


<!-- 説明のない自動化されたコード： -->
<div class="LRDiv">
	<div class="cartItemsList">
		
		<!-- ここから繰り返してください。 -->
		<div class="cartItem">
			<div class="cartItemContent">
				<input type="checkbox" checked>
				<!-- vvv -->
				<img src="CHANGE THE PICTURE" alt="">
				<!-- ^^^ -->
				<div class="cartInfo">
					<!-- vvv -->
					<p>CHANGE THE NAME</p>
					<p>CHANGE THE SIZE</p>
					<p><span class="pallet colorList" 
					style="background-color:CHANGE THE COLOR;overflow: hidden;
					 white-space:nowrap;"></span>
					 <!-- ^^^ -->
				</div>
				<div class="cartInfo2">
					<input type="number"value="1"/>
					<!-- vvv -->
					<h2>CHANGE THE PRICE</h2>
					<!-- ^^^ -->
					<button><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/></svg></button>
				</div>
			</div>
		</div>
		<!-- ここまで繰り返してください。 -->
	<div class="totalPrice">
		<!-- vvv -->
		<p>CHANGE TOTAL PRODUCT NUMBER</p>
		<h1>CHANGE TOTAL PRICE</h1>
		<!-- ^^^ -->
		<button style="width:150px;"><a href="thank.jsp">レジへ</a></button>
	</div>
</div>
<!-- 以上が自動化されたコード -->






<!-- 以下がすべてのリストの範囲を指定するDivになります -->
<div class="LRDiv"> <!-- 左と右のリストで分けるDiv [商品情報と合計金額] -->

	<!-- 以下のdivは、左側で表示されるすべての商品のリストの範囲を指定してます -->
	<div class="cartItemsList">
		　
		<!-- ここから、商品の数だけ繰り返して表示させてください： -->
		
		<!-- 以下のDivは商品一つのリストの形を作ります -->
		<div class="cartItem">

			<!-- 以下のDivは商品一個の内容の位置と画像の位置を設定します： -->
			<div class="cartItemContent">
			
				<input type="checkbox" checked>
				<img src="img/130M.jpg" alt="">
				
				<!-- 以下のDivは商品の内容１：名前、サイズ、色を表示します -->
				<div class="cartInfo">
					<p>コットンオープンカラーポロシャツ</p>
					<p>サイズ: L</p>
					<p><span class="pallet colorList" style="background-color:white;overflow: hidden; white-space:nowrap;"></span>
				</div>
				
				<!-- 以下のDivは商品の内容２：個数、値段、削除ボタンを表示します -->
				<div class="cartInfo2">
					<input type="number"value="1"/>
					<h2>2,990円</h2>
					<button><svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/></svg></button>
				</div>
			</div>
		</div>
	
		<!-- 自動化され例としては以下のようになります： -->
		
		<!-- 固定：-->
		<div class="cartItem">
			<div class="cartItemContent">
				<input type="checkbox" checked>
				
				<!-- 変更：
				<img src="img/147.jpg" alt="">
				 -->
								
				<!-- 固定：-->				
				<div class="cartInfo">
				
				<!-- 変更：　ｐタグの商品名とｐタグのサイズ -->
					<p>商品名</p>
					<p>サイズ:L</p>
					<!-- 変更： style="background-color" の変更-->
					<p><span class="pallet colorList" style="background-color: gray;overflow: hidden; white-space:nowrap;"></span></p>
					
				<!-- 固定：-->
				</div>
				<div class="cartInfo2">
					<input type="number"value="1"/>
					<!-- 変更：ｈ２のお値段の変更-->
					<h2>2,990円</h2>
					<!-- 固定：-->
					<button><svg xmlns="http://www.w3.org/2000/svg" height="20px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/></svg></button>
				</div>
			</div>
		</div>
	</div>
	<!-- 固定：-->
	<div class="totalPrice">
		<!-- 変更：商品の個数合計と、合計金額-->
		<p>Total(2個の商品)</p>
		<h1>￥5,980</h1>
		<!-- 固定：-->		
		<button style="width:150px;"><a href="thank.jsp">レジへ</a></button>
	</div>
</div>

</main>

</body>
</html>