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
import dao.UserMasterDAO;

/**
 * Servlet implementation class UserUpdateMaster
 */
@WebServlet("/UserUpdateMaster")
public class UserUpdateMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {


			int errorId = session.getAttribute("errorUserId")!=null?(int)EcHelper.cutSessionAttribute(session,"errorUserId"):-1;

			//選択されたユーザーのIDを型変換し利用
			int  id = EcHelper.parseInt(request.getParameter("user_id"),errorId);
			//戻るページ表示用
			int pageNum = Integer.parseInt(request.getParameter("page_num")==null?"1":request.getParameter("page_num"));
			//対象のユーザー情報を取得
			UserDataBeans user = UserDAO.getUserDataBeansByUserId(id);
			int point = PointDAO.getInstance().getPointById(id);


			request.setAttribute("point", point);
			request.setAttribute("pageNum",pageNum);
			request.setAttribute("user",user);
			request.getRequestDispatcher(EcHelper.USER_UPDATE_MASTER_PAGE).forward(request, response);
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


			//選択された商品のIDを型変換し利用
			int userId = Integer.parseInt(request.getParameter("user_id"));
			String userName = (String)request.getParameter("userName");
			int point = EcHelper.parseInt(request.getParameter("point"), -1);
			String address = (String)request.getParameter("address");
			String birthDate = (String)request.getParameter("birthDate");
			boolean isOpen = ((String)request.getParameter("isOpen")).equals("open")?true:false;


			UserDataBeans udb = new UserDataBeans();

			udb.setId(userId);
			udb.setName(userName);
			udb.setAddress(address);
			udb.setIs_open(isOpen);


			//ユーザー情報を更新
			String updateCheck = UserMasterDAO.getInstance().updateUser(udb,point,birthDate);

			if(updateCheck.equals("success")) {
				session.setAttribute("UpdateMessage", "商品を更新しました");
				response.sendRedirect("UserList");
			}else {
				 //失敗した場合
				//セッション ユーザーID メッセージ
				session.setAttribute("errorUserId",userId);
                request.setAttribute("UpdateErrorMessage",updateCheck);
			    response.sendRedirect("UserUpdateMaster");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}



}
