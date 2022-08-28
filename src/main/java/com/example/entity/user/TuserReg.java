package com.example.entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.example.entity.address.Taddress;

public class TuserReg {


	@Positive
	private Integer id;
	
	@NotNull
	@Size(min = 13,max = 18)
	@Positive
	private Long idenCard;
	
	@NotEmpty(message = "EL VALOR DE NOMBRE NO PUEDE SER NULO VACIO")
	@Size(min = 1,max = 30)
	private String name;
	
	@NotEmpty(message = "EL VALOR DE APELLIDO NO PUEDE SER NULO VACIO")
	@Size(min = 1,max = 30)
	private String lastName;
	
	@NotEmpty(message = "EL VALOR DE APELLIDO NO PUEDE SER NULO VACIO")
	@Size(min = 7,max = 8)
	private String contact;
	
	@Email(message = "E-MAIL NO VALIDO")
	@Max(value = 50)
	private String email;
	
	@PastOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO")
	private LocalDate fehcaNacimiento;
	
	@Max(value = 150)
	private String password;
	
	@FutureOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO")
	private LocalDateTime fechaCreacion;
	
	@NotNull
	private Taddress addressId;
	
	
	private List<@NotNull TuserTypeReg> usersTypesRegs;

	
	public TuserReg() {
		super();
	}


	public TuserReg(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TuserReg(@NotNull @Size(min = 13, max = 18) @Positive Long idenCard) {
		super();
		this.idenCard = idenCard;
	}


	public TuserReg(@Positive Integer id, @NotNull @Size(min = 13, max = 18) @Positive Long idenCard,
			@NotEmpty(message = "EL VALOR DE NOMBRE NO PUEDE SER NULO VACIO") @Size(min = 1, max = 30) String name,
			@NotEmpty(message = "EL VALOR DE APELLIDO NO PUEDE SER NULO VACIO") @Size(min = 1, max = 30) String lastName,
			@NotEmpty(message = "EL VALOR DE APELLIDO NO PUEDE SER NULO VACIO") @Size(min = 7, max = 8) String contact,
			@Email(message = "E-MAIL NO VALIDO") @Max(50) String email,
			@PastOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO") LocalDate fehcaNacimiento,
			@Max(150) String password,
			@FutureOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO") LocalDateTime fechaCreacion,
			@NotNull Taddress addressId) {
		super();
		this.id = id;
		this.idenCard = idenCard;
		this.name = name;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.fehcaNacimiento = fehcaNacimiento;
		this.password = password;
		this.fechaCreacion = fechaCreacion;
		this.addressId = addressId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Long getIdenCard() {
		return idenCard;
	}


	public void setIdenCard(Long idenCard) {
		this.idenCard = idenCard;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getFehcaNacimiento() {
		return fehcaNacimiento;
	}


	public void setFehcaNacimiento(LocalDate fehcaNacimiento) {
		this.fehcaNacimiento = fehcaNacimiento;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public Taddress getAddressId() {
		return addressId;
	}


	public void setAddressId(Taddress addressId) {
		this.addressId = addressId;
	}


	
	public List<TuserTypeReg> getUsersTypesRegs() {
		return usersTypesRegs;
	}


	public void setUsersTypesRegs(List<TuserTypeReg> usersTypesRegs) {
		this.usersTypesRegs = usersTypesRegs;
	}


	@Override
	public String toString() {
		return "TusersRegs [id=" + id + ", idenCard=" + idenCard + ", name=" + name + ", lastName=" + lastName
				+ ", contact=" + contact + ", email=" + email + ", fehcaNacimiento=" + fehcaNacimiento + ", password="
				+ password + ", fechaCreacion=" + fechaCreacion + ", addressId=" + addressId.getId() + "]";
	}
	
	
	
}
