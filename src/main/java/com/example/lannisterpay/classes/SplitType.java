package com.example.lannisterpay.classes;

public enum SplitType { FLAT(1) , PERCENTAGE(2 ), RATIO(3);
	private int value ;
	SplitType(int i) {
		this.value = i;
	}

	public int getValue() {
		return value;
	}
}
