package com.example.lannisterpay.classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Component

public class Computation {

	@Setter@Getter
	private long id ;
	@Setter@Getter
	private BigDecimal balance;
	private final double epsilon = 0.01;
	@Setter@Getter
	private List<SplitBreakdown> splitBreakdown ;
	@Setter
	private Transaction transaction;

	public Computation(){

	}

	public Computation(Transaction transaction){
		this.transaction = transaction;
		this.id = transaction.getId();
		setSplitBreakdown();
		sortSplitArray(transaction.getSplitInfo());
		this.balance = new BigDecimal(0).setScale(2, RoundingMode.HALF_DOWN);

	}

	private void sortSplitArray(List<Split> splits){
		if(splits == null){
			return;
		}
		splits.sort(new Comparator<Split>() {
			@Override
			public int compare(Split o1, Split o2) {
				return o1.getSplitType().compareTo(o2.getSplitType());
			}
		});
	}

	public void compute(){
		if(transaction.getSplitInfo() == null){
			return;
		}
		balance = BigDecimal.valueOf(transaction.getAmount());
		System.out.println("Initial Balance: " + balance + "\n");
		int totalRatio = getTotalRatio();
		double splitAmount = 0;
		double initialBalance = balance.doubleValue();

		for(Split split: transaction.getSplitInfo()){
			checkEachSplitAmountIsValid(split.getSplitValue());
			double initialBal = balance.doubleValue();
			checkSplitAmtGreaterThanTransactionAmt(splitAmount, initialBalance);
			if(split.getSplitType() == SplitType.FLAT){
				double value = BigDecimal.valueOf(split.getSplitValue()).setScale(2,RoundingMode.UNNECESSARY).doubleValue();
				System.out.println("Split amount for " + split.getSplitEntityId() + ": " + split.getSplitValue());
				balance = balance.subtract(BigDecimal.valueOf(value));
				splitAmount += split.getSplitValue();
				System.out.println("Balance after split calculation for " + split.getSplitEntityId() + ": (" +
						+ initialBal + " - " + split.getSplitValue() +") = " + balance + "\n");
			}
			else if(split.getSplitType() == SplitType.PERCENTAGE){
				double value = BigDecimal.valueOf((split.getSplitValue() * balance.doubleValue()) / 100)
						.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
				System.out.println("Split amount for " + split.getSplitEntityId() + ": ( " + split.getSplitValue() + " % OF "
						+ initialBal +" ) = " + value);
				split.setSplitValue(value);
				splitAmount += value;
				balance = balance.subtract(BigDecimal.valueOf(value).setScale(2,RoundingMode.HALF_DOWN));
				System.out.println("Balance after split calculation for " + split.getSplitEntityId() + ": (" +
						+ initialBal + " - " + value + ") = " + balance + "\n");

			}
			checkSplitAmtGreaterThanTransactionAmt(splitAmount,initialBalance);
		}
		double initialBal = balance.doubleValue();
		for(Split split: transaction.getSplitInfo()) {
			checkSplitAmtGreaterThanTransactionAmt(splitAmount,initialBalance);
			if (split.getSplitType() == SplitType.RATIO) {
				System.out.println("Total Ratio: " + totalRatio);
				System.out.println("Split amount for " + split.getSplitEntityId() + ": ( (" + split.getSplitValue() + "/" + totalRatio +
						") * " + initialBal + " ) = " + (split.getSplitValue() * initialBal) / totalRatio);
				double value = BigDecimal.valueOf((split.getSplitValue() * initialBal) / totalRatio)
						.setScale(2, RoundingMode.HALF_DOWN).doubleValue();
				split.setSplitValue(value);
				splitAmount += value;
				System.out.println("Balance after split calculation for " + split.getSplitEntityId() + ": (" +
						+balance.doubleValue() + " - " + value + ") = " + balance + "\n");
				balance= balance.subtract(BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_DOWN));
			}
		}

		if( balance.doubleValue() < 0 || (splitAmount > initialBalance)){
			transaction.setAmount(0D);
			throw new IllegalStateException("Split amount cannot be greater than transaction amount or less than 0");
		}
		else {
			transaction.setAmount(balance.doubleValue());
		}
		System.out.println("Final Balance: " + balance);
		setSplitBreakdown();
	}

	public void setSplitBreakdown(){
		this.splitBreakdown = new ArrayList<>();
		for(Split split: transaction.getSplitInfo()){
			SplitBreakdown splitBreakdown = new SplitBreakdown(split.getSplitEntityId(),split.getSplitValue());
			this.splitBreakdown.add(splitBreakdown);
		}
	}

	private static void checkSplitAmtGreaterThanTransactionAmt(double splitAmount, double transactionAmount){
		if(splitAmount > transactionAmount)
			throw new IllegalStateException("Total balance cannot be greater than transaction amount");

	}

	private static void checkEachSplitAmountIsValid(double amount){
		if(amount < 0){
			throw new IllegalStateException("Split amount cannot be less than 0");
		}
	}

	private int getTotalRatio(){
		int total = 0;
		for(Split split: transaction.getSplitInfo()){
			if(split.getSplitType() == SplitType.RATIO)
				total += split.getSplitValue();
		}
		return total;
	}


	public String toString(){
		return "{" +
				"\"ID\": " + id +
				", \"Balance\": " + balance +
				", \"SplitBreakdown\": " + (transaction.getSplitInfo()) +
				"}";

	}

//	public Map<String, String> toMap(){
//		Map<String, String> map = new HashMap<>();
//		map.put("ID", String.valueOf(transaction.getId()));
//		map.put("Balance", String.valueOf(balance));
//		map.put("SplitBreakdown", String.valueOf(transaction.getSplitInfo()));
//		return map;
//	}
}
