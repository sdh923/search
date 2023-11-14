package com.klic.search.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.klic.search.service.SearchCollectionResult;
import com.klic.search.service.SearchCollectionVO;
import com.klic.search.service.SearchService;
import com.klic.search.service.SearchVO;
import com.klic.search.service.Sf1VO;
import com.klic.search.service.TotalSearchService;
import com.klic.search.service.common.WNCollection;
import com.klic.search.service.common.WNSearch;
import com.klic.search.service.common.WNUtils;

@Service("TotalSearchService")
public class TotalSearchServiceImpl implements TotalSearchService{

	/** 검색 결과 받기 */
	@Override
	public SearchCollectionResult getSearch(SearchVO searchVO, Sf1VO sf1Vo) throws Exception {
		boolean isDebug = false;
		String[] collections = null;
		String[] searchFields = null;
		boolean isRealTimeKeyword = false;
		boolean useSuggestedQuery = true;
		
		/** 검색어 */
		String query = searchVO.getQuery();
		query = WNUtils.checkReqXSS(query, "");
		/** 결과내 검색어 */
		String realQuery = searchVO.getRealQuery();
		realQuery = WNUtils.checkReqXSS(realQuery, "");
		/** 결과내 재검색 체크필드 */
		String reQuery = searchVO.getReQuery();
		reQuery = WNUtils.checkReqXSS(reQuery, "");
		/** 연산자 */
		String operator = searchVO.getOperator();
		operator = WNUtils.checkReqXSS(operator, "");
		/** 정렬조건 */
		String sortField = searchVO.getSortField();
		sortField = WNUtils.checkReqXSS(sortField, "DATE");
		/** 정렬 방식*/
		String sortOrder = searchVO.getSortOrder();
		sortOrder = WNUtils.checkReqXSS(sortOrder, "DESC");
		/** 검색 필드*/
		String searchField = searchVO.getSearchField();
		searchField = WNUtils.checkReqXSS(searchField, "ALL");
		
		/** 출력갯수 */
		int viewCount = searchVO.getBlockCount();
		if(viewCount  == 0){
			viewCount = 4;
		}
		/** 시작위치 */
		int startCount = searchVO.getStartNo();

		/** 검색 시작날짜 */
		String startDate = searchVO.getStartDate();
		startDate = WNUtils.checkReqXSS(startDate, "1970/01/01");
		/** 검색 종료날짜*/
		String endDate = searchVO.getEndDate();
		endDate = WNUtils.checkReqXSS(endDate, WNUtils.getCurrentDate());
		/** 컬렉션 이름*/
		String collection = searchVO.getCollection();
		collection = WNUtils.checkReqXSS(collection, "ALL");
		/** 디버그 YN*/
		String debug = searchVO.getDebug();
		debug = WNUtils.checkReqXSS(debug, "N");
		
		// 디버그 설정
		if(debug.equals("Y")){
			isDebug = true;
		}else{
			isDebug = false;
		}
		
		//쿼리 설정
		if (reQuery.equals("1")) { 
			realQuery = query + " " + realQuery;
			searchVO.setReQuery("");
		} else if (!reQuery.equals("2") || "".equals(realQuery)) {
			realQuery = query;
		}
		// 컬렉션 설정
		if(collection.equals("ALL")){
			collections = new String[]{"webpage","menu","board","file"};
		}else{
			collections = collection.split(",");
		}
	
		String exquery = ""; //exquery 조건 필드
		String filterquery = ""; //filterquery 조건 필드
		String realfilterquery = ""; //filterquery 실행 조건 필드
		String categoryqeury = ""; //operation 조건 필드
		
		/** 작성일 시작 검색*/
		if(searchVO.getCreateDtStart() !="") {
			filterquery+="<CREATE_DT:gte:"+searchVO.getCreateDtStart()+">";
		}
		/** 작성일 종료 검색*/
		if(searchVO.getCreateDtEnd() !="") {
			filterquery+="<CREATE_DT:lte:"+searchVO.getCreateDtEnd()+">";
		}
		/** 수정일 시작 검색*/
		if(searchVO.getUpdtDtStart() !="") {
			filterquery+="<UPDT_DT:gte:"+searchVO.getUpdtDtStart()+">";
		}
		/** 수정일 종료 검색*/
		if(searchVO.getUpdtDtEnd() !="") {
			filterquery+="<UPDT_DT:lte:"+searchVO.getUpdtDtEnd()+">";
		}		

		/** 기관 코드 검색*/
		if(searchVO.getSysTy() !="") {
			exquery+="<SYS_TY:contains:"+searchVO.getSysTy()+">";
		}
		
		WNSearch wnsearch = new WNSearch(isDebug,false, collections, searchFields);
		
		wnsearch.setSf1Vo(sf1Vo);
		
		for (int i = 0; i < collections.length; i++) {
			// PAGE_INFO 페이지 
			wnsearch.setCollectionInfoValue(collections[i], WNCollection.PAGE_INFO, startCount+","+viewCount);
			// DATE_RANGE 날짜
			wnsearch.setCollectionInfoValue(collections[i], WNCollection.DATE_RANGE, startDate.replaceAll("[.]", "/") + ","+endDate.replaceAll("[.]", "/")+",-");
			
			//searchField 값이 있으면 설정, 없으면 기본검색필드
	        if (!searchField.equals("")  && searchField.indexOf("ALL") == -1 ) {
		        wnsearch.setCollectionInfoValue(collections[i], WNCollection.SEARCH_FIELD, searchField);
			}
	        
	    }
		
		if("OR".equals(operator)){
			realQuery = realQuery.replaceAll(" " , "|");
		}
		
		wnsearch.search(realQuery, isRealTimeKeyword, WNCollection.CONNECTION_CLOSE, useSuggestedQuery);
		searchVO.setRealQuery(realQuery);
		// 결과 담기
		SearchCollectionResult collResultList = new SearchCollectionResult();
		
		// 디버그 메시지 출력
	    String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
	    collResultList.setDebugStr(debugMsg);
	     
	    int errorCode = wnsearch.getErrorCode();
	    collResultList.setErrorCode(errorCode);
	    
	    
	    // 결과  arrList
	    ArrayList<SearchCollectionVO> collList = new ArrayList<SearchCollectionVO>();
	    for (int i = 0; i < collections.length; i++) {	// 컬렉션 for
	    	SearchCollectionVO colVo = new SearchCollectionVO();
	    	int totalCount = wnsearch.getResultTotalCount(collections[i]);
	    	int resultCount = wnsearch.getResultCount(collections[i]);
	    	String collectionNameKr = wnsearch.getCollectionFieldValue(collections[i], WNCollection.COLLECTION_KOR);
	    	String collectionNameEn = wnsearch.getCollectionFieldValue(collections[i], WNCollection.INDEX_NAME);
	  
	    	String indexField = "," + wnsearch.getCollectionFieldValue(collections[i], WNCollection.SEARCH_FIELD) + ",";
	    	String resultField = wnsearch.getCollectionFieldValue(collections[i], WNCollection.RESULT_FIELD);
	    	String displayResultField = wnsearch.getCollectionFieldValue(collections[i], WNCollection.DISPPLAY_RESULT_FIELD);
	    	
  
    		String[] resultFieldArr = resultField.split(",");
    		String[] displayResultFieldArr = displayResultField.split(",");
    	
    		/** map 방식  1. */
    		ArrayList<Map<Object ,Object>> resultList = new ArrayList<Map<Object ,Object>>();  
    		
    		for(int j = 0 ; j < resultCount ; j++){	// 검색 결과 for
	    		Map<Object ,Object> resultMap = new HashMap<Object,Object>();
	    		for(int k = 0 ; k < resultFieldArr.length ; k ++){ // 반환필드 결과 for
	    			String[] documentFields = WNUtils.split(resultFieldArr[k], "/");
	    			//NONE 인경우 출력 제외 
	    			if(!"NONE".equals(displayResultFieldArr[k])) {
	    				if(indexField.indexOf(documentFields[0]) > -1){
	    					String resultData = (wnsearch.getField(collections[i],documentFields[0], j , false));
	    					resultMap.put(displayResultFieldArr[k], resultData );	    				
	    				}else{
	    					String resultData = wnsearch.getField(collections[i],documentFields[0] , j , false);

		    				resultMap.put(displayResultFieldArr[k],resultData);
		    			}	
	    			}
	    			
	    		}
	    		resultList.add(j,resultMap);
	    	}
	    	
    		
    		if(totalCount < 0 ){
    			colVo.setTotalCount(0);
    		}else{
    			colVo.setTotalCount(totalCount);
    		}
	    	colVo.setResultCount(resultCount);
	    	colVo.setNameKr(collectionNameKr);
	    	colVo.setNameEn(collectionNameEn);
			colVo.setSearchResultList(resultList);
	    	collList.add(i,colVo);
	    }
	    
	    collResultList.setCollectionList(collList);
		
	    // 연결 종료
	    if ( wnsearch != null ) {
			wnsearch.closeServer();
		}
		return collResultList;
	}

}
