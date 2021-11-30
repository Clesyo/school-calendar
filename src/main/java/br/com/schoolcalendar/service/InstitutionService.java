package br.com.schoolcalendar.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.forms.InstitutionForm;
import br.com.schoolcalendar.interfaces.IInstitutionService;
import br.com.schoolcalendar.models.Address;
import br.com.schoolcalendar.models.City;
import br.com.schoolcalendar.models.Institution;
import br.com.schoolcalendar.repository.CityRepository;
import br.com.schoolcalendar.repository.InstitutionRepository;
import br.com.schoolcalendar.validator.InstitutionValidator;

@Service
public class InstitutionService implements IInstitutionService {

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private InstitutionValidator institutionValidator;

	@Override
	public List<Institution> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Institution> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Institution> findByPublicId(String publicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Institution save(@Valid InstitutionForm form) {
		institutionValidator.validator(form);
		Institution institution = form.toInstitution();
		createAddressFromInstitution(form, institution);

		Optional<Institution> institutionExist = institutionRepository.findByCnpj(form.getCnpj());
		if (institutionExist.isPresent())
			return institutionExist.get();
		
		return institutionRepository.save(institution);
	}

	@Override
	public Institution update(Long id, InstitutionForm from) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createAddressFromInstitution(InstitutionForm form, Institution institution) {
		City city = cityRepository.findByIbgeCode(form.getIbgeCode())
				.orElseThrow(() -> new EntityNotFoundException("Cidade não encontada para os dados informados."));

		Address address = new Address();
		if (institution.getAddress() != null)
			address = institution.getAddress();

		address.setZipCode(form.getZipCode());
		address.setStreet(form.getStreet());
		address.setNumber(form.getNumber());
		address.setComplement(form.getComplement());
		address.setDistrict(form.getDistrict());
		address.setCity(city);
		address.setState(city.getState());
		institution.setAddress(address);
	}
}
