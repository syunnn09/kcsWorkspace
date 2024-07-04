package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import com.example.demo.consts.Const.TimecardStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timecard implements Persistable<String> {

	@Id
	private String userid;
	private String status;

//	@OneToOne
//	@JoinColumn(name="userid")
//	private User user;

	@Transient
	private boolean isNew = false;

	@Override
	public String getId() {
		return userid;
	}

	public String getStatusString() {
		return TimecardStatus.getStatus(status).getStatusString();
	}

//	public String getUserName() {
//		return this.user.getUsername();
//	}
}
