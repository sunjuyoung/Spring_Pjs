package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SqlVO {

	private String email,fitst_name,last_name,phone_number,job_id;
	
	private int salary,employee_id , commission_pct,manager_id,department_id;
	
	private Date hire_date;
	
	
}
