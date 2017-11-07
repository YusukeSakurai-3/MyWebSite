package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import base.DBManager;
import beans.ItemDataBeans;

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

			System.out.println("searching purchaseNumber has been completed");
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

	/**
	 * 商品を新規登録する。登録時刻は現在時刻で、挿入直前に生成
	 *
	 * @param item
	 *            対応したデータを保持しているJavaBeans
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためにスロー
	 */
	public String insertItem(ItemDataBeans idb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		String error = "入力内容が正しくありません";
		try {


			if(idb.getName().length()==0) {
				return "名前が入力されていません";
			}

			/*
			if(idb.getDetail().length()==0) {
				return error;
			}*/

			if(idb.getPrice() < 0) {
				return "価格は数字を入力してください";
			}
			con = DBManager.getConnection();
			//名前チェック
			st = con.prepareStatement("SELECT * FROM m_item WHERE name = ?");
			st.setString(1, idb.getName());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return "名前が他の商品と一致しています";
			}

			st = null;
			st = con.prepareStatement("INSERT INTO m_item(name,detail,price,file_name,create_date,update_date) VALUES(?,?,?,?,?,?)");
			st.setString(1, idb.getName());
			st.setString(2, idb.getDetail());
			st.setInt(3, idb.getPrice());
			st.setString(4,idb.getFileName());
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();
			System.out.println("inserting item has been completed");
			return "success";
		} catch (SQLException e) {
			return error;

		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 商品更新
	 * @param ItemDataBeans
	 * @throws SQLException
	 */
	public String updateItem(ItemDataBeans idb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		String error = "入力内容が正しくありません";
		try {

			if(idb.getName().length()==0) {
				return "名前が入力されていません";
			}

			if(idb.getPrice() < 0) {
				return "価格は数字を入力してください";
			}

			con = DBManager.getConnection();
			//名前チェック
			st = con.prepareStatement("SELECT * FROM m_item WHERE name = ? and  id != ?");
			st.setString(1, idb.getName());
			st.setInt(2, idb.getId());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return "名前が他の商品と一致しています";
			}
			st = null;

			// 商品更新
			st = con.prepareStatement("UPDATE m_item  SET name = ?, detail = ?, price = ?, file_name = ? , update_date = ? WHERE id = ? ");
			st.setString(1, idb.getName());
			st.setString(2, idb.getDetail());
			st.setInt(3,idb.getPrice());
			st.setString(4,idb.getFileName());
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.setInt(6, idb.getId());


			int rss = st.executeUpdate();

			System.out.println("update Item has been completed");
			return "success";
		} catch (SQLException e) {
			return error;
			//System.out.println(e.getMessage());
			//throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 商品削除
	 * @param itemId
	 * @throws SQLException
	 */
	public boolean deleteItem(int id) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			//商品削除
			st = con.prepareStatement("delete from m_item where id = ? ");
			st.setInt(1, id);

			int rs = st.executeUpdate();

			System.out.println("delete item has been completed");
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

	/**
	 * 詳細商品検索(マスタ)
	 * @param searchWord
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @param priceStart
	 * @param priceEnd
	 * @param select
	 * @param pageNum
	 * @param pageMaxItemCount
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ItemDataBeans> getItemsMasterDetail(String searchWord,int itemId,String startDate,String endDate,int startPrice,int endPrice,int pageNum, int pageMaxItemCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startiItemNum = (pageNum - 1) * pageMaxItemCount;
			con = DBManager.getConnection();
			ResultSet rs;

			if (searchWord.length() == 0&&itemId == -1&&!startDate.equals("nodate")&&!endDate.equals("nodate")&&startPrice==-1&&endPrice==-1) {
				// 全検索
				st = con.prepareStatement("SELECT * FROM m_item ORDER BY id ASC LIMIT ?,? ");
				st.setInt(1, startiItemNum);
				st.setInt(2, pageMaxItemCount);
				rs = st.executeQuery();
			} else {
				boolean flag = true;
				String and = " and ";
				// 商品名検索
				String sql = "SELECT * FROM m_item WHERE name like ";

				String[] searchWords = searchWord.split("　", 0);
				for(String word:searchWords) {
				  word=" '%"+word+"%' ";
				  if(flag) {
					  sql += word;
					  flag = false;
				  }else {
					  sql = sql +" "+and+" name like " + word;
					  }
				}

				if(itemId!=-1) {
					sql = sql +and+" id ="+itemId+" ";
				}

				if(!startDate.equals("nodate")) {
					startDate = "'"+startDate+"'";
					sql = sql +and+"create_date >= "+startDate;
				}
				if(!endDate.equals("nodate")) {
					endDate = "'"+endDate+"'";
					sql = sql +and+"create_date <= "+endDate;
				}

				if(startPrice!=-1) {
				    sql = sql +and+"price >="+startPrice+" ";
				    		}
				if(endPrice!=-1) {
				    sql = sql+and+"price <="+endPrice+" ";
				}

				sql +="LIMIT "+startiItemNum+" , "+pageMaxItemCount;
				System.out.println(sql);

				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
			}


			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans item = new ItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
				item.setCreateDate(rs.getDate("create_date"));
				item.setUpdateDate(rs.getDate("update_date"));
				itemList.add(item);
			}
			System.out.println("get Items by itemName has been completed");
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
	 * 商品総数を取得(マスタ)
	 *
	 * @param searchWord
	 * @return
	 * @throws SQLException
	 */
	public static double getItemCountMaster(String searchWord,int itemId,String startDate,String endDate,int startPrice,int endPrice) throws SQLException {
		Connection con = null;
		boolean flag = true;
		String select = "or";
		String and = " and ";


		try {
			con = DBManager.getConnection();

			String sql ="select count(*) as cnt from m_item where name like ";
			String[] searchWords = searchWord.split("　", 0);
			for(String word:searchWords) {
			  word=" '%"+word+"%' ";
			  if(flag) {
				  sql += word;
				  flag = false;
			  }else {
				  sql = sql +" "+select+" name like " + word;
				  }
			}

			if(itemId!=-1) {
				sql = sql +and+"id ="+itemId;
			}

			if(!startDate.equals("nodate")) {
				startDate = "'"+startDate+"'";
				sql = sql +and+"create_date >= "+startDate;
			}

			if(!endDate.equals("nodate")) {
				endDate = "'"+endDate+"'";
				sql = sql +and+"create_date <= "+endDate;
			}

			if(startPrice!=-1) {
			    sql = sql +and+"price >= "+startPrice+" ";
			    		}
			if(endPrice!=-1) {
			    sql = sql+and+"price <= "+endPrice+" ";
			}
			//System.out.println(sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//test
			//System.out.println(rs);
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

}
