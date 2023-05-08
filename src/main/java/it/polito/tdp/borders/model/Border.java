package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {
	
	private Integer state1No;
	private Integer state2No;
	private Integer dyad;
	private String state1Ab;
	private String state2Ab;
	private Integer year;
	private Integer contType;
	private Double version;
	
	public Border(Integer state1No, Integer state2No, Integer dyad, String state1Ab, String state2Ab, Integer year, Integer contType, Double version) {
		super();
		this.state1No = state1No;
		this.state2No = state2No;
		this.dyad = dyad;
		this.state1Ab = state1Ab;
		this.state2Ab = state2Ab;
		this.year = year;
		this.contType = contType;
		this.version = version;
	}

	public Integer getState1No() {
		return state1No;
	}

	public Integer getState2No() {
		return state2No;
	}

	public Integer getDyad() {
		return dyad;
	}

	public String getState1Ab() {
		return state1Ab;
	}

	public String getState2Ab() {
		return state2Ab;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getContType() {
		return contType;
	}

	public Double getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		return Objects.hash(state1No, state2No);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		return Objects.equals(state1No, other.state1No) && Objects.equals(state2No, other.state2No);
	}

	@Override
	public String toString() {
		return "Border [state1No=" + state1No + ", state2No=" + state2No + ", dyad=" + dyad + ", state1Ab=" + state1Ab
				+ ", state2Ab=" + state2Ab + ", year=" + year + ", contType=" + contType + ", version=" + version + "]";
	}
	

}
