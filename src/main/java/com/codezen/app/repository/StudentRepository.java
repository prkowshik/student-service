package com.codezen.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codezen.app.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
