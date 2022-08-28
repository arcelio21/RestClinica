package com.example.entity.modules;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.entity.status.Tstatus;

public class TmodulePrivilege {

	@Positive
	private Integer id;
	
	@NotNull
	private Tprivilege privilege;
	
	@NotNull
	private Tmodule module;
	
	@NotNull
	private Tstatus status;

	
	public TmodulePrivilege() {
		super();
	}


	public TmodulePrivilege(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TmodulePrivilege(@Positive Integer id, @NotNull Tprivilege privilege, @NotNull Tmodule module,
			@NotNull Tstatus status) {
		super();
		this.id = id;
		this.privilege = privilege;
		this.module = module;
		this.status = status;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Tprivilege getPrivilege() {
		return privilege;
	}


	public void setPrivilege(Tprivilege privilege) {
		this.privilege = privilege;
	}


	public Tmodule getModule() {
		return module;
	}


	public void setModule(Tmodule module) {
		this.module = module;
	}


	public Tstatus getStatus() {
		return status;
	}


	public void setStatus(Tstatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "TmodulesPrivileges [id=" + id + ", privilege=" + privilege.getId() + ", module=" + module.getId() + ", status=" + status.getId()
				+ "]";
	}
	
	
}
