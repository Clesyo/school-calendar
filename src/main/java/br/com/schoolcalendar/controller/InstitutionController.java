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

import br.com.schoolcalendar.dtos.InstitutionDto;
import br.com.schoolcalendar.forms.InstitutionForm;
import br.com.schoolcalendar.interfaces.IInstitutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/institution", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Institution")
public class InstitutionController {

	@Autowired
	private IInstitutionService institutionService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva uma nova Instituição")
	public InstitutionDto save(@RequestBody @Valid InstitutionForm form) {
		return InstitutionDto.convertTo(institutionService.save(form));
	}
}
