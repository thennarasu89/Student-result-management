package com.kashvillan.studentresult.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kashvillan.studentresult.dto.ResultRequestDto;
import com.kashvillan.studentresult.dto.ResultResponseDto;
import com.kashvillan.studentresult.entity.Result;
import com.kashvillan.studentresult.entity.Student;
import com.kashvillan.studentresult.entity.Subject;
import com.kashvillan.studentresult.repositories.ResultRepository;
import com.kashvillan.studentresult.repositories.StudentRepository;
import com.kashvillan.studentresult.repositories.SubjectRepository;
import com.kashvillan.studentresult.service.ResultService;
@Service
public class ResultServiceImpl implements ResultService {
	
	
	 private final ResultRepository resultRepository;
	    private final StudentRepository studentRepository;
	    private final SubjectRepository subjectRepository;

	    public ResultServiceImpl(ResultRepository resultRepository,
	                             StudentRepository studentRepository,
	                             SubjectRepository subjectRepository) {
	        this.resultRepository = resultRepository;
	        this.studentRepository = studentRepository;
	        this.subjectRepository = subjectRepository;
	    }

	    @Override
	    public ResultResponseDto addResult(ResultRequestDto request) {

	        Student student = studentRepository.findById(request.getStudentRegNo())
	                .orElseThrow();

	        Subject subject = subjectRepository.findById(request.getSubjectCode())
	                .orElseThrow();

	        Result result = new Result();
	        result.setStudent(student);
	        result.setSubject(subject);
	        result.setMarks(request.getMarks());

	        Result saved = resultRepository.save(result);

	        ResultResponseDto response = new ResultResponseDto();
	        response.setStudentRegNo(student.getRegNo());
	        response.setStudentName(student.getName());
	        response.setSubjectCode(subject.getSubCode());
	        response.setSubjectName(subject.getSubjectName());
	        response.setMarks(saved.getMarks());

	        return response;
	    }

	    @Override
	    public List<ResultResponseDto> getResultByStudent(Long regNo) {
	        return resultRepository.findByStudentRegNo(regNo)
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<ResultResponseDto> getResultBySubject(Long subCode) {
	        return resultRepository.findBySubjectSubCode(subCode)
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	    }

	    private ResultResponseDto mapToResponse(Result result) {
	        ResultResponseDto dto = new ResultResponseDto();
	        dto.setStudentRegNo(result.getStudent().getRegNo());
	        dto.setStudentName(result.getStudent().getName());
	        dto.setSubjectCode(result.getSubject().getSubCode());
	        dto.setSubjectName(result.getSubject().getSubjectName());
	        dto.setMarks(result.getMarks());
	        return dto;
	    }
	}

