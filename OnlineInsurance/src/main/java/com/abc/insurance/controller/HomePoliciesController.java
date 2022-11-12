package com.abc.insurance.controller;




import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.dto.ErrorDTO;
import com.abc.insurance.dto.MyDTO;
import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;
import com.abc.insurance.service.HomeInsuranceService;
import com.abc.insurance.service.HomePoliciesService;
import com.abc.insurance.util.HomeInsuranceDTOConvertor;


import lombok.Value;


@RestController
@RequestMapping("/policy/client/homePolicies")
@Validated
public class HomePoliciesController {
	@Autowired
	HomePoliciesService homePoliciesService;
	
	@Autowired
	HomeInsuranceService homeInsuranceService;
	
	@Autowired
	HomeInsuranceDTOConvertor dtoConvertor;
private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
	
	@PostMapping("/addpolicies")  // ....../localhost:8009/policy/client/homePolicies/addpolicies?insuranceName=Fire Insurance
	public ResponseEntity<MyDTO> doHomePoliciesThings(@RequestBody @Valid HomePolicies homePolicies,@RequestParam String insuranceName)
	{
		HomeInsurance alreadySavedInsurance = null;
		try
		{
			System.out.println(" --- > "+mylogs);
			mylogs.info("---->>>Inside try of doHomePolicies Things");
			HomePolicies  savedPolicy = homePoliciesService.addHomePolicies(homePolicies);
			if(savedPolicy .getHomePolicyId()!= 0)
			{
				mylogs.info("---->>>Inside if get home policy");
				alreadySavedInsurance  = homeInsuranceService.getHomeInsuranceByInsuranceName(insuranceName);
				if(alreadySavedInsurance!= null)
				{ 
					mylogs.info("---->>>Inside if alreadySavedInsurance not equal to null");
					HomeInsurance homePolicyAddInsurance = homeInsuranceService.linkHomePolicy(homePolicies, alreadySavedInsurance);
					
					DefaultResponseDTO dtoResponse = dtoConvertor.getHomeInsuranceDefaultDTO(homePolicyAddInsurance);
					
					return new ResponseEntity<>(HttpStatus.OK);
					
				}
				else
				{
					mylogs.error("Insurance not found in post mapping uri : add");
					throw new Exception("Insurance not found ,  "+alreadySavedInsurance+" for "+insuranceName);
					//"errorMsg": "Insurance not found ,  null for Fire Insurance"
				}
				
			}
		}
		catch (Exception e) {
			System.out.println(e);
			ErrorDTO errorDTo = new ErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorDTo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null;
		
	
	}

	@GetMapping("/policiesByPolicyNo")
	public List<HomePolicies> policiesBetweenPolicyNo(@RequestParam int r1 , @RequestParam int r2)throws Exception
	{
		
		return homePoliciesService.getHomePoliciesBetweenHomePolicyNo(r1, r2);
	}	
	
	@GetMapping("/policies/{searchClientName}")
	public HomePolicies getByClientName(@PathVariable String clientName)throws Exception
	{
		return homePoliciesService.getHomePoliciesByClientName(clientName);
	}
	@GetMapping("/isClaimed/{claimedDate}")
	public List<HomePolicies> getPoliciesOnClaimedDate(@PathVariable String isClaimed,@RequestParam String claimedDate)throws Exception
	{
		if(claimedDate != null)
		{
			return homePoliciesService.getHomePoliciesBasedOnisClaimedDate(isClaimed, claimedDate);
		}
		else return null;
	}
	
	
}


