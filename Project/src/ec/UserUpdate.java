package ec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		int userId = (int)session.getAttribute("userId");
		//test
		System.out.println(userId);
		try {
			UserDataBeans udb = UserDAO.getUserDataBeansByUserId(userId);
			request.setAttribute("updateuser", udb);
			request.getRequestDispatcher(EcHelper.USER_UPDATE_PAGE).forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


}
