package com.vic.university.respository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

//import com.asiainfo.easyconfig.model.Paginator;

public interface BaseRepository<T> {
	public T findOne(Long id);
	
	public T findOne(Integer id);
	
	public T findOne(DetachedCriteria detachedCriteria);
	
	public List<?> statistics(DetachedCriteria detachedCriteria);
	
	public Integer count();
	
	public Integer count(DetachedCriteria detachedCriteria);
	
	public BigDecimal avg(DetachedCriteria detachedCriteria);
	
	public List<T> findAll();
	
	public List<T> findAll(int page, int fetchSize);
	
	public List<T> findAll(Collection<Long> ids);
	
	public List<T> findAll(DetachedCriteria criteria);
	
	public List<T> findAll(DetachedCriteria detachedCriteria, int page, int pageSize);
	
	public List<T> findAll(DetachedCriteria detachedCriteria, int page, int pageSize,int totalSize);
	
//	public Paginator<T> findPage(DetachedCriteria detachedCriteria, int page, int pageSize);
	
	public List<T> findAll(String hql, int page, int fetchSize, ResultTransformer transformer, Object...params);
	
	public List<T> findAll(String hql, ResultTransformer transformer, Object...params);
	
	public List<T> findAll(String hql, Object...params);
	
	public T save(T entry); 
	
	public Collection<T> batchSave(Collection<T> entity);

	public T delete(T entity);

	public List<T> deleteAll(List<T> list);
	
	public int executeHql(String hql, Object... params);

	public int executeHql(String hql, String[] paramNames, Object...params);
	
	public int executeUpdateSql(String hql, Object... params);
	
	public int executeUpdateSql(String hql, String[] paramNames, Object...params);
	
	public List<Object> executeSql(String hql, Object... params);
	
	public List<Object> executeSqlPage(String hql, int page, int fetchSize, Object... params);
	
	public List<T> executeSqlEntity(String sql, Object... params);
	
	public List<Object> executeSql(String hql, String[] paramNames, Object...params);
	
	public List<T> executeSql(String sql, ResultTransformer transformer, Object... params);
	
	public List<Object> executeSql(String sql, ResultTransformer transformer, String[] paramNames, Object... params);
	
	public List<Object> executeSqlPage(String sql, int page, int fetchSize, ResultTransformer transformer, String[] paramNames, Object... params);
	
	public List<Object> executeSqlPage(String hql, int page, int fetchSize,String[] paramNames, Object...params);
	
	public int countSql(String sql, String[] paramNames, Object...params);
	
	public BigDecimal sum(DetachedCriteria detachedCriteria, String columnName);

	public void evict(Object object);

}
