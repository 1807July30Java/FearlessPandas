package com.revature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_CONDITION")
public class BookCondition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookConditionSequence")
	@SequenceGenerator(allocationSize = 1, name = "bookConditionSequence", sequenceName = "SQ_BOOK_CONDITION_PK")
	@Column(name = "CONDITION_ID")
	private int conditionId;
	@Column(name = "NAME")
	private String name;
/************************************************************************************************************/
	//Constructors

public BookCondition(int conditionId, String name) {
	super();
	this.conditionId = conditionId;
	this.name = name;
}
public BookCondition() {
	super();
	// TODO Auto-generated constructor stub
}
/************************************************************************************************************/
//Getters and Setters

public int getConditionId() {
	return conditionId;
}
public void setConditionId(int conditionId) {
	this.conditionId = conditionId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


	

}
