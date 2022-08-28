package com.example.entity.visit;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TtestVisit {

	@Positive
	private Integer id;
	
	@NotNull
	private Tvisit visitId;
	
	@NotNull
	private Ttest testId;
	
	@Max(30)
	private String observation;

	
	public TtestVisit() {
		super();
	}


	public TtestVisit(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TtestVisit(@NotNull Tvisit visitId) {
		super();
		this.visitId = visitId;
	}


	public TtestVisit(@Positive Integer id, @NotNull Tvisit visitId, @NotNull Ttest testId,
			@Max(30) String observation) {
		super();
		this.id = id;
		this.visitId = visitId;
		this.testId = testId;
		this.observation = observation;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Tvisit getVisitId() {
		return visitId;
	}


	public void setVisitId(Tvisit visitId) {
		this.visitId = visitId;
	}


	public Ttest getTestId() {
		return testId;
	}


	public void setTestId(Ttest testId) {
		this.testId = testId;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	@Override
	public String toString() {
		return "TtestVisit [id=" + id + ", visitId=" + visitId.getId() + ", testId=" + testId.getId() + ", observation=" + observation
				+ "]";
	}
	
	
	
}
