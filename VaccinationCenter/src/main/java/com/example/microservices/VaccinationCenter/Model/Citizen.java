package com.example.microservices.VaccinationCenter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
	
	public int id;
	
	private String name;

  private String phonenumber;
	
	private int vaccinationCenterId;
	

}
