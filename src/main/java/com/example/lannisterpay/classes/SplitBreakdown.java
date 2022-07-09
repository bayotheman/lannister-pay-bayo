package com.example.lannisterpay.classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SplitBreakdown {
	@Setter
	@Getter
	private String id;

	@Setter
	@Getter
	private double value;

	public SplitBreakdown() {
	}

	public SplitBreakdown(String id, double value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SplitBreakdown)) return false;
		SplitBreakdown that = (SplitBreakdown) o;
		return Double.compare(that.value, value) == 0 && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, value);
	}
}
