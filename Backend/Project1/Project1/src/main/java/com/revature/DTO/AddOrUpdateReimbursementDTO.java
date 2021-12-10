package com.revature.DTO;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Objects;

import com.revature.model.User;

public class AddOrUpdateReimbursementDTO {

//	private int reimId; //not needed for DTO
	private double reimAmount;
	private String reimTime;
	private String reimResolveTime;
	private String reimStatus;
	private String reimType;
	private String reimDescription;
	private InputStream reimReceipt; // Is this even the right thing?
	private int reimAuthor;
	private int reimApprover;

	public AddOrUpdateReimbursementDTO() {
//		this.reimStatus = reimStatus;
//		this.reimApprover = currentlyLoggedInUser.getId();
	}
	
	public AddOrUpdateReimbursementDTO(String reimStatus) {
		this.reimStatus = reimStatus;
//		this.reimApprover = currentlyLoggedInUser.getId();
	}

	public AddOrUpdateReimbursementDTO(String reimStatus, User currentlyLoggedInUser) {
		this.reimStatus = reimStatus;
		this.reimApprover = currentlyLoggedInUser.getId();
	}

	public AddOrUpdateReimbursementDTO(double reimAmount, String reimTime, String reimResolveTime, String reimStatus,
			String reimType, String reimDescription, InputStream reimReceipt, User currentlyLoggedInUser,
			int reimApprover) {
		this.reimAmount = reimAmount;
		this.reimTime = reimTime;
		this.reimResolveTime = reimResolveTime;
		this.reimStatus = reimStatus;
		this.reimType = reimType;
		this.reimDescription = reimDescription;
		this.reimReceipt = reimReceipt;
		this.reimAuthor = currentlyLoggedInUser.getId();
		this.reimApprover = reimApprover;
	}

	public AddOrUpdateReimbursementDTO(String reimStatus, int reimApprover) {
		// TODO Auto-generated constructor stub
		this.reimStatus = reimStatus;
		this.reimApprover = reimApprover;
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

	@Override
	public int hashCode() {
		return Objects.hash(reimAmount, reimApprover, reimAuthor, reimDescription, reimReceipt, reimResolveTime,
				reimStatus, reimTime, reimType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateReimbursementDTO other = (AddOrUpdateReimbursementDTO) obj;
		return Double.doubleToLongBits(reimAmount) == Double.doubleToLongBits(other.reimAmount)
				&& reimApprover == other.reimApprover && reimAuthor == other.reimAuthor
				&& Objects.equals(reimDescription, other.reimDescription)
				&& Objects.equals(reimReceipt, other.reimReceipt)
				&& Objects.equals(reimResolveTime, other.reimResolveTime)
				&& Objects.equals(reimStatus, other.reimStatus) && Objects.equals(reimTime, other.reimTime)
				&& Objects.equals(reimType, other.reimType);
	}

	@Override
	public String toString() {
		return "AddOrUpdateReimbursementDTO [reimAmount=" + reimAmount + ", reimTime=" + reimTime + ", reimResolveTime="
				+ reimResolveTime + ", reimStatus=" + reimStatus + ", reimType=" + reimType + ", reimDescription="
				+ reimDescription + ", reimReceipt=" + reimReceipt + ", reimAuther=" + reimAuthor + ", reimApprover="
				+ reimApprover + "]";
	}

}
