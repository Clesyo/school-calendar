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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/notice")
@Api(tags = "Notice")
public class NoticeController {

	@Autowired
	private INoticeService noticeService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Cria uma nova Noticia")
	@ApiResponse(code = 400, message = "Erro de validação")
	public NoticeDto save(@RequestBody @Valid NoticeForm form) {
		return NoticeDto.convertoTo(noticeService.save(form));
	}

	@GetMapping("/student/{cpf}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation("Retorna todas as noticias de um aluno que ainda não foram visualizada")
	public NoticeDetailDto findByNoticeByCpfStudent(@PathVariable String cpf) {
		return noticeService.findByNoticeByCpfStudent(cpf);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation("Busca uma noticia e muda seu status para visualizado.")
	@ApiResponses({ @ApiResponse(code = 401, message = "Noticia não encontrada"),
			@ApiResponse(code = 200, message = "Noticia visualizada com sucesso.") })
	public NoticeDto visualized(@PathVariable Long id, @RequestHeader String accessKey) {
		return NoticeDto.convertoTo(noticeService.getNotice(id, accessKey));
	}
}
