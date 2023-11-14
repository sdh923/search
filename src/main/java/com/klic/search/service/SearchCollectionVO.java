package com.klic.search.service;

import java.util.List;

/**
 * 검색결과 컬렉션 VO
 *
 */
public class SearchCollectionVO {
	/** 컬렉션 한글명 */
	private String nameKr = "";
	/** 컬렉션 영문명*/
	private String nameEn = "";
	/** 컬렉션의 총검색 건수 */
	private int totalCount = 0;
	/** 컬렉션의 반환되는 검색 건수 */
	private int resultCount = 0;
	/** 컬렉션의 검색결과 리스트 */
	private List<?> searchResultList = null;
	
	public String getNameKr() {
		return nameKr;
	}

	public void setNameKr(String nameKr) {
		this.nameKr = nameKr;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	

	public List<?> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<?> searchResultList) {
		this.searchResultList = searchResultList;
	}


}
