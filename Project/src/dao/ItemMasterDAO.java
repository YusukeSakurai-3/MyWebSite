package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;

public class ItemMasterDAO {

	//インスタンスオブジェクトを返却させてコードの簡略化
		public static ItemMasterDAO getInstance() {
			return new ItemMasterDAO();
		}

	/**
	 *
	 * 商品の購入数を得る
	 * @param itemId
	 * @return int
	 * @throws SQLException
	 */
	public int getCountByItemId(int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT count(*) as sum FROM t_buy_detail WHERE item_id = ?");

			st.setInt(1, itemId);

			ResultSet rs = st.executeQuery();


			int count = 0;

			if (rs.next()) {
				count = rs.getInt("sum");
			}

			System.out.println("searching count has been completed");
			return count;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
