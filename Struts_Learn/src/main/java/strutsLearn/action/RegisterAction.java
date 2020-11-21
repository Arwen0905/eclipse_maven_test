package strutsLearn.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import strutsLearn.model.PersonBean;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonBean person;
	private boolean checkMe;
	private RegisterService rService = new RegisterService();
	private String comeDB;
	private RegisterService queryMM = new RegisterService();
	
	public String getComeDB() {
		return comeDB;
	}

	public void setComeDB(String comeDB) {
		this.comeDB = comeDB;
	}

	public boolean isCheckMe() {
		return checkMe;
	}

	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}

	public String register() throws Exception {
		return "register";
	}
	
	public PersonBean getPerson() {
		return person;
	}

	public void setPerson(PersonBean person) {
		this.person = person;
	}
	
	
	private List bfCustCdList;
	
	public List getBfCustCdList() {
		bfCustCdList = rService.queryDBMM();
		return bfCustCdList;
	}

	public void setBfCustCdList(List bfCustCdList) {
		this.bfCustCdList = bfCustCdList;
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
