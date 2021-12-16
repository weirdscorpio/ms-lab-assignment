package com.example.microservices.CitizenService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
	
	@Id
	private int id;
	
	@Column
	private String name;

  @Column
  private String phonenumber;
	
	@Column(name = "vaccinationCenterId")
	private int vaccinationCenterId;
	

}
