package com.restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.dao.EmpDao;
import com.restfull.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	public EmpDao ed;

	@PostMapping(value = "/addEmp", consumes = { "application/xml", "application/json" })
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {

		System.out.println(employee);
		String msg = "Employee Created";

		ed.save(employee);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);

	}

	@DeleteMapping("/deleteEmp")
	public ResponseEntity<String> deleteEmp(@RequestParam String name){
		
		Employee findByName = ed.findByName(name);
		String msg;
		if(findByName==null) {
			
		  msg="Employee"+ name +"not found";
		   
		 }
		else {
			
			ed.delete(findByName);
			
		  msg="Employee "+ name +" found and Deleted";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.GONE);
		
	}
	
	@PutMapping("/updateEmp")
	public ResponseEntity<String> updateEmp(@RequestParam String searchName,@RequestParam String name,@RequestParam String city,@RequestParam String mail,@RequestParam int salary){
		
		String msg;
		Employee findByName = ed.findByName(searchName);
		if(findByName==null) {
			
			 msg="Employee "+ searchName +" not found";
		}
		
		else {
			
			findByName.setName(name);
			findByName.setCity(city);
			findByName.setMail(mail);
			findByName.setSalary(salary);
			ed.save(findByName);
			  msg="Employee "+ searchName +" found and Updated";
			
		}
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
		
		
	}

}
