package br.com.schoolcalendar.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.schoolcalendar.forms.InstitutionForm;
import br.com.schoolcalendar.models.Institution;

public interface IInstitutionService {

	List<Institution> findAll();

	Optional<Institution> findById(Long id);

	Optional<Institution> findByPublicId(String publicId);

	Institution save(InstitutionForm form);

	Institution update(Long id, InstitutionForm form);
}
