package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline, Long>{

}
