package egovframework.nfu.ad.wise.web.search.service.impl;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.nfu.ad.wise.web.search.service.ArkService;
import egovframework.nfu.ad.wise.web.search.service.ArkVO;
import egovframework.nfu.ad.wise.web.search.service.Sf1VO;

@Service("ArkService")
public class ArkServiceImpl implements ArkService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ArkServiceImpl.class);
	
	/**
	 *  자동완성 호출
	 */
	@Override
	public String getArk(ArkVO arkVo, Sf1VO sf1Vo) throws Exception {
		StringBuffer receiverURL = new StringBuffer();		
		receiverURL.append("http://");
		receiverURL.append(sf1Vo.getManagerIp()).append(":");
		receiverURL.append(sf1Vo.getManagerPort());
		receiverURL.append("/manager/WNRun.do");
		
		String receiverURLStr = receiverURL.toString();
		
		//LOGGER.debug(receiverURLStr);
		
		StringBuffer parameter = new StringBuffer();
		parameter.append("query=").append(arkVo.getQuery()); 
		parameter.append("&convert=").append(arkVo.getConvert());
		parameter.append("&target=").append(arkVo.getTarget());
		parameter.append("&charset=").append(arkVo.getCharset());
		parameter.append("&datatype=").append(arkVo.getDataType());
		
		String parameterStr = parameter.toString();
		if(arkVo.getDebugType().equals("Y")){
			LOGGER.info("[getArk() URL]["+receiverURL+"?"+parameter+"]");
			LOGGER.info("[getArk() sf1Vo.toString()]["+sf1Vo.toString()+"]");
			LOGGER.info("[getArk() arkVo.toString()]["+arkVo.toString()+"]");
		}
		return getHtmls(receiverURLStr, parameterStr, arkVo.getTimeOut() , arkVo.getDataType(), arkVo.getCharset());
	}
	
	/**
	 * 관리도구 자동완성 URL 호출해야 해당 결과 담기
	 * @param receiverURL 관리도구 URL 정보
	 * @param parameter ark 파라미터값
	 * @param timeout ark 연결 시간 고정으로 1000 
	 * @param datatype 관리도구 web화면 데이타 타입
	 * @param charset 케릭터 타임
	 * @return dataType 에 따른 자동완성 결과
	 */
	private String getHtmls(String receiverURL, String parameter, int timeout, String datatype , String charset) {
		StringBuffer receiveMsg = new StringBuffer();
		HttpURLConnection uc = null;
		int errorCode = 0;
		try {
			URL servletUrl = new URL(receiverURL);
			uc = (HttpURLConnection) servletUrl.openConnection();
			uc.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setUseCaches(false);
			uc.setDefaultUseCaches(false);
			uc.setConnectTimeout(timeout);
			uc.setReadTimeout(timeout);
			DataOutputStream dos = new DataOutputStream (uc.getOutputStream());
			dos.write(parameter.getBytes());
			dos.flush();
			dos.close();
			
			
			// -- Network error check
			//System.out.println("[URLConnection Response Code] " + uc.getResponseCode());
			if (uc.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String currLine = "";
                // UTF-8. ..
                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
                while ((currLine = in.readLine()) != null) {
                	receiveMsg.append(currLine).append("\r\n");
                }
                in.close();
            } else {
                  errorCode = uc.getResponseCode();
                  return receiveMsg.toString();
             }
       } catch(Exception ex) {
    	   receiveMsg.setLength(0);
    	   if(datatype.toLowerCase().equals("xml")){
    		   receiveMsg.append("<?xml version=\"1.0\" encoding=\""+charset+"\" ?>");
    		   receiveMsg.append("<Response>");
    		   receiveMsg.append("<Value>");
    		   receiveMsg.append("<Return>0</Return>");
    		   receiveMsg.append(" <ARKList>");
    		   receiveMsg.append("<TotalCount>0</TotalCount>");
    		   receiveMsg.append("</ARKList>");
    		   receiveMsg.append("<ARKRList>");
    		   receiveMsg.append(" <TotalCount>0</TotalCount>");
    		   receiveMsg.append("</ARKRList>");
    		   receiveMsg.append("</Value>");
    		   receiveMsg.append("</Response>");
    	   }else if(datatype.toLowerCase().equals("json")){
    		   receiveMsg.append("{\"responsestatus\":0,\"result\":[{\"totalcount\":0},{\"totalcount\":0}]}");
    	   }
    	   LOGGER.debug("[getHtmls() URL]["+receiverURL+"?"+parameter+"]");
    	   LOGGER.debug("[getHtmls() error][errorCode:"+errorCode+"]["+ex+"]");
       } finally {
            uc.disconnect();
       }
       return receiveMsg.toString();
	}

	
}
