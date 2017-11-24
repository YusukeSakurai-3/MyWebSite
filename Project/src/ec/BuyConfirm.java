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
import beans.DeliveryMethodDataBeans;
import beans.ItemDataBeans;
import beans.PointDataBeans;
import dao.DeliveryMethodDAO;
import dao.PointDAO;

/**
 * Servlet implementation class BuyConfirm
 */
@WebServlet("/BuyConfirm")
public class BuyConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//選択された配送方法IDを取得
			int inputDeliveryMethodId = Integer.parseInt(request.getParameter("delivery_method_id"));
			int userId = (int) session.getAttribute("userId");
			int userPoint = PointDAO.getInstance().getPointById(userId);

			int point = EcHelper.parseInt(request.getParameter("point"),0);
			//選択されたIDをもとに配送方法Beansを取得
			DeliveryMethodDataBeans userSelectDMB = DeliveryMethodDAO.getDeliveryMethodDataBeansByID(inputDeliveryMethodId);
			//買い物かご
			ArrayList<ItemDataBeans> cartIDBList = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
			//合計金額
			int totalPrice = EcHelper.getTotalItemPrice(cartIDBList)+userSelectDMB.getPrice();
			if(point > userPoint||point < 0) {
				request.setAttribute("buyActionMessage", "ポイントが不正な値です");
				// 配送方法をDBから取得
				ArrayList<DeliveryMethodDataBeans> dMDBList = DeliveryMethodDAO.getAllDeliveryMethodDataBeans();
				request.setAttribute("dmdbList", dMDBList);
				request.setAttribute("userPoint", userPoint);
				request.getRequestDispatcher(EcHelper.BUY_PAGE).forward(request, response);
			}else if(point> totalPrice){
				request.setAttribute("buyActionMessage", "ポイントが合計金額を超えています");
				// 配送方法をDBから取得
				ArrayList<DeliveryMethodDataBeans> dMDBList = DeliveryMethodDAO.getAllDeliveryMethodDataBeans();
				request.setAttribute("dmdbList", dMDBList);
				request.setAttribute("userPoint", userPoint);
				request.getRequestDispatcher(EcHelper.BUY_PAGE).forward(request, response);
			}else {



				BuyDataBeans bdb = new BuyDataBeans();
				bdb.setUserId(userId);
				//pointを引いた状態の合計金額
				bdb.setTotalPrice(totalPrice-point);
				bdb.setDelivertMethodId(userSelectDMB.getId());
				bdb.setDeliveryMethodName(userSelectDMB.getName());
				bdb.setDeliveryMethodPrice(userSelectDMB.getPrice());
				System.out.println(userPoint);


				//point = Math.min(totalPrice,point);
				PointDataBeans pdb = new PointDataBeans();
				if(point==0) {
					pdb.setUserId(userId);
					pdb.setPoint(totalPrice/100);
				}else {
					pdb.setUserId(userId);
					pdb.setPoint(-point);
				}
				//購入確定で利用
				System.out.println(point);

				session.setAttribute("point", point);
				session.setAttribute("pdb", pdb);
				session.setAttribute("bdb", bdb);
				request.getRequestDispatcher(EcHelper.BUY_CONFIRM_PAGE).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}



}
