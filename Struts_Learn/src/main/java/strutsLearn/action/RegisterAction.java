package strutsLearn.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import strutsLearn.model.PersonBean;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonBean person;
	
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
	private String qcustCd;
	public RegisterService rService = new RegisterService();
	
public List getBfCustCdList() {
	List list = new ArrayList();
	if(qcustCd==null) {
		bfCustCdList = rService.queryDBMM();
		System.out.println(bfCustCdList + " Requestttttttttttttttttttttttttt");
	}
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
