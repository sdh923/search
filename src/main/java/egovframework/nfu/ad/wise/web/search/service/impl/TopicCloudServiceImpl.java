package egovframework.nfu.ad.wise.web.search.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.nfu.ad.wise.web.search.service.TeaVO;
import egovframework.nfu.ad.wise.web.search.service.TopicCloudService;
import egovframework.nfu.ad.wise.web.search.service.common.WNUtils;


@Service("TopicCloudService")
public class TopicCloudServiceImpl implements TopicCloudService{
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicCloudServiceImpl.class);
	/**
	 * 인기검색어 호출
	 */
	@Override
	public String topicCloud(TeaVO teaVO) throws Exception {
		int num_topics = teaVO.getNum_topics(); // 출력 갯수
		String coll_id = teaVO.getColl_id(); //컬랙션 명

		/** 검색 시작날짜 */
		String start_month = teaVO.getStart_month();
		start_month = WNUtils.checkReqXSS(start_month, WNUtils.getLastYearMonth());
		
		/** 검색 종료날짜*/
		String end_month = teaVO.getEnd_month();
		end_month = WNUtils.checkReqXSS(end_month, WNUtils.getCurrentYearMonth());
	
		String url = "";
		String param = "";
		
		url = "http://" + teaVO.getTeaIp() + ":" + teaVO.getTeaPort() + "/topic_cloud_w.tea2";
		param = "coll_id=" + coll_id + "&num_topics=" + num_topics + "&start_date=" + start_month + "&end_date=" + end_month + "&format="+teaVO.getFormat();
		
		url = url+"?"+param;
		if(teaVO.getDebugType().equals("Y")){
			LOGGER.info("[getRecommend() URL]["+url+"]");
			LOGGER.info("[getRecommend() teaVO.toString()]["+teaVO.toString()+"]");
		}
		return getHtmls(url, teaVO.getTimeOut());
	}
	
	/**
	 * TEA 연관검색어 URL 호출해야 해당 결과 담기
	 * @param receiverURL 호출 URL
	 * @param timeout 연결시간
	 * @return
	 */
	public String getHtmls(String receiverURL, int timeout){
        StringBuffer receiveMsg = new StringBuffer();
        int errorCode   = 0;
        try{
            
            // -- receive servlet connect
            URL servletUrl = new URL(receiverURL);
            HttpURLConnection uc = (HttpURLConnection)servletUrl.openConnection();
			uc.setReadTimeout(timeout);
            uc.setRequestMethod("POST");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setUseCaches(false);
            uc.connect();
            // init
            errorCode = 0;
            // -- Network error check
            if(uc.getResponseCode() == HttpURLConnection.HTTP_OK){
				String currLine = new String();
				//UTF-8인 경우
				BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
				while ((currLine = in.readLine()) != null){
					receiveMsg.append(currLine).append("\r\n");
				}
            }else{
            	LOGGER.debug("[getHtmls() URL]["+receiverURL+"]");
         	   	LOGGER.debug("[getHtmls() error][errorCode:"+errorCode+"]");
            	
            }
            uc.disconnect();
        }catch(Exception ex){
        	LOGGER.debug("[getHtmls() URL]["+receiverURL+"]");
     	   	LOGGER.debug("[getHtmls() error][errorCode:"+errorCode+"]["+ex+"]");
        }
        return receiveMsg.toString(); 
	}

	

	
}
