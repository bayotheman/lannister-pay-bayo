package com.example.lannisterpay.classes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class Split{

	@Getter@Setter
	private String splitEntityId;

	@Setter @Getter
	private SplitType splitType;

	@Setter @Getter
	private double splitValue;


    public Split(){

    }


	public Split(SplitType splitType, double splitValue, String SplitEntityId) {
		this.splitEntityId = SplitEntityId;
		this.splitType = splitType;
		this.splitValue = splitValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Split)) return false;
		Split split = (Split) o;
		return Objects.equals(splitEntityId, split.splitEntityId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(splitEntityId);
	}

	@Override
	public String toString() {
		return "{" +
				" \"SplitEntityId\":" + "\"" + splitEntityId + "\"" +
				", \"Amount\":" + splitValue +
				'}';
	}

}