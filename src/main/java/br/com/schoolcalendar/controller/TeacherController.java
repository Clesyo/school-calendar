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

import br.com.schoolcalendar.dtos.TeacherDto;
import br.com.schoolcalendar.forms.TeacherForm;
import br.com.schoolcalendar.interfaces.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/teacher", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Teacher")
public class TeacherController {

	@Autowired
	private ITeacherService teacherService;
	
	@GetMapping("/{id}")
	@ApiOperation("Busca um professo pelo código Id")
	@ApiResponses({ @ApiResponse(code = 404, message = "Professor não encontrado para o Id informado.") })
	public TeacherDto findById(@PathVariable Long id) {
		return TeacherDto.convertTo(teacherService.findById(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um novo professor")
	@ApiResponses({ @ApiResponse(code = 201, message = "Professor salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public TeacherDto save(@Valid @RequestBody TeacherForm form) {
		return TeacherDto.convertTo(teacherService.save(form));
	}
	
	@GetMapping("/public/{publicId}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation("Busca um professor pelo código publicId")
	@ApiResponses({ @ApiResponse(code = 404, message = "Professor não encontrado para o publicId informado.") })
	public TeacherDto findByPublicId(@PathVariable String publicId) {
		return TeacherDto.convertTo(teacherService.findByPublicId(publicId));
	}
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation("Busca um aluno pelo filtro genêrico")
	public Page<TeacherDto> find(@RequestParam Optional<String> filter, Pageable pageable) {
		return TeacherDto.convertTo(teacherService.find(filter, pageable));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Atualiza os dados de um professor do seu cadastro.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Professor alterado com sucesso."),
			@ApiResponse(code = 404, message = "Professor não encontrado para o Id informado."),
			@ApiResponse(code = 400, message = "Erro de validação.") })
	public TeacherDto udpate(@PathVariable Long id, @Valid @RequestBody TeacherForm form) {
		return TeacherDto.convertTo(teacherService.update(id, form));
	}
}
