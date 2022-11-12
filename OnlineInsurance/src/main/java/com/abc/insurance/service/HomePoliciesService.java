package com.abc.insurance.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.abc.insurance.entity.HomePolicies;


@Service
public interface HomePoliciesService {
	public HomePolicies addHomePolicies(HomePolicies homePolicies)throws Exception;
	public List<HomePolicies> viewPoliciesHistory(HomePolicies homePolicies)throws Exception;
	public List<HomePolicies> getHomePoliciesBetweenHomePolicyNo(@Param("range1") int range1,@Param("range2") int range2)throws Exception;
	public HomePolicies getHomePoliciesByClientName(String clientName)throws Exception;
	public List<HomePolicies> getHomePoliciesBasedOnisClaimedDate(String isClaimed,String claimedDate)throws Exception;

}
