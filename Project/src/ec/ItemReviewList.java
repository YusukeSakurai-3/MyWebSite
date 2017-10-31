package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReviewDataBeans;
import dao.ReviewDAO;

/**
 * Servlet implementation class ItemReviewList
 */
@WebServlet("/ItemReviewList")
public class ItemReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int userId = (int)session.getAttribute("userId");
			ArrayList<ReviewDataBeans> rdb = new ArrayList<ReviewDataBeans>();
			rdb  = ReviewDAO.getInstance().getReviewListByUserId(userId);

			//リクエストパラメーターにセット
			request.setAttribute("rdb", rdb);

			request.getRequestDispatcher(EcHelper.ITEM_REVIEW_LIST_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
