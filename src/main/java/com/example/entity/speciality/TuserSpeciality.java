package com.example.entity.speciality;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;

public class TuserSpeciality {

	@Positive
	private Integer id;
	
	@NotNull
	private Tspeciality specialityId;
	
	@NotNull
	private TuserTypeReg userTypeRegId;
	
	@NotNull
	private Tstatus statusId;

	
	public TuserSpeciality() {
		super();
	}


	public TuserSpeciality(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TuserSpeciality(@Positive Integer id, @NotNull Tspeciality specialityId, @NotNull TuserTypeReg userTypeRegId,
			@NotNull Tstatus statusId) {
		super();
		this.id = id;
		this.specialityId = specialityId;
		this.userTypeRegId = userTypeRegId;
		this.statusId = statusId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Tspeciality getSpecialityId() {
		return specialityId;
	}


	public void setSpecialityId(Tspeciality specialityId) {
		this.specialityId = specialityId;
	}


	public TuserTypeReg getUserTypeRegId() {
		return userTypeRegId;
	}


	public void setUserTypeRegId(TuserTypeReg userTypeRegId) {
		this.userTypeRegId = userTypeRegId;
	}


	public Tstatus getStatusId() {
		return statusId;
	}


	public void setStatusId(Tstatus statusId) {
		this.statusId = statusId;
	}


	@Override
	public String toString() {
		return "TuserSpeciality [id=" + id + ", specialityId=" + specialityId.getId() + ", userTypeRegId=" + userTypeRegId.getId()
				+ ", statusId=" + statusId.getId() + "]";
	}
	
	
	
	
}
