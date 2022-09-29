package be.abis.exercise.test;

import java.util.List;

import be.abis.exercise.model.Company;
import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReadCompanies {

	@Test
	void numberOfCompaniesInFileIs5(){
		
		CompanyRepository cr = new FileCompanyRepository();
		List<Company> comps = cr.getCompanies();

		assertEquals(5,comps.size());

	}

}
