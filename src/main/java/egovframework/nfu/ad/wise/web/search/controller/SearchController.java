package egovframework.nfu.ad.wise.web.search.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.nfu.ad.wise.web.search.service.ArkService;
import egovframework.nfu.ad.wise.web.search.service.ArkVO;
import egovframework.nfu.ad.wise.web.search.service.PopWordService;
import egovframework.nfu.ad.wise.web.search.service.PopWordVO;
import egovframework.nfu.ad.wise.web.search.service.RecommendService;
import egovframework.nfu.ad.wise.web.search.service.SearchCollectionResult;
import egovframework.nfu.ad.wise.web.search.service.SearchCollectionVO;
import egovframework.nfu.ad.wise.web.search.service.SearchVO;
import egovframework.nfu.ad.wise.web.search.service.Sf1VO;
import egovframework.nfu.ad.wise.web.search.service.TeaVO;
import egovframework.nfu.ad.wise.web.search.service.TopicCloudService;
import egovframework.nfu.ad.wise.web.search.service.TotalSearchService;
import egovframework.nfu.ad.wise.web.search.service.common.WNUtils;

@Controller
public class SearchController {
	
	/** 통합 검색  */
	@Resource(name="TotalSearchService")
	private TotalSearchService totalSearchService;	
	
	/** 자동완성  */
	@Resource(name="ArkService")
	private ArkService arkService;
	
	/** 인기검색어  */
	@Resource(name="PopwordService")
	private PopWordService popWordService;
	
	/** 연관검색어  */
	@Resource(name="RecommendService")
	private RecommendService recommendService;
	
	/** 주제클라우드  */
	@Resource(name="TopicCloudService")
	private TopicCloudService topicCloudService;
	
	
	
	
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
	
	/** 자동완성 결과 */
	@ResponseBody
	@RequestMapping(value="/search/ark.do", produces="application/json; charset=utf-8")
	public Map<String, Object> ark(ArkVO arkVO) throws Exception {

		Sf1VO sf1Vo = new Sf1VO();
		String arkStr = arkService.getArk(arkVO, sf1Vo);
		Map<String, Object> result = new ObjectMapper().readValue(arkStr, new TypeReference<Map<String, Object>>() {});
		return result;
	}
	
	/** 인기검색어 결과 */
	@ResponseBody
	@RequestMapping(value="/search/popword.do", produces="application/json; charset=utf-8")
	public Map<String, Object> popword(PopWordVO popWordVo) throws Exception {
		Sf1VO sf1Vo = new Sf1VO();
		String popWordStr = popWordService.getPopWord(popWordVo, sf1Vo);
		Map<String, Object> result = new ObjectMapper().readValue(popWordStr, new TypeReference<Map<String, Object>>() {});
		
		return result;
	}	

	/** 연과검색어 결과 */
	@ResponseBody
	@RequestMapping(value="/search/recommend.do", produces="application/json; charset=utf-8")
	public Map<String, Object> recommend(TeaVO teaVO) throws Exception {
		String popWordStr = recommendService.getRecommend(teaVO);
		Map<String, Object> result = new ObjectMapper().readValue(popWordStr, new TypeReference<Map<String, Object>>() {});
		
		return result;
	}
	
	/** 주제 클라우드 결과 */
	@ResponseBody
	@RequestMapping(value="/search/topicCloud.do", produces="application/json; charset=utf-8")
	public Map<String, Object> topicCloud(TeaVO teaVO) throws Exception {
		String popWordStr = topicCloudService.topicCloud(teaVO);
		Map<String, Object> result = new ObjectMapper().readValue(popWordStr, new TypeReference<Map<String, Object>>() {});
		
		return result;
	}	
}
