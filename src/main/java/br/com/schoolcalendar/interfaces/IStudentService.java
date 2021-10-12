package br.com.schoolcalendar.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.schoolcalendar.forms.StudentForm;
import br.com.schoolcalendar.models.Student;

public interface IStudentService {

	Page<Student> find(Optional<String> filter, Pageable pageable);
	Student save(StudentForm form);
	Student findById(Long id);
	Student findByPublicId(String publicId);
	Student update(Long id, StudentForm form);
	void delete(Long id);
}
