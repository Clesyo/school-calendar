package br.com.schoolcalendar.validator;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.exception.InvalidException;
import br.com.schoolcalendar.forms.StudentForm;
import br.com.schoolcalendar.models.Student;
import br.com.schoolcalendar.repository.AccountableRepository;
import br.com.schoolcalendar.utils.Utils;

@Service
public class AccountableValidator {

	@Autowired
	private AccountableRepository accountableRepository;

	public void validate(StudentForm form, Student student) {

		if (!Utils.isCpfValido(form.getCpf())) {
			throw new InvalidException("CPF", "O CPF informado é inválido.");
		}

		if (!StringUtils.isEmpty(form.getPhone()) && !Utils.isPhoneNumberValid(form.getPhone())) {
			throw new InvalidException("Telefone", "Telefone inválido");
		}

		form.getAccountables().forEach(accForm -> {
			accountableRepository.findByCpfAndStudent(accForm.getCpf(), student).ifPresent(acc -> {
				throw new EntityNotFoundException("Responsável já associado ao Aluno.");
			});
		});
		
		if(form.getAccountables().isEmpty()) {
			throw new InvalidException("É necessário informar no mínimo um responsável por aluno.");
		}

	}
}
