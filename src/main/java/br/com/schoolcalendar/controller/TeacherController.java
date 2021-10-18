package br.com.schoolcalendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.exception.dtos.TeacherDto;
import br.com.schoolcalendar.service.TeacherService;

@RestController
@RequestMapping(path = "/teacher", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("/{id}")
	public TeacherDto findById(@PathVariable Long id) {
		return TeacherDto.convertTo(teacherService.findById(id));
	}
}
