package egovframework.nfu.ad.wise.web.search.service;

public class ArkVO {
	/** 키워드  */
	private String query = "";
	/** 자동완성 매칭방식 */
	private String convert = "fw";
	/** 자동완성 타겟 */
	private String target = "common";
	/** 케릭터 셋 */
	private String charset = "UTF-8";
	/** 데이타 타입 */
	private String dataType = "json";
	/** 디버그 여부 */
	private String debugType = "N";
	/** 연결 시간 */
	private Integer timeOut = 1000;
	
	public String getQuery() {
		return this.query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getConvert() {
		return convert;
	}
	public void setConvert(String convert) {
		this.convert = convert;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDebugType() {
		return debugType;
	}
	public void setDebugType(String debugType) {
		this.debugType = debugType;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}
	@Override
	public String toString() {
		return "ArkVO [query=" + getQuery() + ", convert=" + getConvert() + ", target="
				+ getTarget() + ", charset=" + getCharset() + ", dataType=" + getDataType()
				+ ", debugType=" + getDebugType() + ", timeOut=" + getTimeOut() + "]";
	}
	
	
	
	
	
}
