package br.com.schoolcalendar.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.schoolcalendar.forms.TeacherForm;
import br.com.schoolcalendar.models.Teacher;

public interface ITeacherService {

	Page<Teacher> find(Optional<String> filter, Pageable pageable);
	Teacher save(TeacherForm form);
	Teacher findById(Long id);
	Teacher findByPublicId(String publicId);
	Teacher update(Long id, TeacherForm form);
	void delete(Long id);
}
