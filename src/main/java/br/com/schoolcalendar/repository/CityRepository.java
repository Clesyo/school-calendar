package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
