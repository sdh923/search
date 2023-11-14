package com.klic.search.service.common;


import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WNUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(WNUtils.class);
	final static String ENCODE_ORI = "EUC-KR";
	final static String ENCODE_NEW = "UTF-8";
	/**
     * 문자 배열 값을 검색하여 키 값을 리턴
     * @param fieldName
     * @param value
     * @param operation
     * @return
     */
    public static int findArrayValue(String find, String[] arr) {
        int findKey = -1;
        for (int i = 0; i < arr.length; i++) {
            if (find.equals(arr[i])){
                findKey = i;
                break;
            }
        }
        return findKey;
    }

    /**
     *
     * @param s
     * @param findStr
     * @param replaceStr
     * @return
     */
    public static String replace(String s, String findStr, String replaceStr){
        int   pos;
        int   index = 0;

        while ((pos = s.indexOf(findStr, index)) >= 0) {
            s = s.substring(0, pos) + replaceStr + s.substring(pos + findStr.length());
            index = pos + replaceStr.length();
        }

        return s;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String trimDuplecateSpace(String s){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(i < s.length()-1) {
                if( c == ' ' && s.charAt(i+1)==' '){
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString().trim();
    }

    public static String parseDate(String input, String inFormat, String outFormat) {
        String retStr = "";
        Date date = null;
        SimpleDateFormat formatter = null;
        try {
            date = (new SimpleDateFormat(inFormat)).parse(input.trim());
        } catch (ParseException e) {
            LOGGER.info("ParseException occurred");
			//e.printStackTrace();
        }
        formatter = new SimpleDateFormat(outFormat);
        retStr = formatter.format(date);
        return retStr;
    }

    public static String getCurrentDate() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat
                ("yyyy/MM/dd", java.util.Locale.KOREA);
        return dateFormat.format(new java.util.Date());
    }

    /**
     *
     * @param strNum
     * @param def
     * @return
     */
    public static int parseInt(String strNum, int def){
        if(strNum == null) return def;
        try{
            return Integer.parseInt(strNum);
        }catch(Exception e){
            return def;
        }
    }

    /**
     * String의 값이 null일 경우 ""로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static String checkNull(String temp) {
        if (temp != null) {
            temp = temp.trim();
        } else {
            temp = "";
        }
        return temp;
    }
    
    /**
     * int 의 값이 없을경우 일 경우 0로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static int checkNull(int temp) {
    	int number = 0;
    	if(temp != 0){
    		number =  temp;
    	}
        return number;
    }
    
    /**
     * ArrayList<T> 의 값이 null일 경우 List<T>  로 변환하여 리턴한다.
     * @param <T>
     * @param <T>
     * @param temp
     * @return 
     * @return
     */
    public static <T> ArrayList<T> checkNull(List<? extends T> temp)  {
    	ArrayList<T> list = new ArrayList<T>();
    	if (temp != null && !temp.isEmpty()) {
    		list.addAll(temp);
    	}
    	return list;
    }

    /**
     * 1차원 배열의 값중 null인 값을 ""로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static String[] checkNull(String[] temp){
        for(int i=0; i<temp.length; i++) {
            temp[i] = checkNull(temp[i]);
        }
        return temp;
    }

    /**
     * 2차원 배열의 값중 null인 값을 ""로 변환하여 리턴한다.
     * @param temp
     * @return
     */
    public static String[][] checkNull(String[][] temp) {
        for(int i=0; i<temp.length; i++) {
            temp[i][0] = checkNull(temp[i][0]);
            temp[i][1] = checkNull(temp[i][1]);
        }
        return temp;
    }

    /**
     * 스트링을 format 에 맞게 변환을 한다.
     * convertFormat("1", "00") return "01" 로 입력 값을 리턴한다.
     * @param inputStr
     * @param format
     * @return String
     */
    public static String convertFormat(String inputStr, String format){
        int input = Integer.parseInt(inputStr);
        StringBuffer result = new StringBuffer();
        DecimalFormat df = new DecimalFormat(format);
        df.format( input, result, new FieldPosition(1) );
        return result.toString();
    }

    /**
     *
     * @param str
     * @param outFormat
     * @return
     */
    public static String numberFormat(String str, String outFormat) {
        return new DecimalFormat(outFormat).format(str);
    }

	/**
     *
     * @param str
     * @return
     */
    public static String numberFormat(int num) {
		return NumberFormat.getNumberInstance().format(num);

    }

    /**
     *
     * @param str
     * @param oriEncode
     * @param newEncode
     * @return
     */
    public static String encoding(String str, String oriEncode, String newEncode) {
        str = checkNull(str);
        if(str.length() > 0) {
            try {
                str = new String(str.getBytes(oriEncode), newEncode);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                LOGGER.info("UnsupportedEncodingException occurred");
            	//e.printStackTrace();
            }
        }
        return str;
    }


    /**
     * 구분자를 가지고 있는 문자열을 구분자를 기준으로 나누어주는 메소드
     * @param splittee 구분자를 가진 문자열
     * @param splitChar 구분자
     * @return
     */
    public static String[] split(String splittee, String splitChar){
        String taRetVal[] = null;
        StringTokenizer toTokenizer = null;
        int tnTokenCnt = 0;

        try {
            toTokenizer = new StringTokenizer(splittee, splitChar);
            tnTokenCnt = toTokenizer.countTokens();
            taRetVal = new String[tnTokenCnt];

            for(int i=0; i<tnTokenCnt; i++) {
                if(toTokenizer.hasMoreTokens())	taRetVal[i] = toTokenizer.nextToken();
            }
        } catch (Exception e) {
            taRetVal = new String[0];
        }
        return taRetVal ;
    }

    /**
     * String 을 받아 UTF-8 범위내 문자가 이닌경우 공백(0x0020) 으로 치환
     * @param str
     * @return String
     */
    public static String validate(String str) {
        StringBuffer buf = new StringBuffer();

        char ch;
        for(int i=0; i < str.length(); i++) {
            ch = str.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
            } else {
                if(Character.isWhitespace(ch)) {
                } else {
                    if(Character.isISOControl(ch)) {
                        // UTF-8 에서 지원하지 않는 문자 제거
                        ch = (char)0x0020;
                    }
                }
            }

            buf.append(ch);
        }

        return buf.toString();
    }

	public static String replaceURL(String base, String url, String param, String value) {

		String sURL = "";
		if ( url != null && !url.equals("")) {
			if ( url.indexOf(param) < 0 )
				url = url + "&" + param + "=" + value;

			String [] params = url.split("&");
			for ( int idx=0; idx < params.length; idx++ ) {
				if ( params[idx].indexOf(param) >= 0 ) {
					params[idx] = param + "=" + value;
				}

				sURL = sURL + params[idx] ;

				if ( idx + 1 < params.length)
					sURL = sURL + "&" ;

			}

		} else {
				sURL = param + "=" + value;
		}

		sURL = base + "?" + sURL;


		return sURL;

	}

    /**
     * null체크
     **/
    public String nvl(String parameter, String defaultValue) {
        String reqValue = parameter !=null ? parameter:defaultValue;
        return reqValue;
    }

	/**
	 * 문자의 길이를 지정된 길이만큼 잘라서 반환하는 함수<br>
	 * 한글과 영문,숫자에 따라서 길이를 다르게 계산한다.
	 * @param str 변환할 문자열<br>
	 * byteLength 문자열의 길이
	 * @return rtStr 지정된 길이로 수정된 문자열을 반환한다.
	 */
	public static String getSubString(String str, int byteLength) {
		if (str==null) {
			return "";
		}

		StringBuffer rtStr = new StringBuffer();

		rtStr.append(str.substring(0,getLengthInString(str,byteLength)));

		if(rtStr.length() != str.length()) {
			rtStr.append("...");
		}

		return rtStr.toString();
	}


	/**
	 * 문자의 길이를 한글 2자 영문,숫자를 1자로 계산하여<br>
	 * 문자열의 길이를 반환하는 함수
	 * @param str 변환할 문자열<br>
	 * byteLength 문자열의 길이
	 * @return int 문자열의 길이
	 */
	public static int getLengthInString(String str, int byteLength)
	{
		int length = str.length();
		int retLength = 0;
		int tempSize = 0;
		int asc;

		for(int i = 1; i<=length; i++) {
			asc = (int)str.charAt(i-1);
			
			if(asc > 127) {
				if ( byteLength > tempSize ) {
					tempSize += 2;
					retLength++;
				}
			} else {
				if ( byteLength > tempSize ) {
					tempSize++;
					retLength++;
				}
			}
		}

		return retLength;
	}
	/**
     * json 데이타 제외 특수문자
     **/
    public static String getJSonFilter(String data) {
        String ret = data;
        data = data.replaceAll(">"," ");
        data = data.replaceAll("<"," ");
        data = data.replaceAll("&"," ");
        data = data.replaceAll("\r"," ");
        data = data.replaceAll("\t"," ");
        data = data.replaceAll("\n"," ");
        data = data.replaceAll("\""," ");
        data = data.replaceAll("\\\\"," ");
        return data;
    }
    
    /**
     * request XSS 처리
     **/
    public static String checkReqXSS( String value, String defaultValue) {
        String reqValue =  (value == null ||  value.equals("")) ? defaultValue : value;
        //reqValue = reqValue.replaceAll("</?[a-zA-Z][0-9a-zA-Z가-\uD7A3ㄱ-ㅎ=/\"\'%;:,._()\\-# ]+>","");
        reqValue = reqValue.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        reqValue = reqValue.replaceAll("<","");
        reqValue = reqValue.replaceAll(">","");
		//금지 문자열 리스트
        String blockchar[] = {"./", "..", "../", "..\\"};
		
		// 금지할 문자열 포함 여부 체크
        for(int i=0; i<blockchar.length;i++) {
	        if( reqValue.indexOf(blockchar[i]) != -1 ){
	        	reqValue = "";
	        }
        }
		
        return reqValue;
    }
    
    public static String getThemeUrl(String htmlString){
    	Pattern pattern = Pattern.compile("(?i)<a[^>]*href=[\"']?([^>\"']+)[\"']?[^>]*>"); //a 태그 href 추출 정규표현식
    	Matcher matcher = pattern.matcher(htmlString);
    	
    	String themeUrl = "";	
    	while(matcher.find()){
    		if("".equals(themeUrl)){
    			themeUrl = matcher.group(1);	//href 값만 추출
    		}
    	}
    	return themeUrl;
    }
    
    public static String getImgUrl(String htmlString){
    	Pattern pattern = Pattern.compile("(?i)<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
    	Matcher matcher = pattern.matcher(htmlString);
    	
    	String imgUrl = "";	
    	while(matcher.find()){
    		imgUrl = matcher.group();
    	}
    	return imgUrl;
    }

}
