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
import javax.servlet.http.Part;

import beans.ItemDataBeans;
import beans.ReviewDataBeans;
import dao.ItemDAO;
import dao.ReviewDAO;

@WebServlet("/ItemReviewUpdate")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class ItemReviewUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			//選択されたレビューのIDを型変換し利用
			int reviewId = Integer.parseInt(request.getParameter("update_review_id"));
			//選択された商品のIDを型変換し利用
			int itemId = Integer.parseInt(request.getParameter("update_item_id"));

			//対象のレビュー情報を取得
			ReviewDataBeans reviewItem = ReviewDAO.getReviewByReviewId(reviewId);
			//対象の商品情報を取得
			ItemDataBeans item = ItemDAO.getInstance().getItemByItemID(itemId);


			//リクエストパラメーターにセット
			request.setAttribute("reviewItem", reviewItem);
			request.setAttribute("item", item);

			request.getRequestDispatcher(EcHelper.ITEM_REVIEW_UPDATE_PAGE).forward(request, response);
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


			//元の画像ファイルを取得
			String preFileName = (String)request.getParameter("preFileName");
			//アップロードされた画像ファイルを取得
			String fileName = "";

			 Part part = request.getPart("reviewPicture");
			 fileName = EcHelper.getFileName(part);
			 //画像ファイルがあるかないか
			 if(!fileName.equals("")) {
			     part.write(EcHelper.UPLOAD_PAGE+ "/" + fileName);
			 }else {
				 fileName = preFileName;
			 }



			int userId = (int) session.getAttribute("userId");
			//選択された商品のIDを型変換し利用
			int reviewId = EcHelper.parseInt(request.getParameter("itemReviewId"),42);
			int evaluation = EcHelper.parseInt(request.getParameter("evaluation"),3);
			String reviewTitle = (String)request.getParameter("reviewTitle");
			String reviewText = (String)request.getParameter("reviewText");

			//タイトルがなければnotitle
			if(reviewTitle==null||reviewTitle.length()==0||reviewTitle.equals("")) {
		        reviewTitle = "notitle";
		    }

			ReviewDataBeans rdb = new ReviewDataBeans();
			//rdb.setUserId(userId);
			rdb.setId(reviewId);
			rdb.setTitle(reviewTitle);
			rdb.setReviewText(reviewText);
			rdb.setFileName(fileName);
			rdb.setEvaluation(evaluation);


			//レビューを更新
			ReviewDAO.getInstance().updateUserReview(rdb);

			//userIdのレビューリストを取得
			ArrayList<ReviewDataBeans> ReviewList = ReviewDAO.getReviewListByUserId(userId);
			request.setAttribute("rdb", ReviewList);
			request.setAttribute("reviewActionMessage", "商品のレビューを更新しました");
			request.getRequestDispatcher(EcHelper.ITEM_REVIEW_LIST_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
