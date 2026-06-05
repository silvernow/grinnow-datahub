package grinnow.com.cmm.dataaccess;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

public abstract class GnnAbstractDAO extends EgovAbstractMapper{

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="grinnow.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}
	
	@Override
	public int insert(String queryId) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().insert(queryId);
	}

	@Override
	public int insert(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().insert(queryId, parameterObject);
	}

	@Override
	public int update(String queryId) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().update(queryId);
	}

	@Override
	public int update(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().update(queryId, parameterObject);
	}

	@Override
	public int delete(String queryId) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().delete(queryId);
	}

	@Override
	public int delete(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().delete(queryId, parameterObject);
	}

	@Deprecated
	public Object selectByPk(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectOne(queryId, parameterObject);
	}

	@Override
	public <T> T selectOne(String queryId) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectOne(queryId);
	}

	@Override
	public <T> T selectOne(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectOne(queryId, parameterObject);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String queryId, String mapKey) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectMap(queryId, mapKey);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String mapKey) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectMap(queryId, parameterObject, mapKey);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String mapKey, RowBounds rowBounds) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectMap(queryId, parameterObject, mapKey, rowBounds);
	}

	@Deprecated
	public List<?> list(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectList(queryId, parameterObject);
	}

	@Override
	public <E> List<E> selectList(String queryId) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectList(queryId);
	}

	@Override
	public <E> List<E> selectList(String queryId, Object parameterObject) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectList(queryId, parameterObject);
	}

	@Override
	public <E> List<E> selectList(String queryId, Object parameterObject, RowBounds rowBounds) {
		LOGGER.debug("queryId = "+queryId);
		return getSqlSession().selectList(queryId, parameterObject, rowBounds);
	}

	@Override
	public List<?> listWithPaging(String queryId, Object parameterObject, int pageIndex, int pageSize) {
		LOGGER.debug("queryId = "+queryId);
		int skipResults = pageIndex * pageSize;
		RowBounds rowBounds = new RowBounds(skipResults, pageSize);

		return getSqlSession().selectList(queryId, parameterObject, rowBounds);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void listToOutUsingResultHandler(String queryId, ResultHandler handler) {
		LOGGER.debug("queryId = "+queryId);
		getSqlSession().select(queryId, handler);
	}
}
