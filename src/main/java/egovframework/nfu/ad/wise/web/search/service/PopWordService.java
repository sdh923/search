package egovframework.nfu.ad.wise.web.search.service;

public interface PopWordService {
	/** 인기검색어  */
	String getPopWord(PopWordVO popWordVo , Sf1VO sf1Vo )throws Exception;
}
