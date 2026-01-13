package com.kashvillan.studentresult.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kashvillan.studentresult.dto.SubjectRequestDto;
import com.kashvillan.studentresult.dto.SubjectResponseDto;
import com.kashvillan.studentresult.entity.Subject;
import com.kashvillan.studentresult.repositories.SubjectRepository;
import com.kashvillan.studentresult.service.SubjectService;

@Service

public class SubjectServiceImpl implements SubjectService {
	private final SubjectRepository subjectRepository;
	
	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@Transactional
	@Override
	public SubjectResponseDto createSubject(SubjectRequestDto request) {
		 Subject subject = new Subject();
	        subject.setSubCode(request.getSubCode());
	        subject.setSubjectName(request.getSubjectName());
	        if (subjectRepository.existsById(request.getSubCode())) {
	            throw new RuntimeException("Subject already exists with code: " + request.getSubCode());
	        }

	        Subject saved = subjectRepository.save(subject);

	        SubjectResponseDto response = new SubjectResponseDto();
	        response.setSubCode(saved.getSubCode());
	        response.setSubjectName(saved.getSubjectName());

	        return response;
	}
	

    @Override
    public SubjectResponseDto getSubjectByCode(Long subCode) {
        Subject subject = subjectRepository.findById(subCode).orElseThrow();

        SubjectResponseDto response = new SubjectResponseDto();
        response.setSubCode(subject.getSubCode());
        response.setSubjectName(subject.getSubjectName());

        return response;
    }

    @Override
    public List<SubjectResponseDto> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subject -> {
                    SubjectResponseDto dto = new SubjectResponseDto();
                    dto.setSubCode(subject.getSubCode());
                    dto.setSubjectName(subject.getSubjectName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
