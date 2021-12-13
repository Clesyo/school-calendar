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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Student")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um novo aluno")
	@ApiResponses({ @ApiResponse(code = 201, message = "Aluno salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public StudentDto save(@RequestBody @Valid StudentForm form) {
		return StudentDto.convertToDto(studentService.save(form));
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um aluno pelo código Id")
	@ApiResponses({ @ApiResponse(code = 404, message = "Aluno não encontrado para o Id informado.") })
	public StudentDto findById(@PathVariable Long id) {
		return StudentDto.convertToDto(studentService.findById(id));
	}

	@GetMapping("/public/{publicId}")
	@ApiOperation("Busca um aluno pelo código publicId")
	@ApiResponses({ @ApiResponse(code = 404, message = "Aluno não encontrado para o publicId informado.") })
	public StudentDto findByPublicId(@PathVariable String publicId) {
		return StudentDto.convertToDto(studentService.findByPublicId(publicId));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Atualiza os dados de um aluno do seu cadastro.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Aluno alterado com sucesso."),
			@ApiResponse(code = 404, message = "Aluno não encontrado para o Id informado."),
			@ApiResponse(code = 400, message = "Erro de validação.") })
	public StudentDto update(@PathVariable Long id, @RequestBody StudentForm form) {
		return StudentDto.convertToDto(studentService.update(id, form));
	}

	@GetMapping
	@ApiOperation("Busca um aluno pelo filtro genêrico")
	public Page<StudentDto> find(@RequestParam  @ApiParam("Filtro(matricula,cpf,nome)") Optional<String> filter, Pageable pageable) {
		return StudentDto.convertTo(studentService.find(filter, pageable));
	}
}
