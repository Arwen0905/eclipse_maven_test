package strutsLearn.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import strutsLearn.model.PersonBean;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonBean person;
	private boolean checkMe;
	private RegisterService rService = new RegisterService();
	private List bfCustCdList;
	private List responseDataBase;
	static int count = 0;
	

	public boolean isCheckMe() {
		return checkMe;
	}
	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}
	
	public String register() throws Exception {
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		setBfCustCdList(rService.queryList());
		responseDataBase = rService.queryDBMM();
		System.out.println(responseDataBase);
		return "register";
	}
	
	public PersonBean getPerson() {
		return person;
	}
	public void setPerson(PersonBean person) {
		this.person = person;
	}
	
	
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
