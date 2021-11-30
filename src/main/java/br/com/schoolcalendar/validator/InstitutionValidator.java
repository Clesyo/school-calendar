package br.com.schoolcalendar.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.exception.InvalidException;
import br.com.schoolcalendar.forms.InstitutionForm;
import br.com.schoolcalendar.repository.InstitutionRepository;
import br.com.schoolcalendar.utils.Utils;

@Service
public class InstitutionValidator {

	@Autowired
	private InstitutionRepository institutionRepository;

	public void validator(InstitutionForm form) {
		if (!StringUtils.isEmpty(form.getPhone()) && !Utils.isPhoneNumberValid(form.getPhone())) {
			throw new InvalidException("Telefone", "Telefone inválido");
		}

		if (!StringUtils.isEmpty(form.getCnpj()) && !Utils.isCnpjValido(form.getCnpj())) {
			throw new InvalidException("CNPJ", "CNPJ inválido");
		}

		institutionRepository.findByCnpj(form.getCnpj()).ifPresent(institution -> {
			throw new InvalidException("Já existe uma Instituição com CNPJ informado.");
		});
	}

}
