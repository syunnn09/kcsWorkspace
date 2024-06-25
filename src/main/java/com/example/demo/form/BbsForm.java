package com.example.demo.form;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("bbs")
public class BbsForm implements Persistable<Integer> {

	@Id
	@Column("id")
	private int auto_id;

	private int thread_id;
	private String userid;
	private String text;

	@Override
	public Integer getId() {
		return auto_id;
	}

	@Override
	public boolean isNew() {
		return true;
	}
}
