package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReviewDataBeans;
import dao.ReviewDAO;

/**
 * Servlet implementation class ItemReviewAdd
 */
@WebServlet("/ItemReviewAdd")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class ItemReviewAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		try {
			/*
			//写真のアップロード
			 Part part = request.getPart("reviewPicture");
		     String name = EcHelper.getFileName(part);
		     part.write(getServletContext().getRealPath("/WEB-INF/uploadsample") + "/" + name);
		     //response.sendRedirect("jsp/upload.jsp");
		      *
		      */

			int userId = (int) session.getAttribute("userId");
			//選択された商品のIDを型変換し利用
			int itemId = Integer.parseInt(request.getParameter("itemReviewId"));
			int evaluation = 4;
			String reviewTitle = (String)request.getParameter("reviewTitle");
			String reviewText = (String)request.getParameter("reviewText");

			if(reviewTitle==null||reviewTitle.length()==0||reviewTitle.equals("")) {
		        reviewTitle = "notitle";
		    }

			ReviewDataBeans rdb = new ReviewDataBeans();
			rdb.setUserId(userId);
			rdb.setItemId(itemId);
			rdb.setTitle(reviewTitle);
			rdb.setReviewText(reviewText);
			rdb.setFileName("");
			rdb.setEvaluation(evaluation);


			//レビューを追加。
			ReviewDAO.getInstance().insertUserReview(rdb);


			//userIdのレビューリストを取得
			ArrayList<ReviewDataBeans> ReviewList = ReviewDAO.getInstance().getReviewListByUserId(userId);
			request.setAttribute("rdb", ReviewList);
			request.setAttribute("reviewActionMessage", "商品のレビューを投稿しました");
			request.getRequestDispatcher(EcHelper.ITEM_REVIEW_LIST_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
