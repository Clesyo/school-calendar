package br.com.schoolcalendar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.dtos.ScheduleDisciplineDto;
import br.com.schoolcalendar.forms.ScheduleDisciplineForm;
import br.com.schoolcalendar.interfaces.IScheduleDisciplineService;

@RestController
@RequestMapping(path = "/schedule")
public class ScheduleDisciplineController {

	@Autowired
	private IScheduleDisciplineService scheduleDisciplineService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ScheduleDisciplineDto save(@RequestBody @Valid ScheduleDisciplineForm form) {
		return ScheduleDisciplineDto.convertTo(scheduleDisciplineService.save(form));
	}
}
