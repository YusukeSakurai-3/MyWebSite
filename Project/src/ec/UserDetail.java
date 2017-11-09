package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.UserDataBeans;
import dao.BuyDAO;
import dao.PointDAO;
import dao.UserDAO;

/**
 * Servlet implementation class UserDetail
 */
@WebServlet("/UserDetail")
public class UserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		int userId = (int)session.getAttribute("userId");
		//test
		System.out.println(userId);
		try {
			//ユーザー情報更新した時に取得
			String updateMessage = (String)EcHelper.cutSessionAttribute(session,"userUpdateMessage");


			UserDataBeans udb = UserDAO.getUserDataBeansByUserId(userId);
			int point = PointDAO.getInstance().getPointById(userId);
			ArrayList<BuyDataBeans> bdb = BuyDAO.getInstance().getBuyDataBeansByBuyUserId(userId) ;
			request.setAttribute("point", point);
			request.setAttribute("updateuser", udb);
			request.setAttribute("bdb",bdb);
			request.setAttribute("updateMessage", updateMessage);
			request.getRequestDispatcher(EcHelper.USER_DETAIL_PAGE).forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
