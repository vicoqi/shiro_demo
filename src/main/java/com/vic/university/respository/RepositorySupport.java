package com.vic.university.respository;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

public class RepositorySupport<T> implements BaseRepository<T>{

	private SessionFactory sessionFactory;
	private Class<T> domainClass;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public RepositorySupport() {
		domainClass = (Class<T>) ((ParameterizedType) getClass() // Class字节码
				.getGenericSuperclass()) // 因为对于T.class我们无法获取，但是这个方法就能获取到父类的参数类型，返回值为ParameterizedType
				.getActualTypeArguments()[0]; // 数组里第一个就是子类继承父类时所用类型
		System.out.println(domainClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Long id) {
		return (T) getCurrentSession().get(getDomainClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Integer id) {
		return (T) getCurrentSession().get(getDomainClass(), id);
	}
	
	@Override
	public T findOne(DetachedCriteria detachedCriteria) {
		List<T> list = findAll(detachedCriteria, 0, 1);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public List<?> statistics(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
        return criteria.list();
	}
	

	@Override
	public Integer count() {
		Criteria criteria = getCurrentSession().createCriteria(getDomainClass());
		criteria.setProjection(Projections.rowCount());
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	@Override
	public Integer count(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		criteria.setProjection(Projections.rowCount());
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	@Override
	public BigDecimal avg(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		return (BigDecimal) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = getCurrentSession().createCriteria(getDomainClass());
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(int page, int fetchSize) {
		Criteria criteria = getCurrentSession().createCriteria(getDomainClass());
		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(page*fetchSize);
        criteria.setMaxResults(fetchSize);
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Collection<Long> ids) {
		Criteria criteria = getCurrentSession().createCriteria(getDomainClass());
		criteria.add(Restrictions.in("id", ids.toArray(new Object[] {})));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		return criteria.list();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll(DetachedCriteria detachedCriteria, int page, int pageSize) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		criteria.setFirstResult(page*pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll(DetachedCriteria detachedCriteria, int page, int pageSize,int totalSize) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		int fir = totalSize - (page+1)*pageSize;
		if((pageSize > totalSize && page == 0) || (fir < 0 && fir+pageSize >= 0)){
			fir = 0 ;
		}
		
		criteria.setFirstResult(fir);
        criteria.setMaxResults(pageSize);
        return criteria.list();
	}
/*
	@Override
	public Paginator<T> findPage(DetachedCriteria detachedCriteria, int page, int pageSize) {
		int totalCnt = count(detachedCriteria);
		detachedCriteria.setProjection(null);
		List<T> list = findAll(detachedCriteria, page, pageSize);
		return new Paginator<T>(list, totalCnt, page+1, pageSize);
	}
*/	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll(String hql, int page, int fetchSize, ResultTransformer transformer, Object...params) {
		Query query = getCurrentSession().createQuery(hql);
		int i = 0;
		for(Object param : params) {
			query.setParameter(i ++, param);
		}
		query.setFirstResult(page*fetchSize);
		query.setMaxResults(fetchSize);
		query.setResultTransformer(transformer);
		return query.list();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll(String hql, ResultTransformer transformer, Object...params) {
		Query query = getCurrentSession().createQuery(hql);
		int i = 0;
		for(Object param : params) {
			query.setParameter(i ++, param);
		}
		query.setResultTransformer(transformer);
		return query.list();
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll(String hql, Object...params) {
		Query query = getCurrentSession().createQuery(hql);
		int i = 0;
		for(Object param : params) {
			query.setParameter(i ++, param);
		}
		return query.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public T save(T entity) {
		if (getCurrentSession().contains(entity)) {
			return (T) getCurrentSession().merge(entity);
		} else {
			getCurrentSession().saveOrUpdate(entity);
			getCurrentSession().flush();
		}
		return entity;
	}
	
	@Override
	public List<T> batchSave(Collection<T> list) {
		List<T> l = new LinkedList<T>();
		for (T t : list) {
			l.add(save(t));
		}
		return l;
	}
	
	@Override
	public T delete(T entity) {
		getCurrentSession().delete(entity);
		return entity;
	}
	
	@Override
	public List<T> deleteAll(List<T> list) {
		for (T t : list) {
			delete(t);
		}
		return list;
	}
	
	@Override
	public int executeHql(String hql, Object...params) {
		Query query = getCurrentSession().createQuery(hql);
		int i = 0;
		for(Object param : params) {
			if(param instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)param);
			} else {
				query.setParameter(i ++, param);
			}
		}
		return query.executeUpdate();
	}
	
	@Override
	public int executeHql(String hql, String[] paramNames, Object...params) {
		Query query = getCurrentSession().createQuery(hql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		return query.executeUpdate();
	}
	
	@Override
	public int executeUpdateSql(String sql, Object...params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(Object param : params) {
			if(param instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)param);
			} else {
				query.setParameter(i ++, param);
			}
		}
		return query.executeUpdate();
	}
	
	@Override
	public int executeUpdateSql(String sql, String[] paramNames, Object...params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		return query.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeSql(String sql, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(Object param : params) {
			if(param instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)param);
			} else {
				query.setParameter(i ++, param);
			}
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeSqlPage(String sql, int page, int fetchSize, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(Object param : params) {
			if(param instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)param);
			} else {
				query.setParameter(i ++, param);
			}
		}
		query.setFirstResult(page*fetchSize);
		query.setMaxResults(fetchSize);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeSql(String sql, String[] paramNames, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeSql(String sql, ResultTransformer transformer, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(Object param : params) {
			if(param instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)param);
			} else {
				query.setParameter(i ++, param);
			}
		}
		query.setResultTransformer(transformer);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeSql(String sql, ResultTransformer transformer, String[] paramNames, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		query.setResultTransformer(transformer);
		return query.list();
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Object> executeSqlPage(String sql, int page, int fetchSize, ResultTransformer transformer, String[] paramNames, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		query.setResultTransformer(transformer);
		query.setFirstResult(page*fetchSize);
		query.setMaxResults(fetchSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeSqlPage(String sql, int page, int fetchSize, String[] paramNames, Object... params) {
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		query.setFirstResult(page*fetchSize);
		query.setMaxResults(fetchSize);
		return query.list();
	}
	public Class<T> getDomainClass() {
		return domainClass;
	}



	@Override
	public BigDecimal sum(DetachedCriteria detachedCriteria,String columnName) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		criteria.setProjection(Projections.sum(columnName));
		return (BigDecimal) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countSql(String sql, String[] paramNames, Object... params) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		int i = 0;
		for(String paramName : paramNames) {
			if(params[i] instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)params[i++]);
			} else {
				query.setParameter(paramName, params[i++]);
			}
		}
		List<BigInteger> list = query.list();
        int count = list.get(0).intValue();
		return count;
		//return (BigInteger) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeSqlEntity(String sql, Object... params) {
		SQLQuery query = getCurrentSession().createSQLQuery(sql).addEntity(domainClass);
		int i = 0;
		for(Object param : params) {
			if(param instanceof Collection) {
				query.setParameterList("ids", (Collection<?>)param);
			} else {
				query.setParameter(i ++, param);
			}
		}
		return query.list();
	}
	
	@Override
	public void evict(Object object) {
		getCurrentSession().evict(object);
	}
	
}
