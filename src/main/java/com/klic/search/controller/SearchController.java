package com.klic.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klic.search.service.SearchCollectionResult;
import com.klic.search.service.SearchCollectionVO;
import com.klic.search.service.SearchService;
import com.klic.search.service.SearchVO;
import com.klic.search.service.Sf1VO;
import com.klic.search.service.TotalSearchService;
import com.klic.search.service.common.WNUtils;

@Controller
public class SearchController {
	
	/** 통합 검색  */
	@Resource(name="TotalSearchService")
	private TotalSearchService totalSearchService;	
	
	
	/** 통합검색 결과 API(JSON)*/
	@ResponseBody
	@RequestMapping(value="/search/totalSearch.do", method= {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> totalSearch(SearchVO searchVO) throws Exception{
		Map<String, Object> result = (Map<String, Object>)allSearch(searchVO);
		return result;
	}

	private Object allSearch(SearchVO searchVO) throws Exception {

		// 검색관련 서버 설정
		Sf1VO sf1Vo = new Sf1VO();
		SearchCollectionResult collResult = totalSearchService.getSearch(searchVO, sf1Vo);
	
		// 결과
		//webpage
		SearchCollectionVO colWebpage = new SearchCollectionVO();	
		//menu
		SearchCollectionVO colMenu    = new SearchCollectionVO();	
		//board
		SearchCollectionVO colBoard   = new SearchCollectionVO();	
		//file
		SearchCollectionVO colFile    = new SearchCollectionVO();	
		
		//컬렉션 그룹명
		String collectionGroup = searchVO.getCollectionGroup();
		collectionGroup = WNUtils.checkReqXSS(collectionGroup, "ALL");
		//컬렉션 명
		String collection = searchVO.getCollection();
		collection = WNUtils.checkReqXSS(collection, "ALL");
		
		//총건수
		int totalCount = 0;
		
		ArrayList<SearchCollectionVO>  collList = collResult.getCollectionList();
		
		int collListSize = collList.size();
		for(int i = 0 ; i < collListSize ; i++){
			SearchCollectionVO coll = collList.get(i);
			String collEn = coll.getNameEn();
			
			if(collectionGroup.equals("ALL") || collEn.indexOf(collectionGroup) > -1){
				totalCount = totalCount + coll.getTotalCount();
			}

			if(collEn.equals("webpage")){
				colWebpage = coll;
			}else if(collEn.equals("menu")){
				colMenu = coll;
			}else if(collEn.equals("board")){
				colBoard = coll;
			}else if(collEn.equals("file")){
				colFile = coll;
			}
		}
		
		// - 값 (검색엔진 오류 일때  0값 으로 )
		if(totalCount < 0){
			totalCount = 0;
		}
		

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("searchKeyword", searchVO.getQuery());
		
		if(totalCount == 0){
			result.put("status","EMPTY");
			result.put("msg", "SIZE ZERO");
		}
		result.put("totalCount",totalCount);
		result.put("webpage",colWebpage);
		result.put("menu",colMenu);
		result.put("board",colBoard);
		result.put("file",colFile);


		return result;
	
		
	}
}
