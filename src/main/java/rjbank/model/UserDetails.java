package rjbank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user_details")
	private Long id;
	
	@Column(name = "firstname")
	@NotEmpty(message = "{pl.javastart.model.UserDetails.firstName.NotEmpty}")
	@Size(min = 2, max = 15, message = "{pl.javastart.model.UserDetails.firstName.Size}")
	private String firstName;
	
	@Column(name = "lastname")
	@NotEmpty(message = "{pl.javastart.model.UserDetails.lastName.NotEmpty}")
	@Size(min = 2, max = 15, message = "{pl.javastart.model.UserDetails.lastName.Size}")
	private String lastName;
	
	@Column
	@NotEmpty(message = "{pl.javastart.model.UserDetails.email.NotEmpty}")
	@Email(message = "{pl.javastart.model.UserDetails.email.Email}")
	private String email;
	
	@Column
	@Min(value = 1, message = "{pl.javastart.model.UserDetails.money.Min}")
	private int money;
	
	@Column(name = "phone_number")
	@NotEmpty(message = "{pl.javastart.model.UserDetails.phoneNumber.NotEmpty}")
	@Size(min = 9, max = 9, message = "{pl.javastart.model.UserDetails.phoneNumber.Size}")
	private String phoneNumber;
	
	@Column(name = "account_number")
	private int accountNumber;
	
	@NotEmpty(message = "{pl.javastart.model.UserDetails.address.NotEmpty}")
	private String address;
	
	@OneToOne(mappedBy = "userDetails")
	private User user;
	
	public UserDetails(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", money=" + money + ", phoneNumber=" + phoneNumber + ", accountNumber=" + accountNumber
				+ ", address=" + address + ", user=" + user.getId() + "]";
	}

}
