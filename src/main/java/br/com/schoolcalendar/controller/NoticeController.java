package br.com.schoolcalendar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.dtos.NoticeDetailDto;
import br.com.schoolcalendar.dtos.NoticeDto;
import br.com.schoolcalendar.forms.NoticeForm;
import br.com.schoolcalendar.interfaces.INoticeService;

@RestController
@RequestMapping(path = "/notice")
public class NoticeController {
	
	@Autowired
	private INoticeService noticeService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public NoticeDto save(@RequestBody @Valid NoticeForm form) {
		return NoticeDto.convertoTo(noticeService.save(form));
	}
	
	@GetMapping("/student/{cpf}")
	@ResponseStatus(code = HttpStatus.OK)
	public NoticeDetailDto findByNoticeByCpfStudent(@PathVariable String cpf){
		return noticeService.findByNoticeByCpfStudent(cpf);
	}
	
	@GetMapping("/{id}")
	public NoticeDto visualized(@PathVariable Long id, @RequestHeader String accessKey) {
		return NoticeDto.convertoTo(noticeService.getNotice(id, accessKey));
	}
}
