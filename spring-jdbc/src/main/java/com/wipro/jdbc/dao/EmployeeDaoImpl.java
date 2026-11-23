package com.wipro.jdbc.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.wipro.jdbc.model.Employee;
import java.util.List;
@Service
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
*/
	/*-------------- Retrieve single record from table  using BeanPropertyRowMapper --------------*/
	@Override
	public Employee getSingleRecordWithBeanPropertyRowMapper() {
		String sql = "select * from employee where emp_name='Jobin'";
		Employee emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
		return emp;
	}

	public Employee getSingleRecordWithResultSetExtractor() {
		String sql = "select * from employee where empname='Jobin'";
		Employee employee = jdbcTemplate.query(sql, new ResultSetExtractorImpl());
		return employee;
	}

	

	/* -------------- retrieve multiple records--------------- */
	public List<Employee> getMultipleRecordWithResultSetExtractor(){
		String sqlQuery = "select * from employee";
		List<Employee> listOfEmp=(List<Employee>)jdbcTemplate.query(sqlQuery, new ResultSetExtractorImpl1());
		return listOfEmp;
	}
	
	 public void getRecordWithRowCallbackHandler() {
		 jdbcTemplate.query("select emp_name from employee",new RowCallbackHandler() {
				
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					do {
						System.out.println("processrow method called");
						System.out.println(rs.getString("emp_name"));
					}while(rs.next());
					
				}
			});
	 }

	@Override
	public Employee getSingleRecordWithRowMapper() {
		String sql="select * from employee where emp_name='Priya'";
		/*--------------retrieve single record----------------*/
		Employee employee=jdbcTemplate.queryForObject(sql, new RowMapperImpl());
		//System.out.println(employee.getEmpname());
		return employee;
	}

	@Override
	public List<Employee> getRecordsWithRowMapper() {
		/* -------------- retrieve multiple records---------------*/
		String sqlQuery="select * from employee";
		List<Employee> employees=jdbcTemplate.query(sqlQuery,new RowMapperImpl());
		return employees;
	}
	 
	

}
