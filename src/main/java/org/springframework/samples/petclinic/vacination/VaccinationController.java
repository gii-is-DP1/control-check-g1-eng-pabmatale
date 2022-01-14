package org.springframework.samples.petclinic.vacination;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaccinationController {
	
    private final VaccinationService vaccinationService;
    private final PetService petService;
	
    @Autowired
	public VaccinationController(VaccinationService vaccinationService, PetService petService) {
		super();
		this.vaccinationService = vaccinationService;
		this.petService = petService;
	}



	@GetMapping(value = "/vaccination/create")
	public String initCreationForm(Map<String, Object> model) {
		List<Vaccine> vaccines = this.vaccinationService.getAllVaccines();
		Collection<Pet> pets = this.petService.findAllPets();
		Vaccination vaccination = new Vaccination();
		model.put("vaccination", vaccination);
		model.put("vaccines", vaccines);
		model.put("pets", pets);
		return "vaccination/createOrUpdateVaccinationForm";
	}
}
