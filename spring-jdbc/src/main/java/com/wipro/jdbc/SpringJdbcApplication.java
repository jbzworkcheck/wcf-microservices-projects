package com.wipro.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.wipro.jdbc.dao.*;
import java.util.List;
import com.wipro.jdbc.model.Employee;

@SpringBootApplication
public class SpringJdbcApplication {
	

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringJdbcApplication.class, args);
		EmployeeDao dao=context.getBean(EmployeeDaoImpl.class);
Employee emp=dao.getSingleRecordWithBeanPropertyRowMapper();
	System.out.println(emp.getEmpName());
		//dao.getRecordWithRowCallbackHandler();
		// insert employee
	//JdbcTemplate jdbcTemplate=context.getBean("jdbcTemplate",JdbcTemplate.class);
		// insert or update or delete 
/*	String sql="INSERT INTO employee(emp_name,age,salary) VALUES ('Antony', 30, 80000)";
		int rowCount=jdbcTemplate.update(sql);
	System.out.println(rowCount);*/
		// Retrieve Single Employee using RowMapper
//Employee emp1=dao.getSingleRecordWithRowMapper();
//System.out.println(emp1.getEmpName());
		
		// Retrieve List of Employee using RowMapper
	/*List<Employee> listOfEmployee=dao.getRecordsWithRowMapper();
		for(Employee emp:listOfEmployee) {
			System.out.println(emp.getEmpName());
		}
*/
	/*	List<Employee> listOfEmployee1=dao.getMultipleRecordWithResultSetExtractor();
		for(Employee emp:listOfEmployee1) {
			System.out.println(emp.getEmpName());
		}
	*/
	
	}

}
