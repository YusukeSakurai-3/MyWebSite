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
 * Servlet implementation class ItemMasterDetail
 */
@WebServlet("/ItemMasterDetail")
public class ItemMasterDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択された商品のIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("item_id"));

			//戻るページ表示用
			int pageNum = Integer.parseInt(request.getParameter("page_num")==null?"1":request.getParameter("page_num"));
			//対象のアイテム情報を取得
			ItemDataBeans item = ItemDAO.getInstance().getItemByItemID(id);

			//購入数を取得するPurchaseNumber
			int purchaseNum= ItemMasterDAO.getInstance().getCountByItemId(id);

			//リクエストパラメーターにセット
			request.setAttribute("purchaseNum",purchaseNum);
			request.setAttribute("item", item);
			request.setAttribute("pageNum", pageNum);

			request.getRequestDispatcher(EcHelper.ITEM_MASTER_DETAIL_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
