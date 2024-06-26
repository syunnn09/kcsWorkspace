package com.example.demo.form;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDetail {

	public WorkDetail(WorkForm form, int i, int workid) {
		this.workid = workid;
		this.num = i;
		this.start = form.getStart(i);
		this.end = form.getEnd(i);
		this.detail = form.getDetail(i);
		this.progress = form.getProgress(i);
		this.remarks = form.getRemarks(i);
	}

	@Id
	private int id;
	private int workid;
	private int num;
	private String start;
	private String end;
	private String detail;
	private String progress;
	private String remarks;
}
