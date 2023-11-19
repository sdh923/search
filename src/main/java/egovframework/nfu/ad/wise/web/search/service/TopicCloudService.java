package egovframework.nfu.ad.wise.web.search.service;

/**
 * @Class Name : SearchService.java
 * @Description : SearchService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2016.02.23   박도식                최초생성
 *
 */
public interface TopicCloudService {
	/** 자동완성  */
	public String topicCloud(TeaVO teaVO)throws Exception;
}
