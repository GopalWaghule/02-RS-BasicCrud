package com.restfull.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restfull.model.Employee;

public interface EmpDao extends JpaRepository<Employee, Serializable> {
	
	public Employee findByName(String name);

}
