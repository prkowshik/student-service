package com.codezen.app.service;

import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codezen.app.entity.Student;
import com.codezen.app.model.Address;
import com.codezen.app.model.CreateStudentRequest;
import com.codezen.app.model.StudentResponse;
import com.codezen.app.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RestTemplate restTemplate;

	public StudentResponse createStudent(CreateStudentRequest request) {
		Student student = new Student();
		BeanUtils.copyProperties(request, student);
		studentRepository.save(student);

		StudentResponse response = new StudentResponse();
		response.setFirstName(request.getFirstName());
		response.setLastName(request.getLastName());
		response.setAddress(getAddressById(request.getAddressId()));
		return response;
	}

	public StudentResponse getStudentById(Integer id) {
		Student student = studentRepository.findById(id).get();

		StudentResponse response = new StudentResponse();
		response.setId(student.getId());
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setAddress(getAddressById(student.getAddressId()));
		return response;
	}

	private Address getAddressById(Integer addressId) {
		// Before spring 2.5, we were using Rest template

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8082/api/address/getById/" +addressId, HttpMethod.GET, entity, Address.class).getBody();
	}

}
