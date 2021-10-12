package br.com.schoolcalendar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.exception.dtos.StudentDto;
import br.com.schoolcalendar.forms.StudentForm;
import br.com.schoolcalendar.interfaces.IStudentService;

@RestController
@RequestMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public StudentDto save(@RequestBody @Valid StudentForm form) {
		return StudentDto.convertToDto(studentService.save(form));
	}
	
	@GetMapping("/{id}")
	public StudentDto findById(@PathVariable Long id) {
		return StudentDto.convertToDto(studentService.findById(id));
	}
	
	@GetMapping("/{publicId}")
	public StudentDto findByPublicId(String publicId) {
		return StudentDto.convertToDto(studentService.findByPublicId(publicId));
	}
}
