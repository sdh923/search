package com.klic.search.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.klic.search.service.RecommendService;
import com.klic.search.service.TeaVO;

@Service("RecommendService")
public class RecommendServiceImpl implements RecommendService{
	private static final Logger LOGGER = LoggerFactory.getLogger(RecommendServiceImpl.class);
	/**
	 * 인기검색어 호출
	 */
	@Override
	public String getRecommend(TeaVO teaVO) throws Exception {
		String query = teaVO.getQuery() != null ? URLEncoder.encode(teaVO.getQuery(), "UTF-8") : "";
		String server_id = teaVO.getServer_id(); //server01
		String num = teaVO.getNum_relation_keyword(); // 출력 갯수
	
		String url = "";
		String param = "";
		
		url = "http://" + teaVO.getTeaIp() + ":" + teaVO.getTeaPort() + "/relation_keyword_w.tea2";
		param = "server_id=" + teaVO.getServer_id() + "&keyword=" + query + "&num_relation_keyword=" + num + "&format=json&delimiter=^&encoding=utf8";
		
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
