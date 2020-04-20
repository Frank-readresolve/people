package fr.formation.people.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.formation.people.dtos.AddressCreateDto;
import fr.formation.people.dtos.AddressDto;
import fr.formation.people.entities.Address;
import fr.formation.people.repositories.AddressJpaRepository;


// Dit a Spring cette classe est un service
// Spring scanne les packages et retient la classe
@Service 
public class AddressServiceImpl implements AddressService {
	
	private final AddressJpaRepository repository;
	
	public AddressServiceImpl(AddressJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void create(AddressCreateDto dto) {
		// Grace a la validation dans le controller
		// on fait confiance a la qualité des données.
		// Convertir AddressCreateDto en Address (entité)
		// En création pas l'id, l'id est attribué par la bdd
		Address address = new Address();
		address.setStreet(dto.getStreet()); // Copy street du dto vers l'entité
		address.setCity(dto.getCity());
		address.setZipCode(dto.getZipCode());
		address.setCountry(dto.getCountry());
		repository.save(address);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public AddressDto get(Long id) {
		Address address = repository.findById(id).get();
		AddressDto dto = new AddressDto();
		dto.setStreet(address.getStreet()); // Copy street de l'entité vers le DTO
		dto.setCity(address.getCity());
		dto.setZipCode(address.getZipCode());
		dto.setCountry(address.getCountry());
		return dto;
	}

	@Override
	public List<AddressDto> getAll() {
		List<Address> addresses = repository.findAll();
		List<AddressDto> result = new ArrayList<>();
		for (Address address : addresses) {
			AddressDto dto = new AddressDto();
			dto.setStreet(address.getStreet()); 
			dto.setCity(address.getCity());
			dto.setZipCode(address.getZipCode());
			dto.setCountry(address.getCountry());
			result.add(dto);
		}
		return result;
	}

}
