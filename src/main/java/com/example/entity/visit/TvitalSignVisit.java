package com.example.entity.visit;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TvitalSignVisit {

	@Positive
	private Integer id;
	
	@NotNull
	private Tvisit visitId;
	@NotNull
	private TvitalSign vitalSignId;
	
	@Max(30)
	private String observation;

	
	public TvitalSignVisit() {
		super();
	}


	public TvitalSignVisit(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TvitalSignVisit(@Positive Integer id, @NotNull Tvisit visitId, @NotNull TvitalSign vitalSignId,
			@Max(30) String observation) {
		super();
		this.id = id;
		this.visitId = visitId;
		this.vitalSignId = vitalSignId;
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


	public TvitalSign getVitalSignId() {
		return vitalSignId;
	}


	public void setVitalSignId(TvitalSign vitalSignId) {
		this.vitalSignId = vitalSignId;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	@Override
	public String toString() {
		return "TvitalSignVisit [id=" + id + ", visitId=" + visitId.getId() + ", vitalSignId=" + vitalSignId.getId() + ", observation="
				+ observation + "]";
	}
	
	
}
