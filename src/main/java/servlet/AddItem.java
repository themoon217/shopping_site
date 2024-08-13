package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.AddItemData;
import model.AddItemMethod;

@WebServlet("/AddItem")
@MultipartConfig
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("itemName");
		String price = request.getParameter("price");
		// String imgPath = request.getParameter("imgPath");

		String filename;
		Part part=request.getPart("pict");
		if (part.getSize()==0) {
			filename = "noimg.png";
		} else {
			//ファイル名を取得
			//String filename=part.getSubmittedFileName();//ie対応が不要な場合
			filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
			//アップロードするフォルダ
			String path = getServletContext().getRealPath("/img");
			//実際にファイルが保存されるパス確認
			System.out.println(path);
			//書き込み
			part.write(path+File.separator+filename);
		}
		// request.setAttribute("imgPath", imgPath);

		String[] colorList = request.getParameterValues("color");
		String category1 = request.getParameter("category1");
		String category2 = request.getParameter("category2");
		
        // フォーム入力のバリデーション
        if (name == null || name.isEmpty() || price == null || price.isEmpty() || category1 == null || category2 == null) {
            // エラーメッセージを設定
            request.setAttribute("success", false);
            request.setAttribute("message", "必須項目が入力されていません。");
            
            // エラー画面にフォワード
            RequestDispatcher rd = request.getRequestDispatcher("addItemResult.jsp");
            rd.forward(request, response);
            return; // 必ずこのメソッドを終了することで、以降の処理が行われないようにする
        }
		
		AddItemData addItem = new AddItemData(name, Integer.parseInt(price), filename, colorList, new String[]{category1, category2});
		AddItemMethod addItemMethod = new AddItemMethod();
		boolean done = addItemMethod.execute(addItem);
		
        if (done) {
            request.setAttribute("success", true);
            request.setAttribute("message", "商品が追加されました。");
        } else {
            request.setAttribute("success", false);
            request.setAttribute("message", "商品の追加に失敗しました。");
        }
		
		RequestDispatcher rd = request.getRequestDispatcher("addItemResult.jsp");
        rd.forward(request, response);
	}
	
	
}
