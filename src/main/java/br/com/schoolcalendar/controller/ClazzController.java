package br.com.schoolcalendar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.dtos.ClazzDto;
import br.com.schoolcalendar.forms.ClazzForm;
import br.com.schoolcalendar.interfaces.IClazzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/clazz")
@Api(tags = "Class")
public class ClazzController {

	@Autowired
	private IClazzService clazzService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva uma nova turma")
	public ClazzDto save(@RequestBody @Valid ClazzForm form) {
		return ClazzDto.convertTo(clazzService.save(form));
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation("Retorna uma lista de turmas")
	public List<ClazzDto> findAll(){
		return ClazzDto.convertTo(clazzService.findAll());
	}
}
