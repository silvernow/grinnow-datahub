package grinnow.com.main.service;

import java.io.Serializable;
import java.math.BigDecimal;

import grinnow.com.cmm.service.ComSearchVO;

public class MainVO extends ComSearchVO implements Serializable {

	private static final long serialVersionUID = -7211806599814418297L;

	/** 순번 */
	private long id;
	
	/** 수목코드 */
	private String treeCd;
	
	/** 수목명 */
	private String treeNm;
	
	/** 수목분류 */
	private String treeTy;
	
	/** 수목분류명 */
	private String treeTyNm;
	
	/** 수목단위 */
	private String treeUnit;
	
	/** 수목단위명 */
	private String treeUnitNm;
	
	 /** 수목수량 */
    private BigDecimal treeQty;
    
    /** 수목단가 */
    private BigDecimal treeUnitPrice;
    
	/** 수목가격 */
	private BigDecimal treePrice;
	
	/** 거래지역코드 */
	private String treeAddrCd;
	
	/** 거래지역코드명 */
	private String treeAddrCdNm;
	
	/** 거래일자 */
	private String basisDt;
	
	/** 기준단가코드 */
	private String unitStdCd;
	
	/** 기준단가코드명 */
	private String unitStdCdNm;
	
	/** 상차지번호 */
	private String startAreaId;
	
	/** 하차지번호 */
	private String endAreaId;
	
	/** 교목-근원직경 */
	private BigDecimal arborRootCm;
	
	/** 교목-흉고직경 */
	private BigDecimal arborBstCm;
	
	/** 교목-수고 */
	private BigDecimal arborHeiMm;
	
	/** 관목-수관폭 */
	private BigDecimal shrubWidMm;
	
	/** 관목-수고 */
	private BigDecimal shrubHeiMm;
	
	/** 묘목-근원직경 */
	private BigDecimal sapRootCm;
	
	/** 수목규격 */
	private String treeStd;
	
	/** 수목총개수 */
	private String treeTotCount;
	
	/** 상차지 */
	private String startAreaNm;
	
	/** 하차지 */
	private String endAreaNm;
	
	/** 등록일시 */
	private String creDt;
	
	/** 등록아이디 */
	private String creId;
	
	/** 그래프 가로축 */
	private String dataX;
	
	/** 그래프 세로축 */
	private String dataY;
	
	/** 신뢰도 별점표기 */
	private String relStar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTreeCd() {
		return treeCd;
	}

	public void setTreeCd(String treeCd) {
		this.treeCd = treeCd;
	}

	public String getTreeNm() {
		return treeNm;
	}

	public void setTreeNm(String treeNm) {
		this.treeNm = treeNm;
	}

	public String getTreeTy() {
		return treeTy;
	}

	public void setTreeTy(String treeTy) {
		this.treeTy = treeTy;
	}

	public String getTreeTyNm() {
		return treeTyNm;
	}

	public void setTreeTyNm(String treeTyNm) {
		this.treeTyNm = treeTyNm;
	}

	public String getTreeUnit() {
		return treeUnit;
	}

	public void setTreeUnit(String treeUnit) {
		this.treeUnit = treeUnit;
	}

	public String getTreeUnitNm() {
		return treeUnitNm;
	}

	public void setTreeUnitNm(String treeUnitNm) {
		this.treeUnitNm = treeUnitNm;
	}

	public BigDecimal getTreeQty() {
		return treeQty;
	}

	public void setTreeQty(BigDecimal treeQty) {
		this.treeQty = treeQty;
	}

	public BigDecimal getTreeUnitPrice() {
		return treeUnitPrice;
	}

	public void setTreeUnitPrice(BigDecimal treeUnitPrice) {
		this.treeUnitPrice = treeUnitPrice;
	}

	public BigDecimal getTreePrice() {
		return treePrice;
	}

	public void setTreePrice(BigDecimal treePrice) {
		this.treePrice = treePrice;
	}

	public String getTreeAddrCd() {
		return treeAddrCd;
	}

	public void setTreeAddrCd(String treeAddrCd) {
		this.treeAddrCd = treeAddrCd;
	}

	public String getTreeAddrCdNm() {
		return treeAddrCdNm;
	}

	public void setTreeAddrCdNm(String treeAddrCdNm) {
		this.treeAddrCdNm = treeAddrCdNm;
	}

	public String getBasisDt() {
		return basisDt;
	}

	public void setBasisDt(String basisDt) {
		this.basisDt = basisDt;
	}

	public String getUnitStdCd() {
		return unitStdCd;
	}

	public void setUnitStdCd(String unitStdCd) {
		this.unitStdCd = unitStdCd;
	}

	public String getUnitStdCdNm() {
		return unitStdCdNm;
	}

	public void setUnitStdCdNm(String unitStdCdNm) {
		this.unitStdCdNm = unitStdCdNm;
	}

	public String getStartAreaId() {
		return startAreaId;
	}

	public void setStartAreaId(String startAreaId) {
		this.startAreaId = startAreaId;
	}

	public String getEndAreaId() {
		return endAreaId;
	}

	public void setEndAreaId(String endAreaId) {
		this.endAreaId = endAreaId;
	}

	public BigDecimal getArborRootCm() {
		return arborRootCm;
	}

	public void setArborRootCm(BigDecimal arborRootCm) {
		this.arborRootCm = arborRootCm;
	}

	public BigDecimal getArborBstCm() {
		return arborBstCm;
	}

	public void setArborBstCm(BigDecimal arborBstCm) {
		this.arborBstCm = arborBstCm;
	}

	public BigDecimal getArborHeiMm() {
		return arborHeiMm;
	}

	public void setArborHeiMm(BigDecimal arborHeiMm) {
		this.arborHeiMm = arborHeiMm;
	}

	public BigDecimal getShrubWidMm() {
		return shrubWidMm;
	}

	public void setShrubWidMm(BigDecimal shrubWidMm) {
		this.shrubWidMm = shrubWidMm;
	}

	public BigDecimal getShrubHeiMm() {
		return shrubHeiMm;
	}

	public void setShrubHeiMm(BigDecimal shrubHeiMm) {
		this.shrubHeiMm = shrubHeiMm;
	}

	public BigDecimal getSapRootCm() {
		return sapRootCm;
	}

	public void setSapRootCm(BigDecimal sapRootCm) {
		this.sapRootCm = sapRootCm;
	}

	public String getTreeStd() {
		return treeStd;
	}

	public void setTreeStd(String treeStd) {
		this.treeStd = treeStd;
	}

	public String getTreeTotCount() {
		return treeTotCount;
	}

	public void setTreeTotCount(String treeTotCount) {
		this.treeTotCount = treeTotCount;
	}

	public String getStartAreaNm() {
		return startAreaNm;
	}

	public void setStartAreaNm(String startAreaNm) {
		this.startAreaNm = startAreaNm;
	}

	public String getEndAreaNm() {
		return endAreaNm;
	}

	public void setEndAreaNm(String endAreaNm) {
		this.endAreaNm = endAreaNm;
	}

	public String getCreDt() {
		return creDt;
	}

	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}

	public String getCreId() {
		return creId;
	}

	public void setCreId(String creId) {
		this.creId = creId;
	}

	public String getDataX() {
		return dataX;
	}

	public void setDataX(String dataX) {
		this.dataX = dataX;
	}

	public String getDataY() {
		return dataY;
	}

	public void setDataY(String dataY) {
		this.dataY = dataY;
	}

	public String getRelStar() {
		return relStar;
	}

	public void setRelStar(String relStar) {
		this.relStar = relStar;
	}
}
