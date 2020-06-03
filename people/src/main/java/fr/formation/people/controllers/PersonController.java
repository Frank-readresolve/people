package fr.formation.people.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.people.dtos.PersonDto;
import fr.formation.people.dtos.PersonCreateDto;
import fr.formation.people.services.PersonService;

// Définir un ensemble de endpoints
// pour un type de ressources (Person)

@RestController // On dit à Spring: ceci est un controller, tiens en compte
@RequestMapping("/persons") // Correspondance url/code
// http://localhost:8082/api/persons
@CrossOrigin("*")
public class PersonController {

	private final PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}

	// GET http://localhost:8082/api/persons/123
	// /{id} = path variable, variable de chemin
	// /123 = récupérer dans id grace qu mapping "@PathVariable"
	@GetMapping("/{id}")
	public PersonDto get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@DeleteMapping("/{id}") // DELETE "/api/persons/1"
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

	// @RequestBody = extraire et convertir le JSON du corps (body)
	// de la requete HTTP vers une instance de PersonCreateDto
	// @Valid : cascader la validation sur le DTO, permet si besoin
	// de ne pas valider un meme DTO
	@PostMapping // POST "/api/persons"
	public void create(@RequestBody @Valid PersonCreateDto dto) {
		service.create(dto);
	}

	@GetMapping // GET "/api/persons" pas d'id, retourne toute la collection de ressources
	public List<PersonDto> getAll() {
		return service.getAll();
	}

}
