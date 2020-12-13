package strutsLearn.action;


import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;//toDo..

import com.opensymphony.xwork2.ActionSupport;

public class RegisterService extends ActionSupport {
	

	private static final long serialVersionUID = 1L;
	
	private List<Object> qlist = new ArrayList<Object>();
	
	public List<Object> queryList() {
		for(int i=0; i<10; i++) {
			qlist.add(i, "資料"+i);
		}
		return qlist;
	}
	
	public List<String> queryDBMM() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rse = null;
		
		String db_name = "arwen_db";
		String code = "AA";
		
		List<String> dList = new ArrayList<String>();
		try {
			
//			1.加載資料庫驅動
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/"+ db_name +"?serverTimezone=UTC","root","root");
			pstmt = con.prepareStatement("SELECT * FROM customs_table");
			
//			pstmt.setString(1, code);
			
//			通過陳述執行sql
			rse = pstmt.executeQuery();

//			對sql執行結果進行解析處理
			while(rse.next()){
				dList.add(rse.getString(1)+"："+rse.getString(2)+":"+rse.getString(3));
				}

		} catch (Exception e) {
			System.out.println("資料庫連線錯誤："+ e);
		} finally{
//			釋放資源
			if(rse!=null){
				try {
					rse.close();
					System.out.println("釋 放 資 源： 執行成功");
				} catch (Exception e) {
					System.out.println("釋 放 資 源：出現錯誤");
					e.printStackTrace();
				}
			}
		}
		return dList;
	}
	
}
