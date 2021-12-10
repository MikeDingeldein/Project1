package com.revature.model;

import java.io.InputStream;
import java.sql.Blob;

public class Reimbursement {

	private int reimId;
	private double reimAmount;
	private String reimTime;
	private String reimResolveTime;
	private String reimStatus;
	private String reimType;
	private String reimDescription;
	private InputStream reimReceipt; // Is this even the right thing?
	private int reimAuthor;
	private int reimApprover;

	public Reimbursement() {

	}

//	public Reimbursement(int reimId, double reimAmount, String reimTime, String reimResolveTime, String reimStatus,
//			String reimType, String reimDescription, InputStream reimReceipt, int reimAuthor, int reimApprover) {
//		super();
//		this.reimId = reimId;
//		this.reimAmount = reimAmount;
//		this.reimTime = reimTime;
//		this.reimResolveTime = reimResolveTime;
//		this.reimStatus = reimStatus;
//		this.reimType = reimType;
//		this.reimDescription = reimDescription;
//		this.reimReceipt = reimReceipt;
//		this.reimAuthor = reimAuthor;
//		this.reimApprover = reimApprover;
//
//	}
	
	//without receipt
	public Reimbursement(int reimId, double reimAmount, String reimTime, String reimResolveTime, String reimStatus,
			String reimType, String reimDescription, int reimAuthor, int reimApprover) {
		super();
		this.reimId = reimId;
		this.reimAmount = reimAmount;
		this.reimTime = reimTime;
		this.reimResolveTime = reimResolveTime;
		this.reimStatus = reimStatus;
		this.reimType = reimType;
		this.reimDescription = reimDescription;

		this.reimAuthor = reimAuthor;
		this.reimApprover = reimApprover;

	}
	
	public Reimbursement(int id, String reimType2, int reimApprover2) {
		// TODO Auto-generated constructor stub
		this.reimId = id;
		this.reimType = reimType2;
		this.reimApprover = reimApprover2;
	}

	public Reimbursement(int automaticallyGeneratedId, double amount,
			String reimType2, String reimDescription2,   int employeeId) {
		// TODO Auto-generated constructor stub
		this.reimId = automaticallyGeneratedId;
		this.reimAmount = amount;
		this.reimType = reimType2;
		this.reimDescription = reimDescription2;
		this.reimAuthor = employeeId;
	}

	//fake out the test!?!?!?
	public Reimbursement(int i, double d, String string, String string2, String string3, String string4, String string5,
			Object object, int j, int k) {
		// TODO Auto-generated constructor stub
		this.reimId = i;
		this.reimAmount = d;
		this.reimTime = string;
		this.reimResolveTime = string2;
		this.reimStatus = string3;
		this.reimType = string4;
		this.reimDescription = string5;
		this.reimReceipt = (InputStream) object;
		this.reimAuthor = j;
		this.reimApprover = k;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	public double getReimAmount() {
		return reimAmount;
	}

	public void setReimAmount(double reimAmount) {
		this.reimAmount = reimAmount;
	}

	public String getReimTime() {
		return reimTime;
	}

	public void setReimTime(String reimTime) {
		this.reimTime = reimTime;
	}

	public String getReimResolveTime() {
		return reimResolveTime;
	}

	public void setReimResolveTime(String reimResolveTime) {
		this.reimResolveTime = reimResolveTime;
	}

	public String getReimStatus() {
		return reimStatus;
	}

	public void setReimStatus(String reimStatus) {
		this.reimStatus = reimStatus;
	}

	public String getReimType() {
		return reimType;
	}

	public void setReimType(String reimType) {
		this.reimType = reimType;
	}

	public String getReimDescription() {
		return reimDescription;
	}

	public void setReimDescription(String reimDescription) {
		this.reimDescription = reimDescription;
	}

	public InputStream getReimReceipt() {
		return reimReceipt;
	}

	public void setReimReceipt(InputStream reimReceipt) {
		this.reimReceipt = reimReceipt;
	}

	public int getReimAuthor() {
		return reimAuthor;
	}

	public void setReimAuthor(int reimAuthor) {
		this.reimAuthor = reimAuthor;
	}

	public int getReimApprover() {
		return reimApprover;
	}

	public void setReimApprover(int reimApprover) {
		this.reimApprover = reimApprover;
	}
	
	
}
