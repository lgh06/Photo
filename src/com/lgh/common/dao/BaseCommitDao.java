package com.lgh.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import com.lgh.common.tools.GenericUtile;
@SuppressWarnings("unchecked")
public class BaseCommitDao<T extends Serializable>{
	@Autowired
	private SessionFactory sessionFactory;
	private Class<? extends Object> entityClass;
	private String pkname;
	private final String HQL_LIST_ALL;
	private final String HQL_COUNT_ALL;
	
	public BaseCommitDao() {
		Class<? extends Object> genericClass = GenericUtile.getSuperGenericClass(this.getClass());
		this.setEntityClass(genericClass);
		this.getPkname();
		HQL_LIST_ALL="from "+this.entityClass.getSimpleName()+" order by "+pkname+" desc";
		HQL_COUNT_ALL="select count(*) from "+this.entityClass.getSimpleName();
	}
	
	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		return entityClass;
	}
	
	@SuppressWarnings("rawtypes")
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}
	
	public String getPkname() {
		Class<? extends Object> clazz = this.entityClass;
		while (this.pkname==null&&clazz!=null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if(field.isAnnotationPresent(Id.class)){
					this.pkname=field.getName();
					break;
				}
			}
			clazz=clazz.getSuperclass();
		}
		return this.pkname;
	}
	
	public void save(T t){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
	}
	
	public void save(Collection<T> c){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		for (T t : c) {
			session.save(t);
		}
		session.getTransaction().commit();
	}
	
	public void update(T t){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
	}
	
	public void delete(T t){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
	}
	@Deprecated
	public void delete(String field,Object value){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete "+this.entityClass.getSimpleName()+" where "+field+" = '"+value+"'").executeUpdate();
		session.getTransaction().commit();
	}
	
	
	public T get(Serializable id){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		T t=(T) session.get(getEntityClass(), id);
		session.getTransaction().commit();
		return t;
	}
	
	
	public T get(Class<T> clazz,Serializable id){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		T t=(T) session.get(clazz, id);
		session.getTransaction().commit();
		return t;
	}
	
	
	public List<T> findByAll(){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(HQL_LIST_ALL);
		List<T> list = query.list();
		session.getTransaction().commit();
		return list;
	}
	
	
	public Integer findAllCount() {
		Integer count=0;
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(HQL_COUNT_ALL);
		List<?> list = query.list();
		session.getTransaction().commit();
		if(list!=null&&!list.isEmpty()){
			count=((Integer) list.get(0)).intValue();
		}
		return count;
	}
	
	public List<T> findByCriteria(Criteria criteria) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<T> list = criteria.list();
		session.getTransaction().commit();
		return list;
	}
	
	
	public List<T> findByExample(T t) {
		Example example = Example.create(t);
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(getEntityClass());
		criteria.add(example);
		List<T> list = criteria.list();
		session.getTransaction().commit();
		return list;
	}
	
	@Deprecated
	public List<T> findByProperty(String field,Object value) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<T> list=session.createQuery("from "+this.entityClass.getSimpleName()+" where "+field+" = '"+value+"'").list();
		session.getTransaction().commit();
		return list;
	}
	
	public List<T> findByHql(String hql,final Object...objects){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		List<T> list = query.list();
		session.getTransaction().commit();
		return list;
	}
	
	public List<Object[]> findBySql(String sql,final Object...objects){
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		List<Object[]> list = query.list();
		session.getTransaction().commit();
		return list;
	}
}
