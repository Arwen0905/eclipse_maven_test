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
		ResultSet rls = null;
		
		List<String> dList = new ArrayList<String>();
		try {
			
//			1.加載資料庫驅動
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/lotto649?serverTimezone=UTC","root","root");

			String sql = "SELECT * FROM lotto649_2014";;

//			連結器執行 陳述語句
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, "開獎日期");
			
//			通過陳述執行sql
			rls = pstmt.executeQuery();
			
//			對sql執行結果進行解析處理
			while(rls.next()){
				dList.add("特別號："+rls.getString("特別號"));
				System.out.println(rls.getString("特別號"));

				}
		} catch (Exception e) {
			System.out.println("資料庫連線錯誤："+ e);
		} finally{
//			釋放資源
			if(rls!=null){
				try {
					System.out.println("釋放資源");
					rls.close();
				} catch (Exception e) {
					System.out.println("釋放資源 錯誤");
					e.printStackTrace();
				}
			}
		}
		return dList;
	}
	
}
