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
import dao.UserDAO;

/**
 * Servlet implementation class ItemGetList
 */
@WebServlet("/ItemGetList")
public class ItemGetList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 8;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();

	try {
		boolean isLogin = session.getAttribute("isLogin")!=null?(boolean) session.getAttribute("isLogin"):false;
		if(!isLogin) {
			//ログインしていなければログインページに移動

			response.sendRedirect("Login");
		}else {
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//ほしい物リストに商品を追加した時使用
			String listMessage = (String)EcHelper.cutSessionAttribute(session,"listActionMessage");

			//商品を追加削除した時はログインユーザーのリストを得る
			int userId = (int) session.getAttribute("userId");
			int listUserId = EcHelper.parseInt(request.getParameter("listUserId"),userId);
			//idから名前を取得
			String listUser = UserDAO.getUserName(listUserId);

			System.out.println(listUser);
			//userIdのほしい物リストを取得
			ArrayList<ItemDataBeans>userItemList = ItemGetListDAO.getInstance().getUserItemList(listUserId,pageNum,PAGE_MAX_ITEM_COUNT);

			//ページ数を取得する
			double itemCount = ItemGetListDAO.getItemCount(listUserId,pageNum,PAGE_MAX_ITEM_COUNT);
			int pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);
			System.out.println(pageMax);

			//リクエストパラメーターにセット
			request.setAttribute("userItemList", userItemList);
			request.setAttribute("listUser", listUser);
			request.setAttribute("listUserId", listUserId);
			request.setAttribute("pageMax", pageMax);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("listMessage", listMessage);

			request.getRequestDispatcher(EcHelper.ITEM_GET_LIST_PAGE).forward(request, response);
		}
	} catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("errorMessage", e.toString());
		response.sendRedirect("Error");
	}
}

}
