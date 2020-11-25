package strutsLearn.action;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;//toDo..

import com.opensymphony.xwork2.ActionSupport;

public class RegisterService extends ActionSupport {
	
	private List qlist = new ArrayList();
	
	public List queryDBMM() {
		
		try {
			for(int i=0; i<10; i++) {
				qlist.add(i, "資料"+i);
			}
         
		} catch (Exception e) {
			System.out.println("錯誤! "+e);
		}
		return qlist;
	}
	
}
