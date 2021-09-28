package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
