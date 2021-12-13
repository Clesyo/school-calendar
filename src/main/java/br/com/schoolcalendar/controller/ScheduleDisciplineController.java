package br.com.schoolcalendar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.dtos.ScheduleDisciplineDto;
import br.com.schoolcalendar.dtos.ScheduleWeekdayDto;
import br.com.schoolcalendar.forms.ScheduleDisciplineForm;
import br.com.schoolcalendar.interfaces.IScheduleDisciplineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/schedule")
@Api(tags = "ScheduleDiscipline")
public class ScheduleDisciplineController {

	@Autowired
	private IScheduleDisciplineService scheduleDisciplineService;

	@PostMapping
	@ResponseStatus(code = CREATED)
	@ApiOperation("Cria um horario associando a turma á discipliam, e ao dia da semana.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Horário criado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public ScheduleDisciplineDto save(@RequestBody @Valid ScheduleDisciplineForm form) {
		return ScheduleDisciplineDto.convertTo(scheduleDisciplineService.save(form));
	}

	@GetMapping("/clazz/{id}")
	@ResponseStatus(code = OK)
	@ApiOperation("Retorna a lista de todas as disciplinas de um turma buscando pelo seu ID")
	@ApiResponse(code = 401, message = "Horários não encontrados")
	public List<ScheduleDisciplineDto> findByClazzId(@PathVariable Long id) {
		return ScheduleDisciplineDto.convertTo(scheduleDisciplineService.findByIdClazz(id));
	}

	@GetMapping("/weekday/{id}")
	@ResponseStatus(code = OK)
	@ApiOperation("Retorna o horário semanal de um turma especifica,")
	@ApiResponse(code = 401, message = "Turmca não encontrados")
	public ScheduleWeekdayDto buildScheduleWeekday(@PathVariable Long id) {
		return ScheduleWeekdayDto.buildScheduleWeekday(scheduleDisciplineService.buildScheduleWeekday(id));
	}
}
