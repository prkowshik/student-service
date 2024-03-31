package com.codezen.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codezen.app.model.CreateStudentRequest;
import com.codezen.app.model.StudentResponse;
import com.codezen.app.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
    private static final  Logger log = LoggerFactory.getLogger(StudentController.class);

	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public StudentResponse createStudent(@RequestBody CreateStudentRequest request) {
		return studentService.createStudent(request);
	}
	
	@GetMapping("/getById/{id}")
	public StudentResponse getStudentById(@PathVariable Integer id){
		log.info("Get Student request for id = {}", id);
		return studentService.getStudentById(id);
	}
		
	
	
	

}
