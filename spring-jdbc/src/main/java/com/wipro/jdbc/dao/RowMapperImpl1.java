package com.wipro.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.wipro.jdbc.model.Employee;

public class RowMapperImpl1 implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int n) throws SQLException {
		Employee emp=new Employee();
		System.out.println("mapRow called");
		emp.setEmpName(rs.getString("emp_name"));
		
		return emp;
	}

}