package com.lgh.common.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lgh.common.tools.GenericUtile;
public class BaseTransactionDao<T extends Serializable> extends HibernateDaoSupport{
	@Resource(name="sessionFactory")
	private void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	private Class<? extends Object> entityClass;
	
	public Class<? extends Object> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<? extends Object> entityClass) {
		this.entityClass = entityClass;
	}
	public BaseTransactionDao() {
		Class<? extends Object> genericClass = GenericUtile.getSuperGenericClass(this.getClass());
		this.setEntityClass(genericClass);
	}
	public void save(T t){
		super.getHibernateTemplate().save(t);
	}
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id){
		return (T) super.getHibernateTemplate().get(this.getEntityClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> loadAll(Class<? extends Object> entityClass){
		return (List<T>) super.getHibernateTemplate().loadAll(entityClass);
	}
	public List<T> findByAll(){
		return this.loadAll(entityClass);
	}
	@SuppressWarnings("rawtypes")
	public List findByCriteria(DetachedCriteria criteria){
		List list = super.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
	/**
	 * 
	 * findByCriteria:
	 * 
	 * @author 刘各欢
	 * @param criteria qbc对象
	 * @param firstResult 第一个结果
	 * @param maxResults 最后一个结果
	 * @return
	 * @since  　Ver 1.1
	 */
	@SuppressWarnings("unchecked")
	public List<T>findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		return super.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T t){
		return super.getHibernateTemplate().findByExample(t);
	}
	
	@SuppressWarnings("unchecked")
	public Long findCountByAll(){
		String hql = "select count(*) from "+entityClass.getSimpleName();
		List<Object> list = this.getHibernateTemplate().find(hql);
		Object countObj = list.get(0);
		Long count = Long.valueOf(countObj.toString());
		return count;
	}
	
	public void update(T t){
		super.getHibernateTemplate().update(t);
	}
	public void delete(T t){
		super.getHibernateTemplate().delete(t);
	}
	@SuppressWarnings("rawtypes")
	public List find(String queryString, Object...values){
		return super.getHibernateTemplate().find(queryString, values);
	}
	
	public void saveOrUpdate(T t){
		super.getHibernateTemplate().saveOrUpdate(t);
	}
}
