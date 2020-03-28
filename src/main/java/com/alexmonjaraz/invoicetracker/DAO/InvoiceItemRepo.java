package com.alexmonjaraz.invoicetracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexmonjaraz.invoicetracker.entity.Invoice_Item;

public interface InvoiceItemRepo extends JpaRepository<Invoice_Item, Integer> {

}
