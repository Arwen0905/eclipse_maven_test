package tradevan;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CH004Bean {
	
	private String ieType;	//進出口
	private String startD;	//日期起
	private String endD;	//日期訖
	
	private String dealClosed;	//已結案
	private String dealNotYet;	//未結案
	private String docnoNotYet; //有文號未發文
	
	private String custCd; 	//關別
	private String unitId;		//單位
	private String wrkUnit;		//課別
	private String team;		//股別
	
	private String evOfficerId;	//承辦人
	private String sortStatus;	//排序條件
	private String issueUnit;	//查詢單位
	
	private String issueUnitChinese; //查詢單位(中文)#列印使用

	
	public String getIssueUnitChinese() {
		return issueUnitChinese;
	}
	public void setIssueUnitChinese(String issueUnitChinese) {
		this.issueUnitChinese = issueUnitChinese;
	}
	
	
	public String getIssueUnit() {
		return issueUnit;
	}
	public void setIssueUnit(String issueUnit) {
		this.issueUnit = issueUnit;
	}
	
	
	public String getIeType() {
		return ieType;
	}
	public void setIeType(String ieType) {
		this.ieType = ieType;
	}
	
	
	public String getStartD() {
		return startD;
	}
	public void setStartD(String startD) {
		this.startD = startD;
	}

	
	public String getEndD() {
		return endD;
	}
	public void setEndD(String endD) {
		this.endD = endD;
	}
	
	
	public String getDealClosed() {
		return dealClosed;
	}
	public void setDealClosed(String dealClosed) {
		this.dealClosed = dealClosed;
	}
	
	
	public String getDealNotYet() {
		return dealNotYet;
	}
	public void setDealNotYet(String dealNotYet) {
		this.dealNotYet = dealNotYet;
	}
	
	
	public String getDocnoNotYet() {
		return docnoNotYet;
	}
	public void setDocnoNotYet(String docnoNotYet) {
		this.docnoNotYet = docnoNotYet;
	}
	
	
	public String getCustCd() {
		return custCd;
	}
	public void setCustCd(String custCd) {
		this.custCd = custCd;
	}
	
	
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	
	public String getWrkUnit() {
		return wrkUnit;
	}
	public void setWrkUnit(String wrkUnit) {
		this.wrkUnit = wrkUnit;
	}
	
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	
	public String getEvOfficerId() {
		return evOfficerId;
	}
	public void setEvOfficerId(String evOfficerId) {
		this.evOfficerId = evOfficerId;
	}
	
	
	public String getSortStatus() {
		return sortStatus;
	}
	public void setSortStatus(String sortStatus) {
		this.sortStatus = sortStatus;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
