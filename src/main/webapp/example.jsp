<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

   <style>
    .thumbnail {
      width: 100px;
      height: 100px;
      cursor: pointer;
    }
    #preview img {
      max-width: 100%;
      max-height: 100%;
    }
    #preview {
      width: 300px;
      height: 400px;
      border: 1px solid black;
      overflow: hidden; /* 画像がプレビュー枠内に収まるようにするために追加 */
    }
  </style>
</head>
<body>
  <img class="thumbnail" src="img/002M.jpg" alt="画像1" onclick="showImage(this.src)">
  <img class="thumbnail" src="img/036M.jpg" alt="画像2" onclick="showImage(this.src)">
  <img class="thumbnail" src="img/103M.jpg" alt="画像3" onclick="showImage(this.src)">
  <div id="preview"><img src="img/002M.jpg" alt="画像1" ></div>

  <script>
    function showImage(src) {
      const preview = document.getElementById("preview");
      // プレビュー用のimg要素を作成
      const img = document.createElement("img");
      img.src = src;
      img.alt = "プレビュー画像";
      // 既存の画像を置き換えるため、innerHTMLを使って中身をリセット
      preview.innerHTML = '';
      // プレビューに画像を追加
      preview.appendChild(img);
    }
  </script>
</body>
</html>
