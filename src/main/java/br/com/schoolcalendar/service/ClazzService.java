package br.com.schoolcalendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.forms.ClazzForm;
import br.com.schoolcalendar.interfaces.IClazzService;
import br.com.schoolcalendar.models.Clazz;
import br.com.schoolcalendar.repository.ClazzRepository;

@Service
public class ClazzService implements IClazzService {

	@Autowired
	private ClazzRepository clazzRepository;

	@Override
	public Clazz save(ClazzForm form) {
		Clazz clazz = form.toClazz();
		return clazzRepository.save(clazz);
	}

	@Override
	public List<Clazz> findAll() {
		// TODO Auto-generated method stub
		return clazzRepository.findAll();
	}

}
