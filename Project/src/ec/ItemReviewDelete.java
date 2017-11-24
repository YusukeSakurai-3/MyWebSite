package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import dao.UserDAO;

/**
 * Servlet implementation class ItemReviewDelete
 */
@WebServlet("/ItemReviewDelete")
public class ItemReviewDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int userId = (int) session.getAttribute("userId");
			//選択されたレビューのIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("delete_item_id"));


			//対象のレビュー情報を削除する
			ReviewDAO.getInstance().deleteUserReview(id);
			String reviewUserName = UserDAO.getUserName(userId);

//
//			ArrayList<ReviewDataBeans> rdb  = ReviewDAO.getInstance().getReviewListByUserId(userId);
//			//リクエストパラメーターにセット
//			request.setAttribute("rdb", rdb);
//			request.setAttribute("reviewUserName", reviewUserName);
			response.sendRedirect("ItemReviewList");


			//request.getRequestDispatcher(EcHelper.ITEM_REVIEW_LIST_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
