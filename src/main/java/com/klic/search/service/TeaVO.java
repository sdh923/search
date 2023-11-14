package com.klic.search.service;

public class TeaVO {
	
	/** 검색어 */
	String query = "" ;
	/** TEA IP */
	private String teaIp = "49.50.166.167";
	/** TEA PORT */
	private Integer teaPort = 11000;
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
	

}
