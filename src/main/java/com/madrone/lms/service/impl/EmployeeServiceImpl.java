package com.madrone.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madrone.lms.dao.EmployeeDao;
import com.madrone.lms.entity.Employee;
import com.madrone.lms.service.EmployeeService;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empDao;
	
	@Override
	public Employee findById(String id) {
		return empDao.findById(id);
	}
	
	@Override
	public Employee findByIdWithLeaves(String id) {
		return empDao.findByIdWithLeaves(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveEmployee(Employee e) {
		empDao.saveEmployee(e);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(String id) {
		Employee e = empDao.findById(id);
		empDao.delete(e);		
	}

	@Override
	public Employee findByEmailAddress(String primaryEmail) {
		return empDao.findByEmailAddress(primaryEmail);
	}

	@Override
	public Employee findByEmailAddressWithLeaves(String primaryEmail) {
		return empDao.findByEmailAddressWithLeaves(primaryEmail);
	}

	@Override
	public String findMenuOption(String userName) {
		return empDao.findRole(userName);
	}
}
