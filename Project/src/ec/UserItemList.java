package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserItemList
 */
@WebServlet("/UserItemList")
public class UserItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_USER_COUNT = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//ほしい物リストを公開しているユーザーを取得
			ArrayList<UserDataBeans> udb = UserDAO.getUsersByisOpen(pageNum,PAGE_USER_COUNT);

			// 検索ワードに対しての総ページ数を取得
			double userCount = UserDAO.getOpenUserCount("");

			int pageMax = (int) Math.ceil(userCount / PAGE_USER_COUNT);
			System.out.println("pageMax:"+pageMax);


			//リクエストパラメーターにセット
			request.setAttribute("udb", udb);
			request.setAttribute("pageMax", pageMax);
			request.setAttribute("pageNum", pageNum);

			request.getRequestDispatcher(EcHelper.USER_ITEM_LIST_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}



