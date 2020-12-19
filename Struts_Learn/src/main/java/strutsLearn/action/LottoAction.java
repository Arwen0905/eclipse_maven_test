package strutsLearn.action;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.opensymphony.xwork2.ActionSupport;
import strutsLearn.service.LottoService;

public class LottoAction extends ActionSupport{
	
	private LottoService lotService = new LottoService();

	private String n1;
	private String n2;
	private String n3;
	private String n4;
	private String n5;
	private String n6;
	private String year;
	private TreeMap<String,String> lotto = new TreeMap<>();
	
	List<String> userLotto = new ArrayList<String>(); 

	public String lotto() {
		userLotto.add(n1);
		userLotto.add(n2);
		userLotto.add(n3);
		userLotto.add(n4);
		userLotto.add(n5);
		userLotto.add(n6);
		
		System.out.print("對獎號碼：");
		String comma = "";
		for(String i:userLotto) {
			System.out.print(comma + i);
			comma = ",";
		}
		System.out.println("\n取得年份：" + year);
		setLotto(lotService.detect(userLotto, year));
		return SUCCESS;
	}
	
	
	/**傳回JSP的資料**/
	public TreeMap<String, String> getLotto() {
		return lotto;
	}
	public void setLotto(TreeMap<String, String> lotto) {
		this.lotto = lotto;
	}

	/** Access DataBase **/
	public LottoService getLotService() {
		return lotService;
	}
	public void setLotService(LottoService lotService) {
		this.lotService = lotService;
	}
	
	/** getter & setter **/
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getN1() {
		return n1;
	}
	public void setN1(String n1) {
		this.n1 = n1;
	}
	public String getN2() {
		return n2;
	}
	public void setN2(String n2) {
		this.n2 = n2;
	}
	public String getN3() {
		return n3;
	}
	public void setN3(String n3) {
		this.n3 = n3;
	}
	public String getN4() {
		return n4;
	}
	public void setN4(String n4) {
		this.n4 = n4;
	}
	public String getN5() {
		return n5;
	}
	public void setN5(String n5) {
		this.n5 = n5;
	}
	public String getN6() {
		return n6;
	}
	public void setN6(String n6) {
		this.n6 = n6;
	}
	
//	@Override
//	public Object getModel() {
//		return lottoBean;
//	}
}
