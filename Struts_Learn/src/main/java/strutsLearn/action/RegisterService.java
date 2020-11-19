package strutsLearn.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;

public class RegisterService extends ActionSupport {
	private List qlist = new ArrayList();
	private String customs;
	
	public List queryDBMM() {
		try {
			for(int i=0; i<10; i++) {
				qlist.add(i, "資料"+i);
			}
			String URL = "jdbc:mysql://localhost:3307/arwen_db";
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection conn = DriverManager.getConnection(URL, "root", "root");
	         String sql = "SELECT * FROM arwen_db.customs_table";
	         
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery(sql);

	         while (rs.next()) {
	        	 customs = rs.getString(1);
	         }
	         System.out.println(customs+"<<<<<<<<<<<");
		} catch (Exception e) {
			System.out.println(customs+"<<<<<<<<<<<");
			System.out.println("Errrrror");
		}	
		return qlist;
	}
	
}
