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
import dao.ItemDAO;
import dao.ItemMasterDAO;


@WebServlet("/ItemMaster")
public class ItemMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			String searchWordMaster = request.getParameter("search_word")==null?"":request.getParameter("search_word");
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchWordMaster", searchWordMaster);

			// 商品リストを取得 ページ表示分のみ
			ArrayList<ItemDataBeans> searchResultItemList = ItemDAO.getInstance().getItemsByItemName(searchWordMaster,pageNum,PAGE_MAX_ITEM_COUNT);

			//購入数を取得する
			HashMap<Integer, Integer> purchaseNum = new HashMap<Integer,Integer>();
			for(ItemDataBeans item : searchResultItemList) {
				purchaseNum.put(item.getId(),ItemMasterDAO.getInstance().getCountByItemId(item.getId()));
			}



			// リクエストパラメーターにセット
			request.setAttribute("purchaseNum", purchaseNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("itemList", searchResultItemList);

			request.getRequestDispatcher(EcHelper.ITEM_MASTER_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
