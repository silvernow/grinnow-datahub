package grinnow.com.common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComSearchVO implements Serializable{

	private static final long serialVersionUID = -9177102953537903206L;

	/** 검색조건 */
    private String condition;
    
    /** 검색조건 */
    private String condition2;
    
    /** 검색조건 */
    private String condition3;
    
    /** 검색Keyword */
    private String keyword;
    
    /** 검색Keyword2 */
    private String keyword2;
    
    /** 검색Keyword3 */
    private String keyword3;
    
    /** 팝업검색Keyword */
    private String pKeyword;
    
    /** 검색DateFrom */
    private String dateFrom;    

	/** 검색DateTo */
    private String dateTo;
    
    /** 검색Date */
    private String date;
    
    /** 검색타입 */
    private String type;
    
    /** 검색모드 */
    private String mode;
    
	/** 검색조건 */
    private String kind;
    
    /** 팝업검색조건 */
    private String pKind;
    
    /** 현재페이지(jqgird 선언변수) */
    private int page = 1;
    
    /** 시작번호(PaginationInfo) */
    private int firstIndex = 1;

    /** 종료번호(PaginationInfo) */
    private int lastIndex = 1;

    /** 현재 페이지 번호 */
	private int currentPage = 1;
	
    /** 한 페이지에 게시되는 게시물 건수 */
    private int recordCountPerPage = 20;
    
    /** (팝업)한 페이지에 게시되는 게시물 건수 */
    private int pRecordCountPerPage = 20;
    
    /** 정렬옵션 */
	private String sidx;
	
	/** 정렬실행 */
	private String sord;
	
	/** 메인유무 */
    private String mainYn;
    
    /** 사용자아이디 */
    private String userId;
	
    /** List형파라메터 */
    private List<String> searchList = new ArrayList<String>();

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}

	public String getpKeyword() {
		return pKeyword;
	}

	public void setpKeyword(String pKeyword) {
		this.pKeyword = pKeyword;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getpKind() {
		return pKind;
	}

	public void setpKind(String pKind) {
		this.pKind = pKind;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getpRecordCountPerPage() {
		return pRecordCountPerPage;
	}

	public void setpRecordCountPerPage(int pRecordCountPerPage) {
		this.pRecordCountPerPage = pRecordCountPerPage;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getMainYn() {
		return mainYn;
	}

	public void setMainYn(String mainYn) {
		this.mainYn = mainYn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<String> searchList) {
		this.searchList = searchList;
	}
}
