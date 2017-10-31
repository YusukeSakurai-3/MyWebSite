package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.BuyDetailDAO;
import dao.ItemDAO;

/**
 * Servlet implementation class Ranking
 */
@WebServlet("/Ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {


			//ランキング情報を取得
			ArrayList<Integer>  itemIdList = BuyDetailDAO.getInstance().getCountGroupByItemId();

			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
			for (int i : itemIdList) {
				ItemDataBeans item = ItemDAO.getItemByItemID(i);
				itemList.add(item);
			}

			//リクエストスコープにセット
			request.setAttribute("itemList", itemList);

			request.getRequestDispatcher(EcHelper.RANKING_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
