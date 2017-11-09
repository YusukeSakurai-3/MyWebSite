package ec;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		try {

			int userId = (int)session.getAttribute("userId");

			String updateUserName = (String)request.getParameter("updateUserName");
			String updateAddress = (String)request.getParameter("updateAddress");
			String updatePassword = (String)request.getParameter("updatePassword");
			String updateConfirmPassword = (String)request.getParameter("updateConfirmPassword");
			String updateBirthDate =(String)request.getParameter("updateBirthDate");
			boolean isOpen = ((String)request.getParameter("isOpen")).equals("open")?true:false;
			System.out.println(isOpen);
			System.out.println(updateBirthDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        // Date型変換
	        Date formatDate = sdf.parse(updateBirthDate);

	        /*
			if(updatePassword.length()==0||!updatePassword.equals(updateConfirmPassword)) {
				session.setAttribute("updateActionMessage","パスワードがパスワード(確認)と一致していません");
				response.sendRedirect("UserUpdate");
			}*/


			UserDataBeans udb = new UserDataBeans();


			udb.setId(userId);
			udb.setName(updateUserName);
			udb.setAddress(updateAddress);
			udb.setBirthDate(formatDate);
			udb.setPassword(updatePassword);
			udb.setIs_open(isOpen);


			//商品を更新
			String updateCheck = UserDAO.getInstance().updateUser(udb,updateBirthDate);
			System.out.println(updateCheck);

			if(updateCheck.equals("success")) {
				session.setAttribute("userUpdateMessage", "ユーザー情報を更新しました");
				response.sendRedirect("UserDetail");
			}else {

			    session.setAttribute("updateActionMessage",updateCheck);
			    System.out.println("unkounkounko");
			    response.sendRedirect("UserUpdate");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}


}
