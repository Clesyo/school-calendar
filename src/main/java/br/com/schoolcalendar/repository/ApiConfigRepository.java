package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.ApiConfig;

public interface ApiConfigRepository extends JpaRepository<ApiConfig, Long> {

}
