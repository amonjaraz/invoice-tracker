package com.alexmonjaraz.invoicetracker.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invoice_number")
	private int invoiceNumber;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@NotNull
	@Size(min=1, message="Invoice created by is required.")
	@Column(name="created_by")
	private String createdBy;
	
	private String terms;
	
	private String note;
	
	private double discount;
	
	@Column(name="commission_pay_date")
	private Date commissionPayDate;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@OneToMany(mappedBy="invoice")
	private List<Invoice_Item> invoiceItems;
	
	public void add(Invoice_Item invoiceItem) {
		if (invoiceItems == null) {
			invoiceItems = new ArrayList<Invoice_Item>();
		}
		invoiceItems.add(invoiceItem);
		invoiceItem.setInvoice(this);
	}
	
	public Invoice() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getCommissionPayDate() {
		return commissionPayDate;
	}

	public void setCommissionPayDate(Date commissionPayDate) {
		this.commissionPayDate = commissionPayDate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceNumber=" + invoiceNumber + ", dateCreated=" + dateCreated
				+ ", createdBy=" + createdBy + ", terms=" + terms + ", note=" + note + ", discount=" + discount
				+ ", commissionPayDate=" + commissionPayDate + ", store=" + store + "]";
	}

	public List<Invoice_Item> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<Invoice_Item> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	
	
}
