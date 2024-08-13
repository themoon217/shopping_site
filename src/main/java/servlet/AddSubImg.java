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

import model.AddSubImgData;
import model.AddSubImgMethod;

@WebServlet("/AddSubImg")
@MultipartConfig
public class AddSubImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

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
		String itemID = request.getParameter("itemID");
		int id = Integer.parseInt(itemID);
		AddSubImgData addSubImgData = new AddSubImgData(id, filename);
		AddSubImgMethod addSubImgMethod = new AddSubImgMethod();
		addSubImgMethod.execute(addSubImgData);
		RequestDispatcher rd = request.getRequestDispatcher("itemInformationChangeResult.jsp");
        rd.forward(request, response);
	}
}
