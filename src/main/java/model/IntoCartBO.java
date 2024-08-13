package model;

import java.io.Serializable;
//こちらはカートリストの画面を表示するためのものです、「注文確認・購入確定画面」いろいろと情報を保存して表示します。
public class IntoCartBO implements Serializable{
		//carts table:
		private String userID;
		private int itemID; 
		private String color;
		private String size;
		private int orders;
		//カートの内容を表示する：色、サイズ、注文数「最初に注文された数値」
		//items table :
		/*private String name;
		private int price;
		private String imgPath;
		//ほかにも商品名・価格と画像を表示させます。
		//stocks table:
		private int stock;
		//カート画面でも注文数を変更できるため、直接在庫数を取得して最大値とする。
		*/
		
		public IntoCartBO() {};
		public IntoCartBO(String userID, int itemID, String color, String size, int orders) { //cart table情報
			//String name, int price, String imgPath, //items table 情報
			//int stock) { //stocks table 情報
			this.userID=userID;
			this.itemID= itemID;
			this.color=color;
			this.size=size;
			this.orders=orders;
			//this.name=name; //items table 情報
			//this.price=price;
			//this.imgPath=imgPath;
			//this.stock=stock;//stocks table 情報
			
		}
		public String getUserID() {
			return userID;
		}
		public int getItemID() {
			return itemID;
		}
		public String getColor() {
			return color;
		}
		public String getSize() {
			return size;
		}
		public int getOrders() {
			return orders;
		}

		/*
		public String getName() {//品名
			return name;
		}
		public int getPrice() {
			return price;
		}
		public String getImgPath() {
			return imgPath;
		}
		public int getStock() {//　在庫数
			return stock;
		}
		*/		
	}