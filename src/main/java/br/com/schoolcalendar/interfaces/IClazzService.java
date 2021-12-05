package br.com.schoolcalendar.interfaces;

import java.util.List;

import br.com.schoolcalendar.forms.ClazzForm;
import br.com.schoolcalendar.models.Clazz;

public interface IClazzService {

	Clazz save(ClazzForm form);
	
	List<Clazz> findAll();
}
