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
import dao.ItemMasterDAO;


@WebServlet("/ItemMaster")
public class ItemMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 20;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			//商品登録成功した時使用
			String createMessage = (String)EcHelper.cutSessionAttribute(session,"itemCreateMessage");
			//商品更新成功した時使用
			String updateMessage = (String)EcHelper.cutSessionAttribute(session,"itemUpdateMessage");
			//商品削除成功した時使用
			String deleteMessage = (String)EcHelper.cutSessionAttribute(session,"itemDeleteMessage");

			//検索情報
			String searchWordMaster = request.getParameter("search_word")==null?"":request.getParameter("search_word");
			//ID
			int itemId = request.getParameter("itemId")!=null? EcHelper.parseInt(request.getParameter("itemId"),-1):-1;
			//価格
			int startPrice = request.getParameter("startPrice")!=null? EcHelper.parseInt(request.getParameter("startPrice"),-1):-1;
		    int endPrice = request.getParameter("endPrice")!=null? EcHelper.parseInt(request.getParameter("endPrice"),-1):-1;
		    //日付
		    String startDate = request.getParameter("startDate")!=null?(String)request.getParameter("startDate"):"nodate";
		    String endDate = request.getParameter("endDate")!=null?(String)request.getParameter("endDate"):"nodate";
		    //System.out.println(startDate);

			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchWordMaster", searchWordMaster);


			// 商品リストを取得 ページ表示分のみ
			//ArrayList<ItemDataBeans> searchResultItemList = ItemDAO.getInstance().getItemsByItemName(searchWordMaster,pageNum,PAGE_MAX_ITEM_COUNT);
			ArrayList<ItemDataBeans> searchResultItemList =
					ItemMasterDAO.getInstance().getItemsMasterDetail(searchWordMaster,itemId,startDate,endDate,startPrice,endPrice,pageNum,PAGE_MAX_ITEM_COUNT);


			// 検索ワードに対しての総ページ数を取得
			double itemCount = ItemMasterDAO.getItemCountMaster(searchWordMaster,itemId,startDate,endDate,startPrice,endPrice);

			int pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);
			System.out.println("pageMax:"+pageMax);


			//購入数を取得する
			HashMap<Integer, Integer> purchaseNum = new HashMap<Integer,Integer>();
			for(ItemDataBeans item : searchResultItemList) {
				purchaseNum.put(item.getId(),ItemMasterDAO.getInstance().getCountByItemId(item.getId()));
			}






			// リクエストパラメーターにセット
			//id
			request.setAttribute("itemId", itemId);
			//price
			request.setAttribute("startPrice", startPrice);
			request.setAttribute("endPrice", endPrice);
			//date
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);

			request.setAttribute("createMessage", createMessage);
			request.setAttribute("updateMessage", updateMessage);
			request.setAttribute("deleteMessage", deleteMessage);
			request.setAttribute("purchaseNum", purchaseNum);
			request.setAttribute("pageMax", pageMax);
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
