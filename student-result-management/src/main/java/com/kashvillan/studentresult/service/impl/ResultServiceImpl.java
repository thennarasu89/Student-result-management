package com.kashvillan.studentresult.service.impl;

import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kashvillan.studentresult.dto.ResultRequestDto;
import com.kashvillan.studentresult.dto.ResultResponseDto;
import com.kashvillan.studentresult.entity.Result;
import com.kashvillan.studentresult.entity.Student;
import com.kashvillan.studentresult.entity.Subject;
import com.kashvillan.studentresult.entity.User;
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
	    	
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	
	    	User loggedInUser = (User) authentication.getPrincipal();
	    	
	        Student student = studentRepository.findById(request.getStudentRegNo())
	                .orElseThrow(() -> new RuntimeException("Student not found"));

	        Subject subject = subjectRepository.findById(request.getSubjectCode())
	                .orElseThrow(() -> new RuntimeException("Subject Not Found"));
	        if("ADMIN".equals(loggedInUser.getRole())){
	        	
	        }
	        else if("TEACHER".equals(loggedInUser.getRole())) {
	        	String teacherClass = loggedInUser.getAssignedClass();
	        	String studentClass = student.getAssignedClass();
	        	
	        	if(!studentClass.equals(teacherClass)) {
	        		throw new AccessDeniedException("You are not allowed to access this class");
	        	}
	        	
	        }
	        else {
				throw new AccessDeniedException("You're not allowed to acess this ");
			}
	        
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
	    public List<ResultResponseDto> getResultByStudent(Long regNo) throws AccessDeniedException {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if(authentication != null && authentication.isAuthenticated()) {
	        	Object principal = authentication.getPrincipal();
	        	
	        	if(principal instanceof User user) {
	        		if("STUDENT".equals(user.getRole())) {
	        			String username = user.getUsername();
	        			Long currentRegNo = Long.parseLong(username.split("@")[1]);
	        			
	        			if(!currentRegNo.equals(regNo)) {
	        				throw new AccessDeniedException("YOU CAN PNLY VEIW YOUR RESULT");
	        				
	        			}
	        		}
	        	}
	        }
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

