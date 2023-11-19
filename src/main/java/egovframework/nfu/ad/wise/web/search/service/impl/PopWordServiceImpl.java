package egovframework.nfu.ad.wise.web.search.service.impl;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.nfu.ad.wise.web.search.service.ArkService;
import egovframework.nfu.ad.wise.web.search.service.PopWordService;
import egovframework.nfu.ad.wise.web.search.service.PopWordVO;
import egovframework.nfu.ad.wise.web.search.service.Sf1VO;

@Service("PopwordService")
public class PopWordServiceImpl implements PopWordService{
	private static final Logger LOGGER = LoggerFactory.getLogger(PopWordServiceImpl.class);
	/**
	 * 인기검색어 호출
	 */
	@Override
	public String getPopWord(PopWordVO popWordVo, Sf1VO sf1Vo) throws Exception {
		StringBuffer urlSb = new StringBuffer();		
		urlSb.append("http://");
		urlSb.append(sf1Vo.getManagerIp()).append(":");
		urlSb.append(sf1Vo.getManagerPort());
		urlSb.append("/manager/WNRun.do?");
		
		urlSb.append("target=").append(popWordVo.getTarget()); 
		urlSb.append("&charset=").append(popWordVo.getCharset());
		urlSb.append("&range=").append(popWordVo.getRange());
		urlSb.append("&collection=").append(popWordVo.getCollection());
		urlSb.append("&datatype=").append(popWordVo.getDataType());
		
		String url = urlSb.toString();
		if(popWordVo.getDebugType().equals("Y")){
			LOGGER.info("[getPopWord() URL]["+url+"]");
			LOGGER.info("[getPopWord() sf1Vo.toString()]["+sf1Vo.toString()+"]");
			LOGGER.info("[getPopWord() popWordVo.toString()]["+popWordVo.toString()+"]");
		}
		return getHtmls(url, popWordVo.getTimeOut() , popWordVo.getRange() , popWordVo.getDataType() , popWordVo.getCharset());
	}
	
	/**
	 * 관리도구 자동완성 URL 호출해야 해당 결과 담기
	 * @param receiverURL 호출 URL
	 * @param timeout 연결시간
	 * @param range 인기검색어 조회 범위설정[D:하루 ,W:주 ,M:월]
	 * @param datatype 데이타 타입  [xml or json]
	 * @param charset 인코딩 셋  [euc-kr or UTF-8]
	 * @return
	 */
	public String getHtmls(String receiverURL, int timeout , String range , String datatype , String charset){
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
				//BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				while ((currLine = in.readLine()) != null){
					receiveMsg.append(currLine).append("\r\n");
				}
            }else{
            	/*
                errorCode = uc.getResponseCode();
				return receiveMsg.toString();
				*/
            	errorCode = uc.getResponseCode();
            	receiveMsg.setLength(0);
            	if(datatype.toLowerCase().equals("xml")){
            		receiveMsg.append("<?xml version=\"1.0\" encoding=\""+charset+"\"?>").append("\r\n");
            		receiveMsg.append("<Data>").append("\r\n");
            		receiveMsg.append("<MakeTime>error:"+errorCode+"</MakeTime>").append("\r\n");
            		receiveMsg.append("<Label id=\""+range+"\"></Label>").append("\r\n");
            		receiveMsg.append("<Type id=\""+range+"\"></Type>").append("\r\n");
            		receiveMsg.append("</Data>").append("\r\n");
            	}else if(datatype.toLowerCase().equals("json")){
            		receiveMsg.append("{\"Data\":{\"MakeTime\":\"error\",\"Query\":[{}],\"Type\":{\"content\":\""+errorCode+"\",\"id\":\""+range+"\"},\"Label\":{\"content\":\""+errorCode+"\",\"id\":\""+range+"\"}}}");
            	}
            }
            uc.disconnect();
        }catch(Exception ex){
        	receiveMsg.setLength(0);
        	if(datatype.toLowerCase().equals("xml")){
        		receiveMsg.append("<?xml version=\"1.0\" encoding=\""+charset+"\"?>").append("\r\n");
        		receiveMsg.append("<Data>").append("\r\n");
        		receiveMsg.append("<MakeTime>error</MakeTime>").append("\r\n");
        		receiveMsg.append("<Label id=\""+range+"\">"+ex+"</Label>").append("\r\n");
        		receiveMsg.append("<Type id=\""+range+"\"></Type>").append("\r\n");
        		receiveMsg.append("</Data>").append("\r\n");
        	}else if(datatype.toLowerCase().equals("json")){
        		receiveMsg.append("{\"Data\":{\"MakeTime\":\"error\",\"Query\":[{}],\"Type\":{\"content\":\""+ex+"\",\"id\":\""+range+"\"},\"Label\":{\"content\":\""+ex+"\",\"id\":\""+range+"\"}}}");
        	}
        	LOGGER.debug("[getHtmls() URL]["+receiverURL+"]");
     	   	LOGGER.debug("[getHtmls() error][errorCode:"+errorCode+"]["+ex+"]");
        }
        return receiveMsg.toString(); 
	}

	

	
}
