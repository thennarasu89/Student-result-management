package com.kashvillan.studentresult.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashvillan.studentresult.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentRegNo(Long regNo);

    List<Result> findBySubjectSubCode(Long subCode);

}
