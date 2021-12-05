package br.com.schoolcalendar.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.dtos.StudentDto;
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

	@GetMapping("/public/{publicId}")
	public StudentDto findByPublicId(@PathVariable String publicId) {
		return StudentDto.convertToDto(studentService.findByPublicId(publicId));
	}

	@PutMapping("/{id}")
	public StudentDto update(@PathVariable Long id, @RequestBody StudentForm form) {
		return StudentDto.convertToDto(studentService.update(id, form));
	}
	
	@GetMapping()
	public Page<StudentDto> find(@RequestParam Optional<String> filter, Pageable pageable) {
		return StudentDto.convertTo(studentService.find(filter, pageable));
	}
}
