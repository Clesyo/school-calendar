package br.com.schoolcalendar.interfaces;

import java.util.List;

import br.com.schoolcalendar.forms.ScheduleDisciplineForm;
import br.com.schoolcalendar.models.Clazz;
import br.com.schoolcalendar.models.ScheduleDiscipline;

public interface IScheduleDisciplineService {

	ScheduleDiscipline save(ScheduleDisciplineForm form);
	
	List<ScheduleDiscipline> findByIdClazz(Long id);
	
	Clazz buildScheduleWeekday(Long id);
}
