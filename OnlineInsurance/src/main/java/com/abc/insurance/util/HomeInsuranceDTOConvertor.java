package com.abc.insurance.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.entity.HomeInsurance;



@Component
@Scope("singleton")
public class HomeInsuranceDTOConvertor {
	public static DefaultResponseDTO  getHomeInsuranceDefaultDTO(HomeInsurance homeInsurance)
	{
		DefaultResponseDTO dto = new DefaultResponseDTO(
				homeInsurance.getInsuranceName(), 
				homeInsurance.getHId(), 
				homeInsurance.getHomePolicies().getClientName(),
				                 "User Policies Created , policy Id : "+homeInsurance.getHomePolicies().getHomePolicyId());
		
		return dto;
	}
	
	
}
