package strutsLearn.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

import java.sql.*;
import java.util.Iterator;//toDo..

import com.opensymphony.xwork2.ActionSupport;

public class RegisterService extends ActionSupport {
	
	private List list;
	
	public List queryDBMM() {
		
		try {
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/lotto649?serverTimezone=UTC","root","root");
	           
	           PreparedStatement ps = conn.prepareStatement("SELECT * FROM arwen_db.customs_table");

	    	ResultSet rs = ps.executeQuery();
	    	System.out.println(rs);
	    	while(rs.next()) {
	    		System.out.println(rs.getString(2) + ":" + rs.getString(3));
	    	}
         
		} catch (Exception e) {
			System.out.println("資料庫連線錯誤! "+e);
		}
		return list;
	}
	
}
