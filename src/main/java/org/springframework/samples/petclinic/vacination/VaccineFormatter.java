package org.springframework.samples.petclinic.vacination;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class VaccineFormatter implements Formatter<Vaccine>{
	
	private final VaccinationService vaccinationService;
	
	@Autowired
	public VaccineFormatter(VaccinationService vaccinationService) {
		super();
		this.vaccinationService = vaccinationService;
	}

	@Override
    public String print(Vaccine object, Locale locale) {
        // TODO Auto-generated method stub
        return object.name;
    }

    @Override
    public Vaccine parse(String text, Locale locale) throws ParseException {
        // TODO Auto-generated method stub
    	Vaccine v = this.vaccinationService.getVaccine(text);
    	if(v!=null) {
    		return v;
    	}else {
    		throw new ParseException("Vaccine not found: " + text, 0);
    	}
    }
    
}
