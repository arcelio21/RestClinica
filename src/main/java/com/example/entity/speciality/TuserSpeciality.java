package com.example.entity.speciality;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import lombok.Getter;
import lombok.Setter;

/**
 *@apiNote Esta clase se encargar de guardar toda la informacion de los registros de la tabla {TuserSpeciality}
 * */
@Getter
@Setter
public class TuserSpeciality {


	@Positive
	private Long id;

	@NotNull
	private Tspeciality specialityId;

	@NotNull
	private TuserTypeReg userTypeRegId;

	@NotNull
	private Tstatus statusId;

	
	public TuserSpeciality() {
		super();
	}


	public TuserSpeciality(@Positive Long id) {
		super();
		this.id = id;
	}


	public TuserSpeciality(@Positive Long id, @NotNull Tspeciality specialityId, @NotNull TuserTypeReg userTypeRegId,
			@NotNull Tstatus statusId) {
		super();
		this.id = id;
		this.specialityId = specialityId;
		this.userTypeRegId = userTypeRegId;
		this.statusId = statusId;
	}


	@Override
	public String toString() {
		return "TuserSpeciality [id=" + id + ", specialityId=" + specialityId.getId() + ", userTypeRegId=" + userTypeRegId.getId()
				+ ", statusId=" + statusId.getId() + "]";
	}

}
