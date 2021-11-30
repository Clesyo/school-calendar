package br.com.schoolcalendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long>{

	Optional<Institution> findByCnpj(String cnpj);

}
