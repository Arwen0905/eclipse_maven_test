package strutsLearn.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LottoBean {
	private String n0;
	
	public String getN0() {
		return n0;
	}
	public void setN0(String n0) {
		this.n0 = n0;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
