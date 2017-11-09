package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import base.DBManager;
import beans.UserDataBeans;

public class UserMasterDAO {
	// インスタンスオブジェクトを返却させてコードの簡略化
		public static UserMasterDAO getInstance() {
			return new UserMasterDAO();
		}

	/**
	 * ユーザー情報削除
	 * @param
	 * @throws SQLException
	 */
	public boolean deleteUser(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			// ユーザー情報更新
			st = con.prepareStatement("DELETE FROM t_user WHERE id = ? ");
			st.setInt(1, userId);

			System.out.println(st);
			int rs = st.executeUpdate();

			System.out.println("delete User has been completed");
			return true;
		} catch (SQLException e) {
			return false;

		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ユーザー情報更新
	 * @param
	 * @throws SQLException
	 */
	public String updateUser(UserDataBeans udb, int point,String birthDate) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		String error = "入力に誤りがあります";
		try {
			con = DBManager.getConnection();

			if(udb.getName().length()==0) {
				return "名前が入力されていません";
			}

			if(udb.getAddress().length()== 0) {
				return "住所が入力されていません";
			}

			if(point < 0) {
				return "ポイントの値が不正です";
			}

			// ユーザー情報更新
			st = con.prepareStatement("UPDATE t_user  SET name = ?, address = ?, birth_date = ?, is_open = ? , update_date = ? WHERE id = ? ");
			st.setString(1, udb.getName());
			st.setString(2, udb.getAddress());
			st.setString(3,birthDate);
			st.setBoolean(4,udb.getIs_open());
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.setInt(6, udb.getId());

			System.out.println(st);
			st.executeUpdate();
			System.out.println("Update User has been completed");


			st = con.prepareStatement("UPDATE t_point SET point = ? WHERE user_id = ?");

			st.setInt(1,point);
			st.setInt(2,udb.getId());
			st.executeUpdate();

			System.out.println("update userpoint has been completed");

			return "success";
		} catch (SQLException e) {
			return error;

		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


}
