package com.codezen.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codezen.app.entity.Student;
import com.codezen.app.feignclients.AddressFeignClient;
import com.codezen.app.model.Address;
import com.codezen.app.model.CreateStudentRequest;
import com.codezen.app.model.StudentResponse;
import com.codezen.app.repository.StudentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CommonService commonService;
	
	public StudentResponse createStudent(CreateStudentRequest request) {
		Student student = new Student();
		BeanUtils.copyProperties(request, student);
		studentRepository.save(student);

		StudentResponse response = new StudentResponse();
		response.setFirstName(request.getFirstName());
		response.setLastName(request.getLastName());
		response.setAddress(commonService.getAddressById(request.getAddressId()));
		return response;
	}

	public StudentResponse getStudentById(Integer id) {
		Student student = studentRepository.findById(id).get();

		StudentResponse response = new StudentResponse();
		response.setId(student.getId());
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setAddress(commonService.getAddressById(student.getAddressId()));
		return response;
	}
	
}
