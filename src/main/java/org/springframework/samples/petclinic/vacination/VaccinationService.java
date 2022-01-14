package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccinationService {
	
	private final VaccinationRepository vaccinationRepository;
	
	@Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository) {
		super();
		this.vaccinationRepository = vaccinationRepository;
	}

	public List<Vaccination> getAll(){
        return this.vaccinationRepository.findAll();
    }
	
    public List<Vaccine> getAllVaccines(){
        return null;
    }

    public Vaccine getVaccine(String name) {
    	return this.vaccinationRepository.findVaccineByName(name);
    }

    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public void save(Vaccination p) throws UnfeasibleVaccinationException {
    	PetType vaccination = p.vaccinatedPet.getType();
    	PetType vaccine = p.vaccine.getPetType();
    	if(vaccination!=vaccine) {
    		throw new UnfeasibleVaccinationException();
        }else
            vaccinationRepository.save(p);  	     
    }

    
}
