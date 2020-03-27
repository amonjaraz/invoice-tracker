package com.alexmonjaraz.invoicetracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexmonjaraz.invoicetracker.entity.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

}
