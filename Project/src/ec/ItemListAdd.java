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
 * Servlet implementation class ItemListAdd
 */
@WebServlet("/ItemListAdd")
public class ItemListAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			Boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin") : false;
			if (!isLogin) {
				// Login画面にリダイレクト
				response.sendRedirect("Login");
			}else {
				int userId = (int) session.getAttribute("userId");
				String userName = (String)session.getAttribute("userName");
				//選択された商品のIDを型変換し利用
				int itemId = Integer.parseInt(request.getParameter("itemget_id"));

				//ほしい物リストに商品を追加。
				ItemGetListDAO.getInstance().updateUserItemList(userId,itemId);

				session.setAttribute("listActionMessage", "商品をほしい物リストに追加しました");
				response.sendRedirect("ItemGetList");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}


}
