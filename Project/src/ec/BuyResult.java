package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;
import beans.PointDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;
import dao.PointDAO;

/**
 * Servlet implementation class BuyResult
 */
@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {

			// セッションからカート情報を取得
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) EcHelper.cutSessionAttribute(session, "cart");

			BuyDataBeans bdb = (BuyDataBeans) EcHelper.cutSessionAttribute(session, "bdb");
			PointDataBeans pdb = (PointDataBeans) EcHelper.cutSessionAttribute(session, "pdb");


			// 購入情報を登録
			int buyId = BuyDAO.insertBuy(bdb);
			// 購入詳細情報を購入情報IDに紐づけして登録
			for (ItemDataBeans cartInItem : cart) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setItemId(cartInItem.getId());
				BuyDetailDAO.insertBuyDetail(bddb);
			}

			//ポイント情報更新
			PointDAO.getInstance().updateUserPoint(pdb);

			/* ====購入完了ページ表示用==== */
			BuyDataBeans resultBDB = BuyDAO.getBuyDataBeansByBuyId(buyId);
			request.setAttribute("resultBDB", resultBDB);

			// 購入アイテム情報
			ArrayList<ItemDataBeans> buyIDBList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			request.setAttribute("buyIDBList", buyIDBList);

			// 購入完了ページ
			request.getRequestDispatcher(EcHelper.BUY_RESULT_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
