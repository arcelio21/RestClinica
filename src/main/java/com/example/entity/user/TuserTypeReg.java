package com.example.entity.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.entity.status.Tstatus;

public class TuserTypeReg {

	@Positive
	private Long id;
	
	@NotNull
	private TuserReg userRegId;
	
	@NotNull
	private TtypeUser typeUser;
	
	@NotNull
	private Tstatus statusId;

	
	public TuserTypeReg() {
		super();
	}


	public TuserTypeReg(@Positive Long id) {
		super();
		this.id = id;
	}


	public TuserTypeReg(@Positive Long id, @NotNull TuserReg userRegId, @NotNull TtypeUser typeUser,
			@NotNull Tstatus statusId) {
		super();
		this.id = id;
		this.userRegId = userRegId;
		this.typeUser = typeUser;
		this.statusId = statusId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TuserReg getUserRegId() {
		return userRegId;
	}


	public void setUserRegId(TuserReg userRegId) {
		this.userRegId = userRegId;
	}


	public TtypeUser getTypeUser() {
		return typeUser;
	}


	public void setTypeUser(TtypeUser typeUser) {
		this.typeUser = typeUser;
	}


	public Tstatus getStatusId() {
		return statusId;
	}


	public void setStatusId(Tstatus statusId) {
		this.statusId = statusId;
	}


	@Override
	public String toString() {
		return "TuserTypeReg [id=" + id + ", userRegId=" + userRegId.getId() + ", typeUser=" + typeUser.getId() + ", statusId="
				+ statusId.getId() + "]";
	}
	
	
}
