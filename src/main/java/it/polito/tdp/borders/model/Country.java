package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	
	private Integer cCode;
	private String stateAbb;
	private String stateName;
	
	
	public Country(Integer cCode, String stateAbb, String stateName) {
		super();
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateName = stateName;
	}
	
	public Integer getcCode() {
		return cCode;
	}
	
	public String getStateAbb() {
		return stateAbb;
	}
	
	public String getStateName() {
		return stateName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(cCode, other.cCode);
	}

	@Override
	public String toString() {
		return this.stateName;
	}
	
	
	
}
