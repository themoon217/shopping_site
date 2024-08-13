<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

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
		<jsp:include page="WEB-INF/jsp/header.jsp"/>
		<main class="cart-main">
			<h3>ショッピングカート</h3>
			<div class="cart-sub-main">
				<div class="purchaseHistory-main">
					<div class="cartItemsList">
						<!-- 繰り返し開始 -->
						<c:forEach var="cartItems" items="${cartList}">
							<div class="item-section">
								<div class="cartItem">
									<!-- <a href=""> -->
										<img src="img/${cartItems.imgPath}" alt="">
									<!-- </a> -->
									<div class="cartInfo">
										<!-- <a href=""> -->
											<p class="cart-item-name">${cartItems.name}</p>
										<!-- </a> -->
										<p>サイズ: &nbsp;&nbsp;&nbsp; ${cartItems.sizeName}</p>
										<p class="color-container">
											カラー: &nbsp;&nbsp;&nbsp;
											<span class="pallet colorList cart-color" style="background-color:${cartItems.colorName}; "></span>
										</p>
										<p>￥ <span class="pricey">${cartItems.price}</span></p>
										<div class="quantity-input">
											<button class="quantity-btn2 decrement">-</button>
											<input type="number" class="nummy origin" value="${cartItems.orders}" name="orIn" min="1" max="${cartItems.stock}" onblur="quack()" onchange="quack()"/>
											<button class="quantity-btn2 increment">+</button>
										</div>
										
									</div>
									<div class="specific-page">
										<form action="CartList" method="post">
											<input type="hidden"  name="iDEL"  value="${cartItems.itemID}">
											<input type="hidden"  name="siDEL" value="${cartItems.sizeID}">
											<input type="hidden"  name="ciDEL" value="${cartItems.colorID}">
											<input type="hidden"  name="INDEL" value="${cartItems.name}">
											<input type="hidden"  name="pDEL"  value="${cartItems.price}">
											<input type="hidden"  name="iPDEL" value="${cartItems.imgPath}">
											<input type="hidden"  name="cNDEL" value="${cartItems.colorName}">
											<input type="hidden"  name="sNDEL" value="${cartItems.sizeName}">
											<button type="submit">
												<img src="./img/delete.svg" alt="delete" class="delete-icon">
											</button>
										</form>	
									</div>		
								</div>
							</div>
						</c:forEach>
						<!-- 繰り返し終了 -->
					</div>
				</div>
				<section class="total">
					<div  class="payment">
						<div class="points-heading">現在のポイント</div>
						<p class="points-amount">${point}</p>
						<p>アイテム数<span id="itemNum">初期値</span>点</p>
						<div class="total-amount">
							<p>合計<br>金額</p>
							<p class="final-amount">￥<span id="totalPrice">初期値</span></p>
						</div>
					</div>
					<form id="checkoutForm" action="ThankYou" method="post" onsubmit="return validateTotalPrice()">
						<!-- 繰り返し開始 -->
						<c:forEach var="cartItems" items="${cartList}">
							<input type="hidden" style="background-color:grey;" class="copy" name="oDEL""> 
							<input type="hidden" name="iDEL" value="${cartItems.itemID}">
							<input type="hidden" name="siDEL" value="${cartItems.sizeID}">
							<input type="hidden" name="ciDEL" value="${cartItems.colorID}">
							
							<input type="hidden" name="INDEL" value="${cartItems.name}">
							<input type="hidden" name="pDEL" value="${cartItems.price}">
							<input type="hidden" name="iPDEL" value="${cartItems.imgPath}">
							
							<input type="hidden" name="cNDEL" value="${cartItems.colorName}">
							<input type="hidden" name="sNDEL" value="${cartItems.sizeName}">
						</c:forEach>
						<!-- 繰り返し終了 -->							          
						<button type="submit" class="order-confirmed">
							注文を確定する
						</button>
					</form>	
				</section>
			</div>
		</main>
		<footer>
			<jsp:include page="WEB-INF/jsp/footer.jsp" />
		</footer>
		<script language="JavaScript" type="text/javascript"> 
			function quack(){
				const iNum = document.getElementsByClassName("nummy");
				const iPri = document.getElementsByClassName("pricey");
				let totalPrice = 0;
				let totalQuantity = 0;
				
				for (let i = 0; i < iPri.length; i++) {
					const quantity = parseInt(iNum[i].value);
					const price = parseInt(iPri[i].textContent.trim());
					totalQuantity += parseInt(iNum[i].value);
					const subtotal = quantity * price;
					totalPrice += subtotal;
				}
				
				const totalPriceElement = document.getElementById("totalPrice");
				totalPriceElement.textContent = totalPrice;
				const totalItemElement = document.getElementById("itemNum");
				totalItemElement.textContent = totalQuantity;
				var originalInputs = document.getElementsByClassName('origin');
				var copyInputs = document.getElementsByClassName('copy');
				
				for (var i = 0; i < originalInputs.length; i++) {
					copyInputs[i].value = originalInputs[i].value;
				}
			}
			function validateTotalPrice() {
				const totalPrice = parseInt(document.getElementById("totalPrice").textContent);
				const userMoney = (${point}); 
				
				if (totalPrice > userMoney) {
					alert("You don't have enough money.");
					return false; // Prevent form submission
				}
				
				return true; // Allow form submission
			}
			window.onload = quack;
		</script>
		<script src="script/quantity-controls.js"></script>
	</body>
</html>