package grinnow.com.cmm.service;

import java.io.Serializable;

public class ComSearchVO implements Serializable{

	private static final long serialVersionUID = -9177102953537903206L;

	/** 현재 페이지 번호 */
	private int page = 1;
	
	/** 한 페이지당 가져올 데이터 개수 (limit) */
	private int limit = 20;
	
	/** 조회 시작 위치 (offset) */
	private int offset = 0;

	/** 검색조건1 */
	private String condition;
	
	/** 검색조건2 */
	private String condition2;
	
	/** 검색키워드 */
    private String keyword;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
