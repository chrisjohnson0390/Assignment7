package com.meritamerica.main.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meritamerica.main.exceptions.FieldErrorException;

@Entity
@JsonIgnoreProperties(value = {"cdAccount"})
public class CDOffering {
	@NotNull
	@Positive
	private int term;
	@NotNull
	@Positive
	private double interestRate;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="offer_id")
	private long id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "offering")
	private List<CDAccount> cdAccount;
		
	public CDOffering() {
		
	}
	
	static CDOffering readFromString(String cdOfferingDataString) {
		String[] data = cdOfferingDataString.split(",");
		int term = Integer.parseInt(data[0]);
		double interestRate = Double.parseDouble(data[1]);
		return new CDOffering(term, interestRate);
	}
	
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) throws FieldErrorException {
		if (interestRate >= 1) {
			throw new FieldErrorException("Invalid interest rate");
		}
		this.interestRate = interestRate;
	}
	
	public int getTerm() {
		return term;
	}
	
	public void setTerm(int years) {
		this.term = years;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CDAccount> getCdAccount() {
		return cdAccount;
	}

	public void setCdAccount(List<CDAccount> cdAccount) {
		this.cdAccount = cdAccount;
	}
}
