package br.com.schoolcalendar.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	Optional<Teacher> findByCpf(String cpf);
	Optional<Teacher> findByPublicId(String publicId);
	Page<Teacher> findBySearchQueryContains(Optional<String> filter, Pageable pageable);
	Optional<Teacher> findByCpfAndIdNot(String cpf, Long id);
}
