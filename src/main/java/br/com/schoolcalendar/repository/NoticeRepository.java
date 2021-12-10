package br.com.schoolcalendar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.enums.NoticeStatus;
import br.com.schoolcalendar.models.Notice;
import br.com.schoolcalendar.models.Student;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	List<Notice> findByStudentAndStatus(Student student, NoticeStatus status);
	Optional<Notice> findByIdAndStudent(Long id, Student student);
}
