package br.com.schoolcalendar.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Page<Student> findBySearchQuery(Optional<String> searchQuery, Pageable pageable);
	Optional<Student> findByRegistration(String registration);
	
	Optional<Student> findByCpf(String cpf);
	Optional<Student> findByCpfAndEmail(String cpf, String email);
}
