package grinnow.com.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import grinnow.com.cmm.dataaccess.GnnAbstractDAO;
import grinnow.com.common.service.ComCodeVO;
import grinnow.com.common.service.ComSearchVO;

@Repository("commonDao")
public class CommonDao extends GnnAbstractDAO{

	public List<ComCodeVO> selectCommonCodeList(ComSearchVO searchVO) throws Exception{
		return selectList("commonMapper.selectCommonCodeList", searchVO);
	}
	
	public List<ComCodeVO> selectCommonAreaList() throws Exception{
		return selectList("commonMapper.selectCommonAreaList", null);
	}


}
