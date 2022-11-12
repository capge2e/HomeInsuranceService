package com.abc.insurance.service;

import java.util.List;



import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;
import com.abc.insurance.repository.HomeInsuranceRepository;



@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService {
	@Autowired
	HomeInsuranceRepository homeInsuranceRepository;

	@Override
	public HomeInsurance insertHomeInsurance(HomeInsurance homeInsurance)throws Exception {
		
		HomeInsurance savedHomeInsurance =  homeInsuranceRepository.save(homeInsurance);  // Note :  save() is already implemented by Spring Data JPA
		if(savedHomeInsurance != null)
		{
			return savedHomeInsurance;
		}
		else return null;
	}

	@Override
	public List<HomeInsurance> getAllHomeInsurance() throws Exception {

		List<HomeInsurance> allInsurance = homeInsuranceRepository.findAll(); // Note : same as save
		return allInsurance;
	}

	@Override
	public HomeInsurance getHomeInsuranceBySumInsured(int sumInsured) throws Exception {
		return  homeInsuranceRepository.getHomeInsuranceBySumInsured(sumInsured);
	}

	@Override
	public List<HomeInsurance> getHomeInsuranceByPremium(int premium) throws Exception {
	
		return homeInsuranceRepository.getHomeInsuranceByPremium(premium);
	}

	@Override
	public HomeInsurance updateHomeInsurance(HomeInsurance homeInsurance) throws Exception{
		return homeInsuranceRepository.save(homeInsurance);
	}

	@Override
	public HomeInsurance getHomeInsuranceByInsuranceName(String insuranceName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInsuranceByHId(int hId) throws Exception {
		 homeInsuranceRepository.deleteById(hId);
		
	}

	@Override
	@Transactional
	public HomeInsurance linkHomePolicy(HomePolicies homePolicies, HomeInsurance homeInsurance) {
		homeInsurance.setHomePolicies(homePolicies);
		return homeInsurance;
	}



	

	

}//end of class
