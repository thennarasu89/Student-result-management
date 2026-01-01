package com.kashvillan.studentresult.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashvillan.studentresult.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
