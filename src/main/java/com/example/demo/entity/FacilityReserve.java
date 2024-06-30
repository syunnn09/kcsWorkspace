package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityReserve {

	@Id
	private int id;
	private int facilityId;
	private int departmentId;
	private String purpose;

//	@OneToMany(mappedBy="facility")
//	@JoinColumn(name="reserve_id")
//	private List<FacilityReserveDetail> details;
}
