package br.com.schoolcalendar.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.dtos.NoticeDetailDto;
import br.com.schoolcalendar.enums.NoticeStatus;
import br.com.schoolcalendar.exception.InvalidException;
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
		notice.setStatus(NoticeStatus.CREATED);
		return noticeRepository.save(notice);
	}

	@Override
	public NoticeDetailDto findByNoticeByCpfStudent(String cpf) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException("Não existe um aluno para o CPF informado."));
		List<Notice> notices = noticeRepository.findByStudentAndStatus(student, NoticeStatus.CREATED);
		return NoticeDetailDto.convert(student, notices);
	}

	@Override
	public Notice getNotice(Long id, String accessKey) {

		Student student = studentRepository.findByPublicId(accessKey)
				.orElseThrow(() -> new InvalidException("AccessKey inválida"));

		Optional<Notice> noticeExist = noticeRepository.findByIdAndStudent(id, student);
		if (noticeExist.isPresent() && NoticeStatus.CREATED.name().equals(noticeExist.get().getStatus().name())) {
			Notice notice = noticeExist.get();
			notice.setStatus(NoticeStatus.VISUALIZED);
			noticeRepository.save(notice);
			return notice;
		}
		
		return noticeExist.get();
	}

}
