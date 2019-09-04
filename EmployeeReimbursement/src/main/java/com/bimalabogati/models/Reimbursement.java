package com.bimalabogati.models;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Reimbursement implements Serializable{
	
	
	//This id is generated automatically 
	private static final long serialVersionUID = 1L;
	
	private Integer reimb_id;
	private Double reimb_amount;
	private	String reimb_submitted;
	private String reimb_resolved;
	private String reimb_description;
	//look into this later for the datatype changed it to InputStream ,this is stored as BLOB in database
	private InputStream reimb_receipt;
	
	private Integer reimb_author; //author has to be employee id 
	private Integer reimb_resolver; //resolver has to be the id of the employee 
	private Integer reimb_status_id;
	private Integer reimb_type_id;
	
	public Reimbursement(Double reimb_amount2, String reimb_description2, InputStream reimb_receipt2,
			Integer reimb_author2, Integer reimb_type_id2) {
		this.reimb_amount = reimb_amount2;
		this.reimb_description = reimb_description2;
		this.reimb_receipt = reimb_receipt2;
		this.reimb_author = reimb_author2;
		this.reimb_type_id = reimb_type_id2;
	}
	
	public Reimbursement(Integer reimb_id, Double reimb_amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, InputStream reimb_receipt, Integer reimb_author, Integer reimb_resolver,
			Integer reimb_status_id, Integer reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	public Reimbursement(Integer reimb_resolver2, Integer reimb_author2,  Integer reimb_id,Integer status_id) {
		this.reimb_resolver = reimb_resolver2;
		this.reimb_author = reimb_author2;
		this.reimb_id = reimb_id;
		this.reimb_status_id = status_id;
		// TODO Auto-generated constructor stub
	}
	public Integer getReimb_id() {
		return reimb_id;
	}
	public void setReimb_id(Integer reimb_id) {
		this.reimb_id = reimb_id;
	}
	public Double getReimb_amount() {
		return reimb_amount;
	}
	public void setReimb_amount(Double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}
	public String getReimb_submitted() {
		return reimb_submitted;
	}
	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}
	public String getReimb_resolved() {
		return reimb_resolved;
	}
	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}
	public String getReimb_description() {
		return reimb_description;
	}
	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}
	public InputStream getReimb_receipt() {
		return reimb_receipt;
	}
	public void setReimb_receipt(InputStream reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}
	public Integer getReimb_author() {
		return reimb_author;
	}
	public void setReimb_author(Integer reimb_author) {
		this.reimb_author = reimb_author;
	}
	public Integer getReimb_resolver() {
		return reimb_resolver;
	}
	public void setReimb_resolver(Integer reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}
	public Integer getReimb_status_id() {
		return reimb_status_id;
	}
	public void setReimb_status_id(Integer reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}
	public Integer getReimb_type_id() {
		return reimb_type_id;
	}
	public void setReimb_type_id(Integer reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
		
}
