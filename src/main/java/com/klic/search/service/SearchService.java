package com.klic.search.service;

import java.util.HashMap;

/**
 * @Class Name : SearchService.java
 * @Description : SearchService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2016.02.23   박도식                최초생성
 *
 * @author 솔루션사업본부 공공개발2팀 박도식
 * @since 2016. 02. 23
 * @version 1.0
 * @see
 *
 */

public interface SearchService {
	
	/** 검색결과 받기  */
	SearchCollectionResult getSearch(SearchVO searchVO , Sf1VO sf1Vo) throws Exception;

}
