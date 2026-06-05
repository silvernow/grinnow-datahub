package grinnow.com.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import grinnow.com.cmm.exception.ErrorCode;
import grinnow.com.cmm.exception.ProcessingException;
import grinnow.com.common.service.ComCodeVO;
import grinnow.com.common.service.ComSearchVO;
import grinnow.com.common.service.CommonService;
import grinnow.com.common.util.CommonUtil;

@Service("commonService")
public class CommonServiceImpl extends EgovAbstractServiceImpl implements CommonService{
	
	@Resource(name="commonDao")
    private CommonDao commonDao;
	
	@Override
	public List<ComCodeVO> selectCommonCodeList(String codeId, String detailCondition) throws Exception {
		ComSearchVO searchVO = new ComSearchVO();
		List<ComCodeVO> list = new ArrayList<ComCodeVO>();
		
		try {
			searchVO.setCondition(CommonUtil.isNull(codeId));
			searchVO.setCondition2(CommonUtil.isNull(detailCondition));
			list = commonDao.selectCommonCodeList(searchVO);
		} catch (Exception e) {
   			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
   		}
		return list;
	}

	@Override
	public List<ComCodeVO> selectCommonAreaList() throws Exception {
		return commonDao.selectCommonAreaList();
	}

}
