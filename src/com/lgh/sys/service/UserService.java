package com.lgh.sys.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.TypedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgh.sys.dao.UserDao;
import com.lgh.sys.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * 
	 * save:添加用户
	 * 
	 * @author 左天琪
	 * @param user
	 * @since  　Ver 1.1
	 */
	public void save(User user){
		userDao.save(user);
	
		}
	
	/**
	 * 根据用户名和密码查询用户是否存在
	 * @param user
	 * @return
	 */
	public boolean existUser(User user){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("name", user.getName()));
		detachedCriteria.add(Restrictions.eq("password", user.getPassword()));
		detachedCriteria.add(Restrictions.eq("role", user.getRole()));
		List list = userDao.findByCriteria(detachedCriteria);
		
		if(list.isEmpty()){
			return false;
		}else{
			return true;
		}
	
		}
		
	}
