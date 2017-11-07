package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemDAO;
import dao.ItemMasterDAO;

/**
 * Servlet implementation class ItemMasterDelete
 */
@WebServlet("/ItemMasterDelete")
public class ItemMasterDelete extends HttpServlet {
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
			request.getRequestDispatcher(EcHelper.ITEM_MASTER_DELETE_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			//選択された商品のIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("deleteItemId"));

			//対象の商品情報を削除する
			ItemMasterDAO.getInstance().deleteItem(id);

			session.setAttribute("itemDeleteMessage", "商品を削除しました");
			response.sendRedirect("ItemMaster");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
