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
 * Servlet implementation class UserItemDelete
 */
@WebServlet("/UserItemDelete")
public class UserItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			int userId = (int) session.getAttribute("userId");
			String[] deleteItemIdList = request.getParameterValues("delete_item_id_list");
			//ほしい物リストから削除する
			for (String deleteItemId : deleteItemIdList) {
				System.out.println(deleteItemId);
			    boolean flag = ItemGetListDAO.getInstance().deleteUserItemByItemId(userId,Integer.parseInt(deleteItemId));
			}


			String listActionMessage = "削除しました";
			ArrayList<ItemDataBeans> userItemList = ItemGetListDAO.getInstance().getUserItemList(userId);
			request.setAttribute("listActionMessage",listActionMessage);
			request.setAttribute("userItemList", userItemList);
			request.getRequestDispatcher(EcHelper.ITEM_GET_LIST_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
