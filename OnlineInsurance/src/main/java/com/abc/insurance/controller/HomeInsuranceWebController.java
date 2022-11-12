package com.abc.insurance.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.service.HomeInsuranceService;



@RestController
@RequestMapping("/safeInsurance/admin/homeInsurance")
public class HomeInsuranceWebController {
	@Autowired
	HomeInsuranceService homeInsuranceService;
	
	
	public  HomeInsuranceWebController() {
		System.out.println("\n\n\n====>> Inside Constructor "+this);
	}
	
	@GetMapping("/welcome")
	public String welcome()
	{
		
		return "Welcome to Home Insurance";
		
	}
	
	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
		
	@PostMapping("/addHomeInsurance")
	public ResponseEntity<String> addHomeInsurance(@RequestBody HomeInsurance homeInsurance) 
	{
		
		try {
			System.out.println(" --- > "+mylogs);
			mylogs.info("---->>>Inside try of adding home insurance");
			HomeInsurance savedInsurance =   homeInsuranceService.insertHomeInsurance(homeInsurance);
			String responseMsg = savedInsurance.getInsuranceName()+  "   save with premium:   "+savedInsurance.getPremium();
			return new ResponseEntity<String>(responseMsg,HttpStatus.OK);
		} catch (Exception e) {
			String errorMsg =  " Please, Contact to customer care 1800-250-960 or mail us :- care@gmail.com";
			return new ResponseEntity<String>(errorMsg,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/viewHomeInsurance")
	public List<HomeInsurance> viewAllInsurance()
	{

		try {
			List<HomeInsurance>  allExtractedInsurance =homeInsuranceService.getAllHomeInsurance();
			
			return allExtractedInsurance;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}
	@GetMapping("/sumInsured/{sumInsured}")
	public HomeInsurance getHomeInsuranceBySumInsured(@PathVariable int sumInsured)throws Exception
	{
		
		return homeInsuranceService.getHomeInsuranceBySumInsured(sumInsured);
		
	}
	
	@GetMapping("/premium/{premium}")
	public List<HomeInsurance> getHomeInsuranceByPremiumamount(@PathVariable  int premium) throws Exception
	{
		
		return  homeInsuranceService.getHomeInsuranceByPremium(premium);
		
	}
	
	
    @GetMapping("/insuranceName/{name}")
    public HomeInsurance getHomeInsuranceByInsuranceName(@PathVariable String insuranceName) throws Exception
    {
    	return homeInsuranceService.getHomeInsuranceByInsuranceName(insuranceName);
    }
    @PutMapping("/updateHomeInsurance")
	public HomeInsurance updateHomeInsurance(@RequestBody HomeInsurance homeInsurance)throws Exception
	{
		
		return homeInsuranceService.updateHomeInsurance(homeInsurance);
		
		
	}
    @DeleteMapping("/deleteHomeInsurance")
    public String deleteInsurance(@RequestParam int hId) throws Exception
    {
    	homeInsuranceService.deleteInsuranceByHId(hId);
    	return "Deleted id =" +hId+ "Data";
    }
    
}//end of class

