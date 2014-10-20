package com.lgh.common.tools.page;

public class PageUtil {
	/**
	 * 
	 * getFirstResult:
	 * 
	 * @author 刘各欢
	 * @param page
	 * @param rows
	 * @return
	 * @since  fhd　Ver 1.1
	 */
	public static int getFirstResult(int page,int rows){
		int firstResult= (page-1)*rows;
		return firstResult;
	}
}
