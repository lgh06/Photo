package com.lgh.test;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgh.sys.entity.User;

@Service
public class TestService {
	@Autowired
	private TestDAO testDAO;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> findBySome(String id,Class clazz){
		
		DetachedCriteria criteria=DetachedCriteria.forClass(clazz);
		criteria.add(Restrictions.eq("id",id ));
		List<User> list = testDAO.findByCriteria(criteria);
		
		
		
		return list ;
	}
	
	public void addUser(User user){
		
		testDAO.saveOrUpdate(user);
		
		
	}
	
}
