package br.com.schoolcalendar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.forms.ScheduleDisciplineForm;
import br.com.schoolcalendar.interfaces.IScheduleDisciplineService;
import br.com.schoolcalendar.models.ScheduleDiscipline;
import br.com.schoolcalendar.repository.ScheduleDisciplineRepository;
import br.com.schoolcalendar.validator.ScheduleDisciplineValidator;

@Service
public class ScheduleDisciplineService implements IScheduleDisciplineService{

	@Autowired
	private ScheduleDisciplineRepository scheduleDisciplineRepository;
	
	@Autowired
	private ScheduleDisciplineValidator scheduleDisciplineValidator;
	
	@Override
	public ScheduleDiscipline save(@Valid ScheduleDisciplineForm form) {
		ScheduleDiscipline scheduleDiscipline = form.toScheduleDiscipline();
		scheduleDisciplineValidator.validator(form, scheduleDiscipline);
		return scheduleDisciplineRepository.save(scheduleDiscipline);
	}

}
