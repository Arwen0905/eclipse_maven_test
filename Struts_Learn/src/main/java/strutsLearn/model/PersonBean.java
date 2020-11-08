package strutsLearn.model;

public class PersonBean {
	private String account;
	private String password;
	private String email;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return "帳戶：" + getAccount() 
			 + "密碼：" + getPassword() 
			 + "信箱：" + getEmail();
	}

}
