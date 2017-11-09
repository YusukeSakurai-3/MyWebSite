package ec;

import java.io.IOException;

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
			String updateMessage = (String)EcHelper.cutSessionAttribute(session,"updateActionMessage");
			UserDataBeans udb = UserDAO.getUserDataBeansByUserId(userId);

			request.setAttribute("updateMessage", updateMessage);
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
			String prePassword = (String)request.getParameter("prePassword");
			String updatePassword = (String)request.getParameter("updatePassword");
			String updateConfirmPassword = (String)request.getParameter("updateConfirmPassword");
			System.out.println(updateConfirmPassword);
			String updateBirthDate =(String)request.getParameter("updateBirthDate");
			boolean isOpen = ((String)request.getParameter("isOpen")).equals("open")?true:false;
			//System.out.println(isOpen);
			//System.out.println(updateBirthDate);
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


			UserDataBeans udb = new UserDataBeans();


			udb.setId(userId);
			udb.setName(updateUserName);
			udb.setAddress(updateAddress);
			//パスワードが入力されていない時は元のパスワードをセットする
			if(updatePassword.length()!=0&&updateConfirmPassword.length()!=0) {
			    udb.setPassword(EcHelper.toCode(updatePassword));
			    updateConfirmPassword = EcHelper.toCode(updateConfirmPassword);
			    System.out.println(EcHelper.toCode(updatePassword));
			}else {
				updateConfirmPassword = prePassword;
				udb.setPassword(prePassword);
			}
			udb.setIs_open(isOpen);


			//商品を更新
			String updateCheck = UserDAO.getInstance().updateUser(udb,updateBirthDate,updateConfirmPassword);
			System.out.println(updateCheck);

			if(updateCheck.equals("success")) {
				session.setAttribute("userUpdateMessage", "ユーザー情報を更新しました");
				response.sendRedirect("UserDetail");
			}else {

			    session.setAttribute("updateActionMessage",updateCheck);
			    response.sendRedirect("UserUpdate");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}


}
