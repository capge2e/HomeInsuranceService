package com.abc.insurance.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Admin")
public class HomeInsurance {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int hId;
private String insuranceName;
private int premium;
private int sumInsured;
private String isCoveredBelongings;
private  int maxbelongingsAmount;
private  String isCoveredHomeStructure;
private int  homeStructureAmount;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "homepolicies")
private HomePolicies homePolicies;


public HomeInsurance(String insuranceName, int premium, int sumInsured,String isCoveredBelongings,
		int maxbelongingsAmount,String isCoveredHomeStructure, int homeStructureAmount) {
	super();
	this.insuranceName = insuranceName;
	this.premium = premium;
	this.sumInsured= sumInsured;
	this.isCoveredBelongings = isCoveredBelongings;
	this.maxbelongingsAmount = maxbelongingsAmount;
	this.isCoveredHomeStructure = isCoveredHomeStructure;
	this.homeStructureAmount = homeStructureAmount;
}//end of constructor
}//end of class

