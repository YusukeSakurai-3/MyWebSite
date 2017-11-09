package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			 String listActionMessage = "商品が選択されていません";
			//ほしい物リストから削除する
			if(deleteItemIdList!=null) {
			for (String deleteItemId : deleteItemIdList) {
				System.out.println(deleteItemId);
			    boolean flag = ItemGetListDAO.getInstance().deleteUserItemByItemId(userId,Integer.parseInt(deleteItemId));
			    listActionMessage = "ほしい物リストから商品を削除しました";
			}}


			session.setAttribute("listActionMessage",listActionMessage);
			response.sendRedirect("ItemGetList");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
