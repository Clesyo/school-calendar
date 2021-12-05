package br.com.schoolcalendar.interfaces;

import br.com.schoolcalendar.forms.ScheduleDisciplineForm;
import br.com.schoolcalendar.models.ScheduleDiscipline;

public interface IScheduleDisciplineService {

	ScheduleDiscipline save(ScheduleDisciplineForm form);
}
