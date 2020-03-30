package com.alexmonjaraz.invoicetracker.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alexmonjaraz.invoicetracker.entity.Invoice;
import com.alexmonjaraz.invoicetracker.entity.Invoice_Credit;
import com.alexmonjaraz.invoicetracker.entity.Invoice_Item;

public class InvoiceDTO {

	@Valid
	private Invoice invoice;
	private List<@Valid Invoice_Item> invoiceItems;
	private List<@Valid Invoice_Credit> creditItems;
	
	public InvoiceDTO() {}
	
	public void addInventoryItem(Invoice_Item inventoryItem) {
		if (invoiceItems == null) {
			invoiceItems = new ArrayList<>();
		}
		invoiceItems.add(inventoryItem);
	}
	
	public void addCreditItem(Invoice_Credit creditItem) {
		if (creditItems == null) {
			creditItems = new ArrayList<>();
		}
		creditItems.add(creditItem);
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public List<Invoice_Item> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<Invoice_Item> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public List<Invoice_Credit> getCreditItems() {
		return creditItems;
	}

	public void setCreditItems(List<Invoice_Credit> creditItems) {
		this.creditItems = creditItems;
	}
	
}
