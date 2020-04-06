package com.alexmonjaraz.invoicetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="credit_item")
public class Invoice_Credit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=1, max=50, message="Credit description must be filled.")
	private String description;
	
	@NotNull
	private int quantity;
	
	@NotNull
	private double price;
	
	private boolean dump;
	
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private Invoice invoice;
	
	public Invoice_Credit() {}
	
	public Invoice_Credit(String desc) {
		this.description = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isDump() {
		return dump;
	}

	public void setDump(boolean dump) {
		this.dump = dump;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "Invoice_Credit [id=" + id + ", description=" + description + ", quantity=" + quantity + ", price="
				+ price + ", dump=" + dump + ", invoice=" + invoice + "]";
	}
	
	
}
