package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReserveDetail {

	@Id
	private int id;
	private int reserveId;
	private String date;
	private int hour;

//	@ManyToOne
//	FacilityReserve reserve;
}
