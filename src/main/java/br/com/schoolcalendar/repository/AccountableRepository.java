package br.com.schoolcalendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Accountable;
import br.com.schoolcalendar.models.Student;

public interface AccountableRepository extends JpaRepository<Accountable, Long>{

	Optional<Accountable> findByCpfAndStudent(String cpf, Student student);

	Optional<Student> findByCpf(String cpf);
}
