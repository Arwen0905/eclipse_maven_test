import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBase_Special {
	List list = new ArrayList();
	
	public List getListDB() {
		try {
			
//			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/arwen_db?serverTimezone=UTC","root","root");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM arwen_db.customs_table");
			
			System.out.println(ps);
			System.out.println(conn);

   	    	ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("id:"+rs.getString(2)+" 關別:"+rs.getString(2)+"  關區:"+rs.getString(3));
			}
   	    	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println( e );
			System.out.println("QQQQQQQQQQQ");
		}
		return list;
	}
	public static void main(String[] args) {
		DataBase_Special db = new DataBase_Special();
		db.getListDB();
	}
}