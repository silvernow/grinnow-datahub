package grinnow.com.main.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import grinnow.com.cmm.dataaccess.GnnAbstractDAO;

@Repository("mainDao")
public class MainDao extends GnnAbstractDAO{

	public void insertTreePrice(Map<String, Object> map) throws Exception{
		insert("mainMapper.insertTreePrice", map);
	}
}

