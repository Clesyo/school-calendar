package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.ScheduleDiscipline;

public interface ScheduleDisciplineRepository extends JpaRepository<ScheduleDiscipline, Long> {

}
