package br.com.schoolcalendar.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.forms.NoticeForm;
import br.com.schoolcalendar.interfaces.INoticeService;
import br.com.schoolcalendar.models.Notice;
import br.com.schoolcalendar.models.Student;
import br.com.schoolcalendar.repository.NoticeRepository;
import br.com.schoolcalendar.repository.StudentRepository;

@Service
public class NoticeService implements INoticeService {

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Notice save(NoticeForm form) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(form.getStudentId())
				.orElseThrow(() -> new EntityNotFoundException("Aluno não identificado para id informado."));
		Notice notice = form.toNotice();
		notice.setStudent(student);
		return noticeRepository.save(notice);
	}

}
