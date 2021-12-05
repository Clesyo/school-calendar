package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Clazz;

public interface ClazzRepository extends JpaRepository<Clazz, Long>{

}
