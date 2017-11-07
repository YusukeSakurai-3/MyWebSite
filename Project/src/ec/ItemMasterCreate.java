package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.ItemDataBeans;
import dao.ItemMasterDAO;

/**
 * Servlet implementation class ItemMasterCreate
 */
@WebServlet("/ItemMasterCreate")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class ItemMasterCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
	    request.setCharacterEncoding("UTF-8");

	    ItemDataBeans idb = new ItemDataBeans();
	    idb.setName("");
	    idb.setDetail("");
	    idb.setPrice(-1);
	    request.setAttribute("idb",idb);
		request.getRequestDispatcher(EcHelper.ITEM_MASTER_CREATE_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doIPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		try {

			//アップロードされた画像ファイルを取得
			String fileName = "";
			 Part part = request.getPart("uploadFile");
			 fileName = EcHelper.getFileName(part);
			 //画像ファイルがあるかないか
			 if(!fileName.equals("")) {
			     part.write(EcHelper.UPLOAD_PAGE+ "/" + fileName);
			 }else {
				 fileName = "noimage.png";
			 }



			//登録したい商品の情報を取得

			int itemPrice = EcHelper.parseInt(request.getParameter("price"),-1);
			String itemName = (String)request.getParameter("itemName");
			String itemDetail = (String)request.getParameter("itemDetail");

			/*
			if(itemName==null||itemName.length()==0||itemName.equals("")) {
		        エラー発生させる
		    }
		    */

			ItemDataBeans idb = new ItemDataBeans();

			idb.setName(itemName);
			idb.setDetail(itemDetail);
			idb.setPrice(itemPrice);
			idb.setFileName(fileName);


			//商品を登録する
			String itemCheck = ItemMasterDAO.getInstance().insertItem(idb);
			if(itemCheck.equals("success")) {
				//成功したら商品マスタ一覧画面に戻る
				session.setAttribute("itemCreateMessage", "商品を登録しました");
				response.sendRedirect("ItemMaster");
			}else {
			    //失敗した場合
				request.setAttribute("idb",idb);
			    request.setAttribute("itemCreateError",itemCheck);
			    request.getRequestDispatcher(EcHelper.ITEM_MASTER_CREATE_PAGE).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
