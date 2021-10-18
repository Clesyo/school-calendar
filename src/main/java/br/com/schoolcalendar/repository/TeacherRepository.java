package br.com.schoolcalendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	Optional<Teacher> findByCpf(String cpf);
}
