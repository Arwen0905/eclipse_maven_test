package tradevan;

package gov.customs.aci.cs.ch.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.tradevan.commons.collection.DataList;
import com.tradevan.commons.collection.DataObject;
import com.tradevan.taurus.xdao.DataPage;
import com.tradevan.taurus.xdao.XdaoException;

import gov.customs.aci.cs.dao.DefaultModel;
import gov.customs.aci.commons.bean.SelElem;
import gov.customs.aci.cs.ch.bean.CH004Bean;
import gov.customs.commons.service.DefaultService;
import gov.customs.commons.service.ProcessResult;

public class CH004Service extends DefaultService {
	
	/**
	 * getOb 說明：執行sql查詢之前，帶入的查詢條件<br>
	 * 
	 * @param ch004Bean
	 * @return DataObject
	 */
	public DataObject getOb(CH004Bean ch004Bean) {
		
		DataObject ob = new DataObject();
		
		ob.setValue("IE_TYPE", ch004Bean.getIeType());
		ob.setValue("STARTD", ch004Bean.getStartD());
		ob.setValue("ENDD", ch004Bean.getEndD());
		ob.setValue("evOfficerId", ch004Bean.getEvOfficerId());
		

		//將複數checkbox作為sql(CHECK_BOX)條件判斷
		String checkBoxSql = "";
		boolean addOR = false;
		if(ch004Bean.getDealClosed().equals("true")) {			
			checkBoxSql += "(M.CLS_DATE IS NOT NULL)";
			addOR=true;
		}
		if(ch004Bean.getDealNotYet().equals("true")) {
			if(addOR==true) {checkBoxSql += "OR ";
			}
			checkBoxSql += "((M.CLS_DATE IS NULL) AND (M.DOC_DATE IS NOT NULL))";
			addOR=true;
		}
		if(ch004Bean.getDocnoNotYet().equals("true")) {				
			if(addOR==true) {
				checkBoxSql += "OR ";
			}
			checkBoxSql += "(M.DOC_DATE IS NULL)";
			addOR=true;
		}
		if(addOR==true && checkBoxSql.split("OR").length!=3) {
			ob.setValue("CHECK_BOX", checkBoxSql);
		}
		

		//將連動式下拉選單查詢條件作為sql(ISSUE_UNIT)條件判斷
		String issueUnitSql = "";
		if(StringUtils.isNotBlank(ch004Bean.getCustCd())) {
			issueUnitSql+=ch004Bean.getCustCd();
		}
		if(StringUtils.isNotBlank(ch004Bean.getUnitId())) {
			issueUnitSql+=ch004Bean.getUnitId();
		}
		if(StringUtils.isNotBlank(ch004Bean.getWrkUnit())) {
			issueUnitSql+=ch004Bean.getWrkUnit();
		}
		if(StringUtils.isNotBlank(ch004Bean.getTeam())) {
			issueUnitSql+=ch004Bean.getTeam();
		}
		ob.setValue("ISSUE_UNIT", issueUnitSql);
		
		//排序條件
		ob.setValue("SORT_STATUS", ch004Bean.getSortStatus());
		
		return ob;
	}
	/**
	 * queryCSBFAMM 說明：根據條件查詢CSBFAMM資料表資料<br>
	 * @param ch004Bean
	 * @return ProcessResult
	 * @throws Exception
	 */
	public ProcessResult queryCSBFAMM(CH004Bean ch004Bean) throws Exception {
		
		ProcessResult result = new ProcessResult();
		
		try {
			
			DataPage dataPage = DefaultModel.getTemplate().getDataPage("CH004.queryCSBFAMM", getOb(ch004Bean));
			
			result.setStatus(ProcessResult.OK);
			
			if(dataPage.getTotalPages()==0) {
				result.addMessage(getText(MCOD004));
			}else {
				result.addMessage(getText(ECOP002));
				result.setDataPage(dataPage);
			}
			
		} catch (Exception ex) {
			logger.error("CH004Service.queryCSBFAMM : ", ex);
			result.setStatus(ProcessResult.NG);
			result.addError(getText(ECOD005));
		}
		
		return result;
	}
	
	/**
	 * getDataList 說明：取得查詢資料.<br>
	 * @param ch004Bean
	 * @return DataList
	 */
	public DataList getDataList(CH004Bean ch004Bean) {
		
		DataList dList = new DataList(); 
		try{
			dList = DefaultModel.getTemplate().executeQuery("CH004.queryCSBFAMM", getOb(ch004Bean)).getResult();
		}
		catch (XdaoException e) {
			e.printStackTrace();
		}
		return dList;
	}
	
	/**
	 * CH004_custCdQuery 說明：取得關別資料<br>
	 * 
	 * @param ob
	 * @return List<SelElem>
	 */
	public List CH004_custCdQuery()  {
		
		DataList dlist = null;
		DataObject ob = new DataObject();
		List<SelElem> list = new ArrayList<SelElem>();
		
		try {
			
			dlist = DefaultModel.getTemplate().executeQuery("CH004.custCdQueryCSACODM", ob).getResult();

			for (int i = 0; i < dlist.size(); i++) {
				list.add(kindList((String)dlist.get(i).getValue("CODE"), dlist.get(i).getValue("CODE")+":"+dlist.get(i).getValue("CODE_CHINESE")));
			}
			
		} catch (XdaoException ex) {
			logger.error("CH004Service.custCdQueryCSACODM : ", ex);
			ex.printStackTrace();
		}
		return list;
	}
	
	private SelElem kindList(String key,String value) {
		SelElem bean = new SelElem(key, value);
		return bean;
	}
}
