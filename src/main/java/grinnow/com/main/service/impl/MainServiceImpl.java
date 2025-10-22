package grinnow.com.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import grinnow.com.main.service.MainService;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{

	@Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	@Resource(name="mainDao")
    private MainDao mainDao;

	@Override
	public int insertTreePriceList(List<Map<String, Object>> dataList) throws Exception {
		int cnt = 0;
        for (Map<String, Object> map : dataList) {
        	mainDao.insertTreePrice(map);
            cnt++;
        }
        return cnt;
	}
}

	
