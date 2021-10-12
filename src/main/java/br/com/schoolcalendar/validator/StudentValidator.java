package br.com.schoolcalendar.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.exception.InvalidException;
import br.com.schoolcalendar.forms.StudentForm;
import br.com.schoolcalendar.repository.StudentRepository;
import br.com.schoolcalendar.repository.UserRepository;
import br.com.schoolcalendar.utils.Utils;

@Service
public class StudentValidator {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;

	public void validate(StudentForm form) {

		if (!Utils.isCpfValido(form.getCpf())) {
			throw new InvalidException("CPF", "O CPF informado é inválido.");
		}

		if (!StringUtils.isEmpty(form.getPhone()) && !Utils.isPhoneNumberValid(form.getPhone())) {
			throw new InvalidException("Telefone", "Telefone inválido");
		}

		studentRepository.findByCpf(form.getCpf()).ifPresent(s -> {
			throw new InvalidException("Já existe um Aluno com CPF informado.");
		});
		
		userRepository.findByEmail(form.getCpf()).ifPresent(s -> {
			throw new InvalidException("Email já está sendo usado por outro usuário.");
		});
		
	}
}