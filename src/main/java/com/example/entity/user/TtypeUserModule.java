package com.example.entity.user;

import javax.validation.constraints.NotNull;

import com.example.entity.modules.TmodulePrivilege;

public class TtypeUserModule {
	
	@NotNull
	private TmodulePrivilege modulePrivilegeId;
	
	@NotNull
	private TtypeUser typeUser;

	
	public TtypeUserModule() {
		super();
	}


	public TtypeUserModule(@NotNull TmodulePrivilege modulePrivilegeId, @NotNull TtypeUser typeUser) {
		super();
		this.modulePrivilegeId = modulePrivilegeId;
		this.typeUser = typeUser;
	}

	
}
