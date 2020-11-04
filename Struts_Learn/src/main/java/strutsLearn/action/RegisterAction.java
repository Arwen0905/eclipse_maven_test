package strutsLearn.action;

import com.opensymphony.xwork2.ActionSupport;
import strutsLearn.model.PersonBean;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private PersonBean person = new PersonBean();
	
	public String register() throws Exception {
		return "register";
	}
	
	public PersonBean getPerson() {
		return person;
	}

	public void setPerson(PersonBean person) {
		this.person = person;
	}
	
//	validation
	public void validate() {
//		if(person.getAccount().length() == 0) {
//			addFieldError("person.account","QQQ");
//		}
//		if(person.getPassword().length() == 0) {			
//			addFieldError("person.password","QQQ");
//		}
//		if(person.getEmail().length() == 0) {
//			addFieldError("person.email", "QQQ");
//		}
	}
}
