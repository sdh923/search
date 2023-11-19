package egovframework.nfu.ad.wise.web.search.service;

public class PopWordVO {
	/** 인기검색어 타겟 popword 고정 참조 */
	private String target = "popword";
	/** 인코딩 셋  */
	private String charset = "UTF-8";
	/** 인기검색어 조회 범위설정 */
	private String range = "W";
	/** collection 통계라벨ID */
	private String collection ="_ALL_";
	/** 데이타 타입 */
	private String dataType = "json";
	/** 디버그메시지 */
	private String debugType ="N";
	/** 타임 아웃 */
	private Integer timeOut = 1000;
	
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
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
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
		return "PopWordVO [target=" + getTarget() + ", charset=" + getCharset()
				+ ", range=" + getRange() + ", collection=" + getCollection()
				+ ", dataType=" + getDataType() + ", debugType=" + getDebugType()
				+ ", timeOut=" + getTimeOut() + "]";
	}
	
	
}
