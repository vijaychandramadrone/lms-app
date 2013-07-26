package com.madrone.lms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madrone.lms.dao.EmployeeDao;
import com.madrone.lms.dao.UserDao;
import com.madrone.lms.entity.Employee;
import com.madrone.lms.entity.User;
import com.madrone.lms.service.UserService;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeDao empDao;
		
	@Override
    public User findById(Long id) {
		return userDao.findById(id);	
    }
 
    @Override
    @Transactional(readOnly = false)
    public void saveUser(User user) {
    	if(user != null) {
    		Employee e = empDao.findByEmailAddress(user.getUserName());

    		// Temporary code. Shall be removed after binding the HR module 
    		// with the LMS module. 
    		// if Employee does not exist, create employee on the fly
    		if(e == null) {
    			empDao.saveEmployee(user.getEmployee());
    		}

    		userDao.saveUser(user);
    		logger.info("Successfully saved " + user.toString());
    	} else { 
    		throw new IllegalArgumentException("User object passed is null");
    	}	
    }
 
    @Override
    @Transactional(readOnly = false)
    public void deleteUser(String userName) {
        User user = userDao.findByUserName(userName);
        userDao.delete(user);
    }
 
    @Override
    public User findByUserName(String userName) {
    	return userDao.findByUserName(userName);	
    }
	
}