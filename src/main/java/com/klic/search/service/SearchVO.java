package com.klic.search.service;


public class SearchVO {
	/** 검색어 */
	String query = "" ;
	/** 검색어(최종) */
	String realQuery = "" ;
	/** 결과내 재검색*/
	String reQuery = "";
	/** 검색어 연산자*/
	String operator = "";
	/** 정렬조건 */
	String sortField = "";
	/** 정렬 방식*/
	String sortOrder = "";
	/** 검색 필드*/
	String searchField = "";
	/** 검색 기간*/
	String range = "";
	/** 출력갯수 */
	int blockCount = 10;
	/** 시작위치 */
	int startNo = 0;
	/** 검색 시작날짜 */
	String startDate = "";
	/** 검색 종료날짜*/
	String endDate = "";
	/** 컬렉션 그룹*/
	String collectionGroup = "ALL";
	/** 컬렉션 이름*/
	String collection = "ALL";
	/** 디버그 YN*/
	String debug = "N";
	
	/** 작성일 시작일*/
	String  createDtStart="";
	/** 작성일 종료일*/
	String  createDtEnd="";
	/** 수정일 시작일*/
	String  updtDtStart="";
	/** 수정일 종료일*/
	String  updtDtEnd="";
	/** 기관 검색 코드*/
	String  sysTy="";
	/** 시스템 검색 코드*/
	String  sysId="";
	
	
	/** 정렬 조건 검색*/
	String order = "";
	

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getRealQuery() {
		return realQuery;
	}
	public void setRealQuery(String realQuery) {
		this.realQuery = realQuery;
	}
	public String getReQuery() {
		return reQuery;
	}
	public void setReQuery(String reQuery) {
		this.reQuery = reQuery;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public int getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCollectionGroup() {
		return collectionGroup;
	}
	public void setCollectionGroup(String collectionGroup) {
		this.collectionGroup = collectionGroup;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getDebug() {
		return debug;
	}
	public void setDebug(String debug) {
		this.debug = debug;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCreateDtStart() {
		return createDtStart;
	}
	public void setCreateDtStart(String createDtStart) {
		this.createDtStart = createDtStart;
	}
	public String getCreateDtEnd() {
		return createDtEnd;
	}
	public void setCreateDtEnd(String createDtEnd) {
		this.createDtEnd = createDtEnd;
	}
	public String getUpdtDtStart() {
		return updtDtStart;
	}
	public void setUpdtDtStart(String updtDtStart) {
		this.updtDtStart = updtDtStart;
	}
	public String getUpdtDtEnd() {
		return updtDtEnd;
	}
	public void setUpdtDtEnd(String updtDtEnd) {
		this.updtDtEnd = updtDtEnd;
	}
	public String getSysTy() {
		return sysTy;
	}
	public void setSysTy(String sysTy) {
		this.sysTy = sysTy;
	}	
	
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}	
	
	
	
	
}
