package helloworld.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opensymphony.xwork2.ActionSupport;

public class AccessAction extends ActionSupport {
	
	public String execute() {
		String ret = ERROR;
		Connection conn = null;
		try {
			String URL = "jdbc:mysql://localhost:3307/lotto649?ServerTimezoen";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
	}
	
}
