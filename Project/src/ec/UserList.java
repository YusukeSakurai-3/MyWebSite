package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SearchDataBeans;
import beans.UserDataBeans;
import dao.UserDAO;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_MAX_USER_COUNT = 3;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//検索情報
			String searchName = request.getParameter("searchName")==null?"":request.getParameter("searchName");
			//ログインID
			String loginId = request.getParameter("loginId")!=null? (String)request.getParameter("loginId"):"__noId";
			loginId = loginId.equals("")? "__noId":loginId;
			//日付
		    String startDate = request.getParameter("startDate")!=null?(String)request.getParameter("startDate"):"nodate";
		    startDate = startDate.equals("")?"nodate":startDate;
		    String endDate = request.getParameter("endDate")!=null?(String)request.getParameter("endDate"):"nodate";
		    endDate = endDate.equals("")?"nodate":endDate;

			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchName", searchName);

			// ユーザーリストを取得 ページ表示分のみ
			SearchDataBeans sdb = new SearchDataBeans();
			sdb.setName(searchName);
			sdb.setStartDate(startDate);
			sdb.setEndDate(endDate);
			sdb.setLoginId(loginId);
			ArrayList<UserDataBeans> searchResultUserList = UserDAO.getInstance().getUsersDetail(sdb,pageNum,PAGE_MAX_USER_COUNT);


			//ユーザー数を取得
			double userCount = UserDAO.getUserCount(sdb);

			int pageMax = (int) Math.ceil(userCount / PAGE_MAX_USER_COUNT);
			System.out.println("pageMax:"+pageMax);


			//リクエストパラメーターにセット
			//loginId
			request.setAttribute("loginId", loginId);
			//date
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("pageMax", pageMax);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("userList", searchResultUserList);




			request.getRequestDispatcher(EcHelper.USER_LIST_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
