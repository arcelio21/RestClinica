package com.example.entity.visit;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;

public class Tvisit {

	@Positive
	private Integer id;
	
	@NotNull
	private TuserTypeReg patientId;
	
	@NotNull
	private TuserSpeciality nurseSpecialityId;
	
	@NotNull
	private TuserSpeciality physicianSpecialityId;
	
	@NotNull
	private Tspeciality specialityId;
	
	@NotEmpty
	@Max(50)
	private String observation;
	
	@FutureOrPresent
	private LocalDateTime creationDate;
	
	@NotNull
	private Tstatus statusId;

	private List<TvitalSignVisit> vitalSigns;
	
	private List<TtestVisit> tests;
	
	private List<TdiagnoseVisit> diagnoses;
	
	private List<TprescriptionVisit> prescriptions;
	
	private List<TsymptomVisit> symptoms;
	
	public Tvisit(@Positive Integer id) {
		super();
		this.id = id;
	}


	public Tvisit(@NotNull TuserTypeReg patientId) {
		super();
		this.patientId = patientId;
	}


	public Tvisit(@Positive Integer id, @NotNull TuserTypeReg patientId, @NotNull TuserSpeciality nurseSpecialityId,
			@NotNull TuserSpeciality physicianSpecialityId, @NotNull Tspeciality specialityId,
			@NotEmpty @Max(50) String observation, @FutureOrPresent LocalDateTime creationDate,
			@NotNull Tstatus statusId) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.nurseSpecialityId = nurseSpecialityId;
		this.physicianSpecialityId = physicianSpecialityId;
		this.specialityId = specialityId;
		this.observation = observation;
		this.creationDate = creationDate;
		this.statusId = statusId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public TuserTypeReg getPatientId() {
		return patientId;
	}


	public void setPatientId(TuserTypeReg patientId) {
		this.patientId = patientId;
	}


	public TuserSpeciality getNurseSpecialityId() {
		return nurseSpecialityId;
	}


	public void setNurseSpecialityId(TuserSpeciality nurseSpecialityId) {
		this.nurseSpecialityId = nurseSpecialityId;
	}


	public TuserSpeciality getPhysicianSpecialityId() {
		return physicianSpecialityId;
	}


	public void setPhysicianSpecialityId(TuserSpeciality physicianSpecialityId) {
		this.physicianSpecialityId = physicianSpecialityId;
	}


	public Tspeciality getSpecialityId() {
		return specialityId;
	}


	public void setSpecialityId(Tspeciality specialityId) {
		this.specialityId = specialityId;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	public Tstatus getStatusId() {
		return statusId;
	}


	public void setStatusId(Tstatus statusId) {
		this.statusId = statusId;
	}


	
	public List<TvitalSignVisit> getVitalSigns() {
		return vitalSigns;
	}

	

	public List<TtestVisit> getTests() {
		return tests;
	}


	
	public List<TdiagnoseVisit> getDiagnoses() {
		return diagnoses;
	}

	

	public List<TprescriptionVisit> getPrescriptions() {
		return prescriptions;
	}

	

	public List<TsymptomVisit> getSymptoms() {
		return symptoms;
	}


	@Override
	public String toString() {
		return "Tvisit [id=" + id + ", patientId=" + patientId.getId() + ", nurseSpecialityId=" + nurseSpecialityId.getId()
				+ ", physicianSpecialityId=" + physicianSpecialityId.getId() + ", specialityId=" + specialityId.getId()
				+ ", observation=" + observation + ", creationDate=" + creationDate + ", statusId=" + statusId + "]";
	}
	
	
}
