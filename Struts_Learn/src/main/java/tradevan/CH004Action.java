package tradevan;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.tradevan.commons.collection.DataList;

import gov.customs.aci.cs.ch.bean.CH004Bean;
import gov.customs.aci.cs.ch.service.CH004Service;
import gov.customs.commons.Reporter;
import gov.customs.commons.action.GridAction;
import gov.customs.commons.service.ProcessResult;
import gov.customs.commons.util.CommonJasperReportUtils;
import net.sf.jasperreports.engine.JRException;

/**
 * 作業代碼：CH004<br>
 * 作業名稱：通關疑義暨權責機關答覆聯絡單追蹤作業<br>
 * 程式代號：CH004Action.java<br>
 * 描述：根據選單進/出口、關別代碼其連動資料、承辦人、發文日期起迄 (預設空白)<br>
 * checkboxGroup欄位:已結案件、已發文未結案、已取文號未發文(可複選)，進行查詢與列印作業。<br>
* 公司：Tradevan Co.<br>
 *<br>【資料來源】：CSBFAMM(答聯單主檔)、CSBFAMD(答聯單明細檔)、CSACODM(代碼檔)<br>
 *<br>【輸出報表】：CH004_RPT<br>
 *<br>【異動紀錄】：<br>
 *<br>
 * @author	:6651<br>
 * @version	:1.0.0<br>
 */

public class CH004Action extends GridAction implements ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	
	private CH004Bean ch004Bean = new CH004Bean();
	private CH004Service ch004Service = new CH004Service();

	/**關別下拉選單*/
	private List<Object> bfCustCdList;

	/**產出查詢頁籤的關別下拉*/
	@Override
	public String execute() throws Exception {
		setBfCustCdList(ch004Service.CH004_custCdQuery());
		return super.execute();
	}

	/**
	 * queryCSBFAMM 說明：查詢通關疑義暨權責機關答覆聯絡單<br>
	 * 
 	 * @return processSuccess
	 * @Exception Exception
	 */
	public String queryCSBFAMM() {
		logger.info("run CH004Action.queryCSBFAMM()");
		
		try {
			ProcessResult result = ch004Service.queryCSBFAMM(ch004Bean);
			
			if(result.getStatus() == ProcessResult.OK) {
				setGridModel(result.getDataPage());

				return processSuccess(result.getMessages());
			}else {
				return processFail(result.getErrors());
			}
		} catch (Exception e) {
			logger.error("CH004Action.queryCSBFAMM() Exception:"+ e);
			return processException(e);
		}
	}
	
	/**
	 * print 說明：列印通關疑義暨權責機關答覆聯絡單<br>
	 * 
 	 * @param list
	 * @Exception JRException
	 * @Exception IOException
	 */
	public void print() throws Exception { 
		logger.info("run CH004Action.print()");
		
		HashMap<String,Object> params = new HashMap<String , Object>();

		DataList list = ch004Service.getDataList(ch004Bean);
		
		
		//放入查詢條件，及查詢條件名稱 
		params.put("pid", getPid());
		params.put("custCd", ch004Bean.getCustCd());
		params.put("issueUnit", ch004Bean.getIssueUnitChinese());
		params.put("userId", getUserData().getUserId());

		try {
			CommonJasperReportUtils.generateReport(request, response,params, Reporter.toMap(list.toList()));
		} catch (JRException e) {
			logger.error("CH004Action.print() JRException:"+ e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CH004Action.print() IOException:"+ e);
			e.printStackTrace();
		}
	
}
	
	/**
	 * getBfCustCdList 說明：取得查詢頁籤下拉選單的代碼內容<br>
	 * 
	 * @param bfCustCdList
	 * @return List<SelElem>
	 */
	public void setBfCustCdList(List bfCustCdList) {
		this.bfCustCdList = bfCustCdList;
	}
	
	public List getBfCustCdList() {
		return bfCustCdList;
	}

	
	//setter & getter
	public CH004Bean getCh004Bean() {
		return ch004Bean;
	}

	public void setCh004Bean(CH004Bean ch004Bean) {
		this.ch004Bean = ch004Bean;
	}

	@Override
	public Object getModel() {
		return ch004Bean;
	}
	
}