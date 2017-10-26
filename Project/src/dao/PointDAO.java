package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;

public class PointDAO {
	// インスタンスオブジェクトを返却させてコードの簡略化
	public static PointDAO getInstance() {
		return new PointDAO();
	}

	/**
	 * ユーザーを登録した時同時にポイントを0で登録する
	 *
	 * @param id
	 *            対応したデータを保持しているJavaBeans
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためにスロー
	 */
	public void insertPoint(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO t_point(user_id,point) VALUES(?,?)");
			st.setInt(1,userId);
			st.setInt(2, 0);
			st.executeUpdate();
			System.out.println("inserting point has been completed");
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
	 *-ユーザーのポイントを手に入れる
	 *
	 * @param id
	 *            対応したデータを保持しているJavaBeans
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためにスロー
	 */
	public int getPointById(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int point = 0;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT * FROM t_point WHERE user_id = ?");
			st.setInt(1,userId);
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				point = rs.getInt("point");
			}

			System.out.println("searching point has been completed");
			return point;
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
