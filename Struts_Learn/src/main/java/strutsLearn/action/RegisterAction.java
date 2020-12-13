package strutsLearn.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import strutsLearn.model.PersonBean;
import strutsLearn.service.LottoService;

/**
 * @see 測試連線功能
 * @author 6651
 *
 */
public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonBean person;
	private boolean checkMe;
	private RegisterService rgsService = new RegisterService();
	private List bfCustCdList;
	private List viewDataBase;
	static int count = 0;
	
	//勾選欄位
	public boolean isCheckMe() {
		return checkMe;
	}
	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}
	
//	/**產出查詢頁籤的關別下拉*/
//	@Override
//	public String execute() throws Exception {
//		setViewDataBase(rgsService.queryDBMM());
//		return super.execute();
//	}
	
	/**樂透頁面**/
	public String lotto() {
		return "lotto";
	}
	/**下拉選單內容**/
	public String selector(){
		//下拉選單取得資料庫查詢結果
		setViewDataBase(rgsService.queryDBMM());
		return "selector";
	}
	
	/**
	 * @see 進畫面即會執行
	 * @return register
	 *
	 */
	public String register() throws Exception {
		//下拉選單取得陣列迴圈
		setBfCustCdList(rgsService.queryList());
		return "register";
	}
	//選單內容的變數
	public List getViewDataBase() {
		return viewDataBase;
	}
	public void setViewDataBase(List viewDataBase) {
		this.viewDataBase = viewDataBase;
	}
	//Bean:帳號、密碼、信箱
	public PersonBean getPerson() {
		return person;
	}
	public void setPerson(PersonBean person) {
		this.person = person;
	}
	
	/**取得下拉選單內容**/
	public void setBfCustCdList(List bfCustCdList) {
		this.bfCustCdList = bfCustCdList;
	}
	public List getBfCustCdList() {
		return bfCustCdList;
	}


	//	validation
	public void validate() {
		try {
			if (person.getAccount().length() == 0) {
				addFieldError("person.account", "validate stop! account");
			}
			if(person.getPassword().length() == 0) {			
				addFieldError("person.password","validate stop! password");
			}
			if(person.getEmail().length() == 0) {
				addFieldError("person.email", "validate stop! email");
			}
		} catch (Exception e) {
		}
	}
}
