package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.DBManager;
import beans.PointDataBeans;

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
	public static int getPointById(int userId) throws SQLException {
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

	/**
	 *購入時にポイントを更新する
	 *
	 * @param id
	 *
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためにスロー
	 */
	public void updateUserPoint(PointDataBeans pdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("UPDATE t_point SET point = point + ? WHERE user_id = ?");

			st.setInt(1,pdb.getPoint());
			st.setInt(2,pdb.getUserId());
			System.out.println(pdb.getPoint()+":"+pdb.getUserId());
			st.executeUpdate();
			System.out.println("update userpoint has been completed");
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
	 * ポイント情報を削除する
	 *
	 * @param id
	 *
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためにスロー
	 */
	public void deletePoint(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("DELETE FROM t_point WHERE user_id = ? ");
			st.setInt(1,userId);

			st.executeUpdate();
			System.out.println("delete point has been completed");
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
