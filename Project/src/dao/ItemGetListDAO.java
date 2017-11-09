package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.ItemDataBeans;

public class ItemGetListDAO {


	// インスタンスオブジェクトを返却させてコードの簡略化
	public static ItemGetListDAO getInstance() {
		return new ItemGetListDAO();
	}

	/**
	 * ユーザーIdに対するほしい物リストを取得する
	 * @param userId
	 * @return itemDataBeans
	 * @throws SQLException
	 */
	public ArrayList<ItemDataBeans> getUserItemList(int userId,int pageNum,int pageMaxItemCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startItemNum = (pageNum - 1) * pageMaxItemCount;
			con = DBManager.getConnection();

				// 商品名検索
				st = con.prepareStatement("SELECT item_id FROM t_lists WHERE user_id = ? ORDER BY id ASC LIMIT ?,?");
				st.setInt(1, userId);
				st.setInt(2, startItemNum);
				st.setInt(3, pageMaxItemCount);


			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();

			ItemDataBeans item = new ItemDataBeans();
			while (rs.next()) {
				int itemId = rs.getInt("item_id");
				item = ItemDAO.getItemByItemID(itemId);
				itemList.add(item);
			}
			System.out.println("get UserItemList by userId  has been completed");
			return itemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ユーザーIDに対する商品総数を取得
	 *
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public static double getItemCount(int userId,int pageNum,int pageMaxItemCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startItemNum = (pageNum - 1) * pageMaxItemCount;
			con = DBManager.getConnection();

			st = con.prepareStatement("select count(*) as cnt from t_lists where user_id = ? ORDER BY id ASC LIMIT ?,?");
			st.setInt(1, userId);
			st.setInt(2, startItemNum);
			st.setInt(3, pageMaxItemCount);


			ResultSet rs = st.executeQuery();

			double count = 0.0;
			while (rs.next()) {
				count = Double.parseDouble(rs.getString("cnt"));
			}
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**
	 * ほしい物リスト更新
	 * @param userId
	 * @param item
	 * @throws SQLException
	 */
	public void updateUserItemList(int userId,int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

				// ほしい物リスト更新
				st = con.prepareStatement("insert t_lists (user_id, item_id) VALUES(?,?)");
				st.setInt(1, userId);
				st.setInt(2, itemId);


			int rs = st.executeUpdate();

			System.out.println("update UserItemList   has been completed");
			return ;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ほしい物リスト削除
	 * @param userId
	 * @param item
	 * @throws SQLException
	 */
	public boolean deleteUserItemByItemId(int userId,int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			// ほしい物リスト更新(削除)
			st = con.prepareStatement("delete from t_lists where user_id = ? and item_id = ?");
			st.setInt(1, userId);
			st.setInt(2, itemId);


			int rs = st.executeUpdate();

			System.out.println("update UserItemList   has been completed");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
			//throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
