package br.com.schoolcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.schoolcalendar.models.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

}
