package beans;

import java.io.Serializable;


public class SearchDataBeans implements Serializable {

	/**
	 *
	 * 検索項目をまとめておくBeans
	 *
	 */

	private String name;
	private String startDate;
	private String endDate;
	private String loginId;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}






}
