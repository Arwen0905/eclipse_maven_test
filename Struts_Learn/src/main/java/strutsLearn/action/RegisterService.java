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
	
	private List qlist = new ArrayList();
	
	public List queryList() {
		for(int i=0; i<10; i++) {
			qlist.add(i, "資料"+i);
		}
		return qlist;
	}
	
	public List queryDBMM() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List dList = new ArrayList();
		try {
			
//			1.加載資料庫驅動
			Class.forName("com.mysql.jdbc.Driver");
//			2.創建並獲取資料庫連接
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/arwen_db?serverTimezone=UTC", "root", "root");
//			3.設置sql語句
			String sql = "SELECT * FROM lotto649_2014";

//			設置sql語句中的參數(使用preparedStatement)
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, "期別");
//			通過statement執行sql
			resultSet = preparedStatement.executeQuery();
//			對sql執行結果進行解析處理
			System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
			while(resultSet.next()){
				dList.add("特別號："+resultSet.getString("特別號"));
				System.out.println(resultSet.getString("特別號"));

				}

		} catch (Exception e) {
			System.out.println("資料庫 連結錯誤：" + e);
		} finally{
//			釋放資源
			if(resultSet!=null){
				try {
					System.out.println("釋放資源");
					resultSet.close();
				} catch (Exception e) {
					System.out.println("釋放資源 錯誤");
					e.printStackTrace();
				}
			}
		}
		return dList;
	}
	
}
