package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//ログイン情報がある場合商品一覧ページに行く
		boolean isLogin = session.getAttribute("isLogin")!=null?(boolean) session.getAttribute("isLogin"):false;
		if(isLogin) {
			response.sendRedirect("Index");
			return;
		}


		//ログイン失敗時に使用するため
		String inputLoginId =session.getAttribute("loginId")!=null?(String) EcHelper.cutSessionAttribute(session,"loginId"):"";
		String loginErrorMessage = (String)EcHelper.cutSessionAttribute(session, "loginErrorMessage");
		//ログアウトした場合
		String logoutcheck = (String)EcHelper.cutSessionAttribute(session, "check");



		request.setAttribute("inputLoginId", inputLoginId);
		request.setAttribute("loginErrorMessage", loginErrorMessage);
		//ログアウト
		request.setAttribute("logoutCheck", logoutcheck);



		request.getRequestDispatcher(EcHelper.LOGIN_PAGE).forward(request, response);
	}



}
