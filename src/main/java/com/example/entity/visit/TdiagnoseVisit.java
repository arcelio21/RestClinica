package com.example.entity.visit;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TdiagnoseVisit {

	@Positive
	private Integer id;
	
	@NotNull
	private Tvisit visitId;
	
	@NotNull
	private Tdiagnose diagnoseId;
	
	@Max(50)
	private String observation;

	
	public TdiagnoseVisit() {
		super();
	}

	public TdiagnoseVisit(@Positive Integer id) {
		super();
		this.id = id;
	}

	public TdiagnoseVisit(@NotNull Tvisit visitId) {
		super();
		this.visitId = visitId;
	}

	public TdiagnoseVisit(@Positive Integer id, @NotNull Tvisit visitId, @NotNull Tdiagnose diagnoseId,
			@Max(50) String observation) {
		super();
		this.id = id;
		this.visitId = visitId;
		this.diagnoseId = diagnoseId;
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

	public Tdiagnose getDiagnoseId() {
		return diagnoseId;
	}

	public void setDiagnoseId(Tdiagnose diagnoseId) {
		this.diagnoseId = diagnoseId;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "TdiagnoseVisit [id=" + id + ", visitId=" + visitId.getId() + ", diagnoseId=" + diagnoseId.getId() + ", observation="
				+ observation + "]";
	}
	
	
}
