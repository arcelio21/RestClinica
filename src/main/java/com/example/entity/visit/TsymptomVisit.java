package com.example.entity.visit;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TsymptomVisit {

	@Positive
	private Integer id;
	
	@NotNull
	private Tvisit visitId;
	
	@NotNull
	private Tsymptom symptomId;
	
	@Max(50)
	private String observation;

	
	public TsymptomVisit() {
		super();
	}


	public TsymptomVisit(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TsymptomVisit(@NotNull Tvisit visitId) {
		super();
		this.visitId = visitId;
	}


	public TsymptomVisit(@Positive Integer id, @NotNull Tvisit visitId, @NotNull Tsymptom symptomId,
			@Max(50) String observation) {
		super();
		this.id = id;
		this.visitId = visitId;
		this.symptomId = symptomId;
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


	public Tsymptom getSymptomId() {
		return symptomId;
	}


	public void setSymptomId(Tsymptom symptomId) {
		this.symptomId = symptomId;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	@Override
	public String toString() {
		return "TsymptomVisit [id=" + id + ", visitId=" + visitId.getId() + ", symptomId=" + symptomId.getId() + ", observation="
				+ observation + "]";
	}
	
	
}
