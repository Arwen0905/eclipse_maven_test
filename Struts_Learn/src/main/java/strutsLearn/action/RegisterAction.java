package strutsLearn.action;

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
