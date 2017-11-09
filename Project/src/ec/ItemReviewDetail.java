package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReviewDataBeans;
import dao.ReviewDAO;
import dao.UserDAO;

/**
 * Servlet implementation class ItemReviewDetail
 */
@WebServlet("/ItemReviewDetail")
public class ItemReviewDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択されたレビューのIDを型変換し利用
			int id = Integer.parseInt(request.getParameter("review_id"));


			//対象のレビュー情報を取得
			ReviewDataBeans review = ReviewDAO.getInstance().getReviewByReviewId(id);
			String reviewUserName = UserDAO.getUserName(review.getUserId());
			//リクエストパラメーターにセット
			request.setAttribute("review", review);
			request.setAttribute("reviewUserName", reviewUserName);


			request.getRequestDispatcher(EcHelper.ITEM_REVIEW_DETAIL_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
