package com.wipro.jdbc.dao;

import com.wipro.jdbc.model.Employee;
import java.util.List;

public interface EmployeeDao {
	Employee getSingleRecordWithRowMapper();

	List<Employee> getRecordsWithRowMapper();

	Employee getSingleRecordWithBeanPropertyRowMapper();

	Employee getSingleRecordWithResultSetExtractor();

	List<Employee> getMultipleRecordWithResultSetExtractor();

	void getRecordWithRowCallbackHandler();
}
