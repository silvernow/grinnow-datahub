package grinnow.com.common.service;

import java.util.List;

public interface CommonService {

	public List<ComCodeVO> selectCommonCodeList(String codeId, String detailCondition) throws Exception;

	public List<ComCodeVO> selectCommonAreaList() throws Exception;
	
}
