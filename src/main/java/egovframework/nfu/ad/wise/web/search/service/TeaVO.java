package egovframework.nfu.ad.wise.web.search.service;

public class TeaVO {
	
	/** 검색어 */
	String query = "" ;
	/** TEA IP */
	private String teaIp = "49.50.166.167";
	/** TEA PORT */
	private Integer teaPort = 11200;
	/** 디버그메시지 */
	private String debugType ="N";
	/** 타임 아웃 */
	private Integer timeOut = 1000;
	/** 서버 ID */
	private String server_id="server01";
	/** 연관검색어 출력값 */
	private String num_relation_keyword="10";
	/** format */
	private String format="json";
	/** 구분자 */
	private String delimiter="^";
	/** encoding */
	private String encoding="utf8";
	/** 컬렉셩 ID */
	private String coll_id="";
	/** 가져올 주제어 갯수 */
	private Integer num_topics=30;
	/** 분석 시작 월 */
	private String start_month="";
	/** 분석 종료 월 */
	private String end_month="";
	
	
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getTeaIp() {
		return teaIp;
	}
	public void setTeaIp(String teaIp) {
		this.teaIp = teaIp;
	}
	public Integer getTeaPort() {
		return teaPort;
	}
	public void setTeaPort(Integer teaPort) {
		this.teaPort = teaPort;
	}
	public String getDebugType() {
		return debugType;
	}
	public void setDebugType(String debugType) {
		this.debugType = debugType;
	}
	public Integer getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}
	public String getServer_id() {
		return server_id;
	}
	public void setServer_id(String server_id) {
		this.server_id = server_id;
	}
	public String getNum_relation_keyword() {
		return num_relation_keyword;
	}
	public void setNum_relation_keyword(String num_relation_keyword) {
		this.num_relation_keyword = num_relation_keyword;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getColl_id() {
		return coll_id;
	}
	public void setColl_id(String coll_id) {
		this.coll_id = coll_id;
	}
	public Integer getNum_topics() {
		return num_topics;
	}
	public void setNum_topics(Integer num_topics) {
		this.num_topics = num_topics;
	}
	public String getStart_month() {
		return start_month;
	}
	public void setStart_month(String start_month) {
		this.start_month = start_month;
	}
	public String getEnd_month() {
		return end_month;
	}
	public void setEnd_month(String end_month) {
		this.end_month = end_month;
	}
	

}
