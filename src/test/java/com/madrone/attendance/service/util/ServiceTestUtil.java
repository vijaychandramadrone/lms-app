package com.madrone.attendance.service.util;

import java.util.Calendar;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.madrone.attendance.entity.Department;
import com.madrone.attendance.entity.DesignationEnum;
import com.madrone.attendance.entity.Employee;
import com.madrone.attendance.entity.EmployeeLeave;
import com.madrone.attendance.entity.Leave;
import com.madrone.attendance.entity.Role;
import com.madrone.attendance.entity.User;
import com.madrone.attendance.service.DepartmentService;
import com.madrone.attendance.service.EmployeeLeaveService;
import com.madrone.attendance.service.EmployeeService;
import com.madrone.attendance.service.LeaveService;
import com.madrone.attendance.service.RoleService;
import com.madrone.attendance.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", 
		"classpath:spring/hibernateContext.xml"})
@Component
public class ServiceTestUtil {
	
	// Spring does not allow Autowiring static fields. Hence as suggested in 
	// few forums, defined setters for these static fields and autowired the
	// setter methods. Also made the class a component for this to work.
	
	private static UserService userService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static RoleService roleService;
	private static LeaveService leaveService;
	private static EmployeeLeaveService employeeLeaveService;
	
	@Autowired
	public void setUserService(UserService userService) {
		ServiceTestUtil.userService = userService;
	}
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		ServiceTestUtil.employeeService = employeeService;
	}
	
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		ServiceTestUtil.departmentService = departmentService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		ServiceTestUtil.roleService = roleService;
	}
	
	@Autowired
	public void setLeaveService(LeaveService leaveService) {
		ServiceTestUtil.leaveService = leaveService;
	}
	
	@Autowired
	public void setemployeeLeaveService(
			EmployeeLeaveService employeeLeaveService) {
		ServiceTestUtil.employeeLeaveService = employeeLeaveService;
	}
	
	private ServiceTestUtil() {
	}

	public static User createUser(String empId, String deptId, String roleId, 
			String primaryEmail) {
		User u = userService.findByUserName(primaryEmail);
		if(u == null) {

			Employee e = createEmployee(empId, deptId, roleId, primaryEmail);

			u = new User(primaryEmail, "password");
			u.setEmployee(e);
			userService.saveUser(u);
		}
		return u;
	}

	public static Employee createEmployee(String empId, String deptId, 
			String roleId, String primaryEmail) {

		Employee e = employeeService.findById(empId);
		if(e == null) {
			e = new Employee(empId, "tom", "jerry", primaryEmail, null, 
					Calendar.getInstance(), DesignationEnum.SSE, 
					"#25 Chitrakulam north st", "Mylapore", "Chennai", 
					"TN", 600004);
			Department d = createDepartment(deptId, null);
			e.setDept(d);
			
			Role r = createRole(roleId, null);
			e.setRole(r);
			
			employeeService.saveEmployee(e);
		}
		return e;
	}

	public static Department createDepartment(String deptId, String desc) {
		Department d = departmentService.findById(deptId);
		if(d == null) {
			d = new Department(deptId, desc != null ? desc : "test");
			departmentService.saveDepartment(d);
		}
		return d;
	}
	
	public static Role createRole(String roleId, String desc) {
		Role r = roleService.findById(roleId);
		if(r == null) {
			r = new Role(roleId, desc != null ? desc : "test");
			roleService.saveRole(r);
		}
		return r;
	}

	public static Leave createLeave(String leaveId, String desc, int days) {	Leave l = leaveService.findById(leaveId);
		if(l == null) {
			l = new Leave(leaveId, desc != null ? desc : "test", days);
			leaveService.saveLeave(l);
		}
		return l;
	}

	public static EmployeeLeave createEmployeeLeave(String empId, String deptId, 
			String roleId, String primaryEmail, String leaveId) {
		
		Employee e = createEmployee(empId, deptId, roleId, primaryEmail);
		Leave l = createLeave(leaveId, "test", 10);
		Calendar fromDate = Calendar.getInstance();
		fromDate.add(Calendar.DAY_OF_MONTH, 5);
		Calendar toDate = Calendar.getInstance();
		toDate.add(Calendar.DAY_OF_MONTH, 7);
				
		EmployeeLeave el = new EmployeeLeave(e, l, fromDate, toDate);
		employeeLeaveService.saveEmployeeLeave(el);
		
		return el;
	}

}
