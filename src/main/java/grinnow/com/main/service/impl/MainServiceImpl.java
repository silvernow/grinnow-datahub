package grinnow.com.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import grinnow.com.cmm.service.Globals;
import grinnow.com.common.amazon.MongoDbClient;
import grinnow.com.main.service.MainService;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "mongoDbClient")
    private MongoDbClient mongoDbClient;
	  
	@Resource(name="mainDao")
    private MainDao mainDao;
	
	@Override
	public int insertTreePriceList(List<Map<String, Object>> postDataList, List<Map<String, Object>> mongoDataList) throws Exception {
		int cnt = 0;
        for (Map<String, Object> map : postDataList) {
        	mainDao.insertTreePrice(map);
            cnt++;
        }
        
        if(Globals.MONGO_USE_YN.equals("Y")) {
        	for (Map<String, Object> map : mongoDataList) {
        		try {
        			mongoDbClient.saveTreePrice(map);
        		}catch (Exception e) {
        			LOGGER.error("[insertTreePriceList] Insert failed for {}: {}", map, e.getMessage(), e);
        		}
            }
        	LOGGER.info("[insertTreePriceList] {} rows processed successfully.", cnt);
        }
        return cnt;
	}
}
	
