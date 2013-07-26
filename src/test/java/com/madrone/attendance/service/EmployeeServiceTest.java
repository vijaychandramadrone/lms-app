package com.madrone.attendance.service;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.madrone.attendance.entity.Department;
import com.madrone.attendance.entity.Employee;
import com.madrone.attendance.service.util.ServiceTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", 
		"classpath:spring/hibernateContext.xml"})
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;

	private final String ROLE_R1 = "r1";
	private final String DEPT_D1 = "d1";
	private final String EMP_100 = "100";
	private final String USER_NAME = "tom@jerry.com";

    @After
    public void tearDown() throws Exception {
    	if(employeeService.findById(EMP_100) != null) {
    		employeeService.deleteEmployee(EMP_100);
    	}
    	if(departmentService.findById(DEPT_D1) != null) {
    		departmentService.deleteDepartment(DEPT_D1);
    	}
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee e = createEmployee();
        Employee found = employeeService.findById(EMP_100);
        assertEquals(e, found);
    }

    @Test
    public void testDeleteEmployee() throws Exception {
    	createEmployee();
        assertNotNull(employeeService.findById(EMP_100));
        employeeService.deleteEmployee(EMP_100);
        assertNull(employeeService.findById(EMP_100));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
    	
    	Employee e = createEmployee();
        assertNotNull(e.getDept());
        assertEquals(DEPT_D1, e.getDept().getId());

        Department d2 = new Department("d2", "department 2");
        departmentService.saveDepartment(d2);
        
        e.setDept(d2);
        employeeService.saveEmployee(e);
        
        e = employeeService.findById(EMP_100);
        assertNotNull(e.getDept());
        assertEquals(d2.getId(), e.getDept().getId());
    }
    
    private Employee createEmployee() {
    	return ServiceTestUtil.createEmployee(EMP_100, DEPT_D1, 
    			ROLE_R1, USER_NAME);
    }
}