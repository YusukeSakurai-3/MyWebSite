package ec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import beans.ReviewDataBeans;
import dao.ItemDAO;
import dao.ReviewDAO;
import dao.UserDAO;

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
			String reviewActionMessage = (String)EcHelper.cutSessionAttribute(session,"reviewActionMessage");
			ArrayList<ReviewDataBeans> rdb = new ArrayList<ReviewDataBeans>();
			rdb  = ReviewDAO.getInstance().getReviewListByUserId(userId);
			String reviewUserName = UserDAO.getUserName(userId);
			HashMap<Integer,ItemDataBeans> item = new HashMap<Integer,ItemDataBeans>();
			for(ReviewDataBeans review: rdb) {
				item.put(review.getId(),ItemDAO.getItemByItemID(review.getItemId()));
			}

			//リクエストパラメーターにセット
			request.setAttribute("rdb", rdb);
			request.setAttribute("reviewActionMessage",reviewActionMessage);
			request.setAttribute("reviewUserName", reviewUserName);
			request.setAttribute("item",item);

			request.getRequestDispatcher(EcHelper.ITEM_REVIEW_LIST_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
