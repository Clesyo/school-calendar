package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.State;

public interface StateRepository extends JpaRepository<State, Long>{

}
