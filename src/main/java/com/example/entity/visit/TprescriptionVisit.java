package com.example.entity.visit;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TprescriptionVisit {

	@Positive
	private Integer id;
	
	@NotNull
	private Tvisit visitId;
	
	@NotNull
	private Tprescription prescriptionId;
	
	@Max(50)
	private String observation;

	public TprescriptionVisit() {
		super();
	}

	public TprescriptionVisit(@Positive Integer id) {
		super();
		this.id = id;
	}

	public TprescriptionVisit(@NotNull Tvisit visitId) {
		super();
		this.visitId = visitId;
	}

	public TprescriptionVisit(@Positive Integer id, @NotNull Tvisit visitId, @NotNull Tprescription prescriptionId,
			@Max(50) String observation) {
		super();
		this.id = id;
		this.visitId = visitId;
		this.prescriptionId = prescriptionId;
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

	public Tprescription geTprescriptionId() {
		return prescriptionId;
	}

	public void seTprescriptionId(Tprescription prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "TprescriptionVisit [id=" + id + ", visitId=" + visitId.getId() + ", prescriptionId=" + prescriptionId.getId() + ", observation="
				+ observation + "]";
	}
	
}
