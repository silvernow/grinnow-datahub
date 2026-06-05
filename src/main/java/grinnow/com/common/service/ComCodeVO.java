package grinnow.com.common.service;

import java.io.Serializable;

public class ComCodeVO extends ComSearchVO implements Serializable{

	private static final long serialVersionUID = -1763744074180889438L;

	/** 코드아이디 */
	private String codeId;
	
	/** 코드 */
	private String code;
	
	/** 코드명 */
	private String codeNm;
	
	/** 코드상세설명 */
	private String codeDc;
	
	/** 정렬순서 */
	private int sortOrder;
	
	/** 사용여부 */
	private String useYn;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getCodeDc() {
		return codeDc;
	}

	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
