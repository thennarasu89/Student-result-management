package com.kashvillan.studentresult.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashvillan.studentresult.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
