package com.util;
public class PageUtil {
	public static PageInfo getPageInfo(int pageSize,int rowCount,int pageIndex){
		PageInfo p=new PageInfo();
		p.setPageSize(pageSize==0?10:pageSize);
		p.setBeginRow(pageSize*(pageIndex-1));
		p.setPageCount((rowCount+pageSize-1)/pageSize);
		p.setRowCount(rowCount);
		p.setPageIndex(pageIndex);
		p.setHasNext(!(pageIndex==0||pageIndex==p.getPageCount()));
		p.setHasPre(pageIndex!=1);
		return p;
	}
}
