package br.com.schoolcalendar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.exception.dtos.InstitutionDto;
import br.com.schoolcalendar.forms.InstitutionForm;
import br.com.schoolcalendar.interfaces.IInstitutionService;

@RestController
@RequestMapping(path = "/institution", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstitutionController {

	@Autowired
	private IInstitutionService institutionService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public InstitutionDto save(@RequestBody @Valid InstitutionForm form) {
		return InstitutionDto.convertTo(institutionService.save(form));
	}
}
