package com.alexmonjaraz.invoicetracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexmonjaraz.invoicetracker.entity.Invoice_Credit;

public interface InvoiceCreditRepo extends JpaRepository<Invoice_Credit, Integer> {

}
