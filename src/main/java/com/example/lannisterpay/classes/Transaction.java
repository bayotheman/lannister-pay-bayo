package com.example.lannisterpay.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Component
//@Entity
public class Transaction {
	@Getter
	@Setter
	private long id;

	@Getter
	private double amount;

	@Setter
	@Getter
	private String currency;

	@Setter
	@Getter
	private String customerEmail;

	@Getter
	private List<Split> splitInfo ;

	public Transaction(){

	}

	public Transaction(long ID, double amount, String currency, String customerEmail, List<Split> splitInfo) {
		if(splitInfo.size() > 20 || splitInfo.size() == 0) throw new IllegalStateException("Exceeds Split items limit");
		this.id = ID;
		this.amount = amount;
		this.currency = currency;
		this.customerEmail = customerEmail;
		this.splitInfo = splitInfo;
	}

	public void setAmount(Double amount){
		assert amount >= 0: "amount is < 0 : " + amount;
		this.amount = amount;
	}

	public void setSplitInfo(List<Split> splitInfo){
		assert (splitInfo.size() > 20 || (splitInfo.isEmpty())):
				new AssertionError("SplitInfo size is either 0 or greater than 20");
		this.splitInfo = splitInfo;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Transaction)) return false;
		Transaction that = (Transaction) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "{" +
				"\"ID\": " + id +
				", \"Amount\": " + amount +
				", \"Currency\": '" + currency + '\'' +
				", \"CustomerEmail\": '" + customerEmail + '\'' +
				", \"SplitInfo\": " + splitInfo +
				'}';
	}

}