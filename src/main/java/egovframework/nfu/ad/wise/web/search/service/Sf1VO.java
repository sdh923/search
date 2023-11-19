package egovframework.nfu.ad.wise.web.search.service;

public class Sf1VO {
	/** 검색기 IP */
	private String searchIp = "49.50.166.167";
	/** 검색기 PORT*/
	private Integer searchPort = 7000;
	/** 검색 타임아웃 */
	private Integer searchTimeOut = 5000;
	/** */
	private String searchDebugType = "N";
	/** 관리도구 IP */
	private String managerIp = "49.50.166.167";
	/** 관리도구 PORT */
	private Integer managerPort = 7800;
	public String getSearchIp() {
		return searchIp;
	}
	public void setSearchIp(String searchIp) {
		this.searchIp = searchIp;
	}
	public Integer getSearchPort() {
		return searchPort;
	}
	public void setSearchPort(Integer searchPort) {
		this.searchPort = searchPort;
	}
	public Integer getSearchTimeOut() {
		return searchTimeOut;
	}
	public void setSearchTimeOut(Integer searchTimeOut) {
		this.searchTimeOut = searchTimeOut;
	}
	public String getSearchDebugType() {
		return searchDebugType;
	}
	public void setSearchDebugType(String searchDebugType) {
		this.searchDebugType = searchDebugType;
	}
	public String getManagerIp() {
		return managerIp;
	}
	public void setManagerIp(String managerIp) {
		this.managerIp = managerIp;
	}
	public Integer getManagerPort() {
		return managerPort;
	}
	public void setManagerPort(Integer managerPort) {
		this.managerPort = managerPort;
	}
	@Override
	public String toString() {
		return "Sf1VO [searchIp=" + getSearchIp() + ", searchPort=" + getSearchPort()
				+ ", searchTimeOut=" + getSearchTimeOut() + ", searchDebugType="
				+ getSearchDebugType() + ", managerIp=" + getManagerIp()
				+ ", managerPort=" + getManagerPort() + "]";
	}
	
	
	

}
