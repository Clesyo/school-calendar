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

@RestController
@RequestMapping(path = "/schedule")
public class ScheduleDisciplineController {

	@Autowired
	private IScheduleDisciplineService scheduleDisciplineService;

	@PostMapping
	@ResponseStatus(code = CREATED)
	public ScheduleDisciplineDto save(@RequestBody @Valid ScheduleDisciplineForm form) {
		return ScheduleDisciplineDto.convertTo(scheduleDisciplineService.save(form));
	}

	@GetMapping("/clazz/{id}")
	@ResponseStatus(code = OK)
	public List<ScheduleDisciplineDto> findByClazzId(@PathVariable Long id) {
		return ScheduleDisciplineDto.convertTo(scheduleDisciplineService.findByIdClazz(id));
	}

	@GetMapping("/weekday/{id}")
	@ResponseStatus(code = OK)
	public ScheduleWeekdayDto buildScheduleWeekday(@PathVariable Long id) {
		return ScheduleWeekdayDto.buildScheduleWeekday(scheduleDisciplineService.buildScheduleWeekday(id));
	}
}
