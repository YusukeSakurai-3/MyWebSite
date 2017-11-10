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
import dao.ItemDAO;
import dao.ItemMasterDAO;

/**
 * Servlet implementation class ItemMasterUpdate
 */
@WebServlet("/ItemMasterUpdate")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class ItemMasterUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {

			//選択された商品のIDを型変換し利用
			int  id = Integer.parseInt(request.getParameter("item_id"));

			ItemDataBeans item = ItemDAO.getItemByItemID(id);
			int purchaseNum = ItemMasterDAO.getInstance().getCountByItemId(id);

			request.setAttribute("purchaseNum", purchaseNum);
			request.setAttribute("item",item);
			request.getRequestDispatcher(EcHelper.ITEM_MASTER_UPDATE_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		try {
			//元の画像ファイルを取得
			String preFileName = (String)request.getParameter("preFileName");
			//アップロードされた画像ファイルを取得
			String fileName = "";

			 Part part = request.getPart("uploadFile");
			 fileName = EcHelper.getFileName(part);
			 //画像ファイルがあるかないか
			 if(!fileName.equals("")) {
			     part.write(EcHelper.UPLOAD_PAGE+ "/" + fileName);
			 }else {
				 fileName = preFileName;
			 }

			//選択された商品のIDを型変換し利用
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String itemName = (String)request.getParameter("itemName");
			int itemPrice = EcHelper.parseInt(request.getParameter("itemPrice"), -1);
			String itemDetail = (String)request.getParameter("itemDetail");


			ItemDataBeans idb = new ItemDataBeans();

			idb.setId(itemId);
			idb.setName(itemName);
			idb.setPrice(itemPrice);
			idb.setFileName(fileName);
			idb.setDetail(itemDetail);


			//商品を更新
			String updateCheck = ItemMasterDAO.getInstance().updateItem(idb);

			if(updateCheck.equals("success")) {
				session.setAttribute("itemUpdateMessage", "商品を更新しました");
				response.sendRedirect("ItemMaster");
			}else {
				 //失敗した場合
				ItemDataBeans item = ItemDAO.getItemByItemID(itemId);
				int purchaseNum = ItemMasterDAO.getInstance().getCountByItemId(itemId);

				request.setAttribute("purchaseNum", purchaseNum);
				request.setAttribute("item",item);
			    request.setAttribute("itemUpdateError",updateCheck);
			    request.getRequestDispatcher(EcHelper.ITEM_MASTER_UPDATE_PAGE).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}



}
