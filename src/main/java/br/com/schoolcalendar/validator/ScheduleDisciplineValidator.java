package br.com.schoolcalendar.validator;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.forms.ScheduleDisciplineForm;
import br.com.schoolcalendar.models.Clazz;
import br.com.schoolcalendar.models.Discipline;
import br.com.schoolcalendar.models.ScheduleDiscipline;
import br.com.schoolcalendar.repository.ClazzRepository;
import br.com.schoolcalendar.repository.DisciplineRepository;

@Service
public class ScheduleDisciplineValidator {

	@Autowired
	private ClazzRepository clazzRepository;

	@Autowired
	private DisciplineRepository disciplineRepository;

	public void validator(ScheduleDisciplineForm form, ScheduleDiscipline scheduleDiscipline) {
		Clazz clazz = clazzRepository.findById(form.getClazzId())
				.orElseThrow(() -> new EntityNotFoundException("Turma não encontrada para Id informado."));

		Discipline discipline = disciplineRepository.findById(form.getDisciplineId())
				.orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada para Id informado."));
		
		scheduleDiscipline.setClazz(clazz);
		scheduleDiscipline.setDiscipline(discipline);
	}

}
