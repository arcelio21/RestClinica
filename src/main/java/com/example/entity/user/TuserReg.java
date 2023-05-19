package com.example.entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class TuserReg implements UserDetails {


	@Positive
	private Long id;
	
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
	private LocalDate birthday;
	
	@Max(value = 150)
	private String password;
	
	@FutureOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO")
	private LocalDateTime creationDate;
	
	@NotNull
	private Taddress addressId;
	
	
	private List<@NotNull TuserTypeReg> usersTypesRegs;

	
	public TuserReg() {
		super();
	}


	public TuserReg(@Positive Long id) {
		super();
		this.id = id;
	}



	public TuserReg(@Positive Long id, @NotNull @Size(min = 13, max = 18) @Positive Long idenCard,
			@NotEmpty(message = "EL VALOR DE NOMBRE NO PUEDE SER NULO VACIO") @Size(min = 1, max = 30) String name,
			@NotEmpty(message = "EL VALOR DE APELLIDO NO PUEDE SER NULO VACIO") @Size(min = 1, max = 30) String lastName,
			@NotEmpty(message = "EL VALOR DE APELLIDO NO PUEDE SER NULO VACIO") @Size(min = 7, max = 8) String contact,
			@Email(message = "E-MAIL NO VALIDO") @Max(50) String email,
			@PastOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO") LocalDate birthday,
			@Max(150) String password,
			@FutureOrPresent(message = "EL RANGO DE FECHA NO ES CORRECTO") LocalDateTime creationDate,
			@NotNull Taddress addressId) {
		super();
		this.id = id;
		this.idenCard = idenCard;
		this.name = name;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.birthday = birthday;
		this.password = password;
		this.creationDate = creationDate;
		this.addressId = addressId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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
	
	public LocalDate getBirthday() {
		return birthday;
	}


	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return String.valueOf(this.idenCard);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public void setPassword(String password) {
		this.password = password;
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
				+ ", contact=" + contact + ", email=" + email + ", fechaNacimiento=" + birthday + ", password="
				+ password + ", fechaCreacion=" + creationDate + ", addressId=" + addressId.getId() + "]";
	}
	
	
	
}
