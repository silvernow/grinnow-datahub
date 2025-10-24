package grinnow.com.main.service;

import java.util.List;
import java.util.Map;

public interface MainService { 

	public int insertTreePriceList(List<Map<String, Object>> postDataList, List<Map<String, Object>> mongoDataList) throws Exception;

}
