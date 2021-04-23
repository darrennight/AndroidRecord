package com.hao.androidrecord.activity.indexBar;


import java.io.Serializable;

/**
 * 国家区号
 * @Title: CountryCode.java  
 * @Description: 
 * @author jiwei@breadtrip.com
 * @date 2015年2月28日 下午5:07:05  
 * @version V1.0
 */
public class CountryCode implements Serializable, PinnedListView.ICover {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4033000333897096865L;

	/**国家名*/
	private String chName;
	
	/**国家英文*/
	private String enName;
	
	/**首字母*/
	private String firstLetter;
	
	/**国家代码*/
	private String countryCode;
	
	/**国家区号*/
	private String countryNum;
	
	
	//==========下面是业务属性,考虑国家可能比较多，减少多次for循环====
	
	/**是否是第一个，列表展示属性*/
	private boolean isFirst;
	
	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCountryNum() {
		return countryNum;
	}

	public void setCountryNum(String countryNum) {
		this.countryNum = countryNum;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	@Override
	public boolean isCover() {
		return isFirst;
	}

	@Override
	public String getCoverText() {
		return firstLetter;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
