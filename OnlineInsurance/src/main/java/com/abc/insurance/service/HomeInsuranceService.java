package com.abc.insurance.service;

import java.util.List;



import org.springframework.stereotype.Service;

import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;



@Service
public interface HomeInsuranceService {
public HomeInsurance insertHomeInsurance(HomeInsurance homeInsurance)throws Exception;
public List<HomeInsurance> getAllHomeInsurance()throws Exception;
public HomeInsurance getHomeInsuranceBySumInsured(int sumInsured) throws Exception;
public HomeInsurance getHomeInsuranceByInsuranceName(String insuranceName)throws Exception;
public List<HomeInsurance> getHomeInsuranceByPremium(int premium) throws Exception;
public HomeInsurance updateHomeInsurance(HomeInsurance homeInsurance)throws Exception;
public void deleteInsuranceByHId(int hId)throws Exception;
public HomeInsurance linkHomePolicy(HomePolicies homePolicies,HomeInsurance homeInsurance);


}
