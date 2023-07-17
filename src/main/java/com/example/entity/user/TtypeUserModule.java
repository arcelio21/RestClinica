package com.example.entity.user;

import javax.validation.constraints.NotNull;

import com.example.entity.modules.TmodulePrivilege;
import lombok.ToString;

@ToString
public class TtypeUserModule {

	private Long id;

	@NotNull
	private TmodulePrivilege modulePrivilegeId;
	
	@NotNull
	private TtypeUser typeUser;

	
	public TtypeUserModule() {
		super();
	}


	public TtypeUserModule(Long id,@NotNull TmodulePrivilege modulePrivilegeId, @NotNull TtypeUser typeUser) {
		super();
		this.modulePrivilegeId = modulePrivilegeId;
		this.typeUser = typeUser;
	}


	public TmodulePrivilege getModulePrivilegeId() {
		return modulePrivilegeId;
	}


	public void setModulePrivilegeId(TmodulePrivilege modulePrivilegeId) {
		this.modulePrivilegeId = modulePrivilegeId;
	}


	public TtypeUser getTypeUser() {
		return typeUser;
	}


	public void setTypeUser(TtypeUser typeUser) {
		this.typeUser = typeUser;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
