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
import dao.PointDAO;
import dao.UserDAO;

/**
 * Servlet implementation class UserCreate
 */
@WebServlet("/UserCreate")
public class UserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(EcHelper.USER_CREATE_PAGE).forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		try {

		    //新規登録
	        String inputLoginId = (String)request.getParameter("loginId");
	        String inputPassword =(String)request.getParameter("pass");
	        String inputConfirmPassword =(String)request.getParameter("passConfirm");
	        String inputUserName =(String)request.getParameter("userName");
	        String inputUserBirthDate =(String)request.getParameter("birthDate");
	        String inputUserAddress = (String)request.getParameter("address");
	        //パスワード以外の入力内容を残す
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        // Date型変換
	        Date formatDate = sdf.parse(inputUserBirthDate);
	        //test
	        System.out.println(inputLoginId+inputPassword+inputUserName+inputConfirmPassword+inputUserBirthDate+inputUserAddress);
	        UserDataBeans udb = new UserDataBeans();
	        udb.setName(inputUserName);
			udb.setAddress(inputUserAddress);
			udb.setBirthDate(formatDate);
			udb.setLoginId(inputLoginId);
			udb.setPassword(inputPassword);


			//パスワードとログインのチェック
			boolean flag = true;
			//insertできるかチェック
			boolean check = true;

			// 入力されているパスワードが確認用と等しいか
			if (!inputPassword.equals(inputConfirmPassword)) {
			 flag = false;
			}
			// loginIdの重複をチェック
			if (UserDAO.isOverlapLoginId(udb.getLoginId(), 0)||udb.getLoginId().equals("__noId")) {
			  flag = false;
			}

			if(flag) {
			   check = UserDAO.getInstance().insertUser(udb);
			}else {
				check = false;
			}

			if(check) {
			    //新規ユーザーのポイントを0で登録する
			    int id = UserDAO.getInstance().getUserId(inputLoginId,inputPassword);
			    PointDAO.getInstance().insertPoint(id);
				request.setAttribute("udb", udb);
				response.sendRedirect("Login");
			  }else {
			    session.setAttribute("udb", udb);
				request.setAttribute("check1", "NG");
				request.getRequestDispatcher(EcHelper.USER_CREATE_PAGE).forward(request, response);

			  }
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}


}
