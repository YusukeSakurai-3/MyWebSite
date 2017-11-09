package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.PointDAO;
import dao.UserDAO;

/**
 * Servlet implementation class UserDetailMaster
 */
@WebServlet("/UserDetailMaster")
public class UserDetailMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択されたユーザーのIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("user_id"));

			//戻るページ表示用
			int pageNum = Integer.parseInt(request.getParameter("page_num")==null?"1":request.getParameter("page_num"));
			//対象のユーザー情報を取得
			UserDataBeans user = UserDAO.getUserDataBeansByUserId(id);

			//ポイントを取得する
			int userPoint = PointDAO.getPointById(id);

			//リクエストパラメーターにセット
			request.setAttribute("userPoint",userPoint);
			request.setAttribute("user", user);
			request.setAttribute("pageNum", pageNum);

			request.getRequestDispatcher(EcHelper.USER_DETAIL_MASTER_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}


}
