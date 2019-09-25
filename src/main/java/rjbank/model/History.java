package rjbank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "history")
public class History implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int sender; 
	
	@Column
	@Min(value = 10000000, message = "{pl.javastart.model.History.receiver.Min}")
	@Max(value = 99999999, message = "{pl.javastart.model.History.receiver.Max}")
	private int receiver;
	
	@Column
	@Min(value = 1, message = "{pl.javastart.model.History.amount.Min}")
	private int amount;
	
	@Column
	String date;
	
	public History() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", amount=" + amount + ", data="
				+ date + "]";
	}

}
