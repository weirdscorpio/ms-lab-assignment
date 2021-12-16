package com.example.microservices.VaccinationCenter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.expression.spel.ast.TypeReference;
import com.example.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.example.microservices.VaccinationCenter.Model.Citizen;
import com.example.microservices.VaccinationCenter.Model.RequiredResponse;
import com.example.microservices.VaccinationCenter.Repos.CenterRepo;



@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {
	
	@Autowired
	private CenterRepo centerRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(path ="/add")
	public ResponseEntity<VaccinationCenter> addCenter(@RequestBody VaccinationCenter vaccinationCenter) {
		
		VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
		return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}/getCenterDetails")
	public ResponseEntity<RequiredResponse> getAllDadaBasedonCenterId(@PathVariable Integer id){
		RequiredResponse requiredResponse =  new RequiredResponse();
		//1st get vaccination center detail
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		
		// then get all citizen registerd to vaccination center1
		
		java.util.List<Citizen> listOfCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/find/"+id, List.class);
		requiredResponse.setCitizens(listOfCitizens);
		return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
	}
	
	@PutMapping(path ="/update/{id}")
	public ResponseEntity<VaccinationCenter> updateCenter(@RequestBody VaccinationCenter vaccinationCenter) {
		VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
		return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
	}
	
  @DeleteMapping(path ="/delete/{id}")
  public String deleteCenter(@RequestBody VaccinationCenter vaccinationCenter, @PathVariable int id) {
		centerRepo.deleteById(id);
    Citizen[] listOfCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/find/"+id, Citizen[].class);

    // listOfCitizens.forEach(((element) -> {
    //   restTemplate.delete("http://CITIZEN-SERVICE/citizen/delete/"+element.id);
    // }));

    for (int i = 0; i<listOfCitizens.length; i++) {
      Citizen element = listOfCitizens[i];
      restTemplate.delete("http://CITIZEN-SERVICE/citizen/delete/"+element.id);
    }
		return "Center deleted";
	}
	

}
