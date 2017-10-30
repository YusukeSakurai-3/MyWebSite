package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemGetListDAO;

/**
 * Servlet implementation class ItemGetList
 */
@WebServlet("/ItemGetList")
public class ItemGetList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();

	try {
		int userId = (int) session.getAttribute("userId");

		//userIdのほしい物リストを取得
		ArrayList<ItemDataBeans>userItemList = ItemGetListDAO.getInstance().getUserItemList(userId);


		request.setAttribute("userItemList", userItemList);


		request.setAttribute("UserListActionMessage", "商品をほしい物リストに追加しました");
		request.getRequestDispatcher(EcHelper.ITEM_GET_LIST_PAGE).forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("errorMessage", e.toString());
		response.sendRedirect("Error");
	}
}

}
