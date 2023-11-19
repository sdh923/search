package egovframework.nfu.ad.wise.web.search.service;

import java.util.ArrayList;

public class SearchCollectionResult {
	/** 디버그 메세지 */
	private String debugStr = "";
	/** 엔진 에러 코드 */
	private int errorCode = 0;
	/** 컬렉션 리스트 */
	private ArrayList<SearchCollectionVO> collectionList = null;
	public String getDebugStr() {
		return debugStr;
	}
	public void setDebugStr(String debugStr) {
		this.debugStr = debugStr;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public ArrayList<SearchCollectionVO> getCollectionList() {
		return collectionList;
	}
	public void setCollectionList(ArrayList<SearchCollectionVO> collectionList) {
		this.collectionList = collectionList;
	}
	

	
}
