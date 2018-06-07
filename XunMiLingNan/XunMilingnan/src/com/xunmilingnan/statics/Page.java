package com.xunmilingnan.statics;

import java.util.List;

/**
 * 
 * Description: <br />
 * Project: abc <br />
 * ClassName: Page <br />
 * @author wangwei
 * @version 1.0 2014骞�10鏈�21鏃ヤ笅鍗�3:11:27
 *
 * @param <T>
 */
public class Page<T> {
	private List<T> list;//需要分页的列表
	private int currentPageNum;//当前页码
	private int pageSize;//每页显示的条数
//	private int prePageNum;//上一页
//	private int nextPageNum;//下一页
	private int totalPageNum;//总页数
	private int totalCount;//总条数
	//构造
	public Page(){}
	
	public Page(int pageNum,int pageSize){
		this.currentPageNum=pageNum;//当前页码
		this.pageSize=pageSize;//每页显示的条数
	}
	public Page(int pageSize){
		this.pageSize=pageSize;//每页显示的条数
	}
	//需要分页的列表
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
		this.setTotalCount(list.size());
	}
	//当前页码
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		if(currentPageNum<=0)
			this.currentPageNum=1;
		else if(currentPageNum>=totalPageNum)
			this.currentPageNum=totalPageNum;
		else
			this.currentPageNum = currentPageNum;
	}
	//总页数
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
//	//上一页
//	public int getPrePageNum() {
//		return prePageNum;
//	}
//	public void setPrePageNum(int prePageNum) {
//			this.prePageNum = prePageNum;
//	}
//	//下一页
//	public int getNextPageNum() {
//		return nextPageNum;
//	}
//	public void setNextPageNum(int nextPageNum) {
//		this.nextPageNum = nextPageNum;
//	}
	//总条数
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount%pageSize==0)//总条数能否整除每页条数
			totalPageNum=totalCount/pageSize;//
		else
			totalPageNum=totalCount/pageSize+1;
			
//		if(currentPageNum>1)//当前页是否是第一页
//			prePageNum=currentPageNum-1;//上一页为当前页减一
//		else
//			prePageNum=1;
//		
//		if(currentPageNum<totalPageNum)//当前页是否是尾页
//			nextPageNum=currentPageNum+1;//上一页为当前页加一
//		else
//			nextPageNum=totalPageNum;
	}
	//每页显示的条数
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getPartList(){
		if(currentPageNum==totalPageNum) {
			return list.subList((currentPageNum-1)*pageSize, totalCount); 
		}else {
			return list.subList((currentPageNum-1)*pageSize, currentPageNum*pageSize); 
		} 
//		return list.subList((currentPageNum-1)*pageSize, currentPageNum*pageSize); 
	}
}
