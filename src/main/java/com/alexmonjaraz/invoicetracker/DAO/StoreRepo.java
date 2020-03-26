package com.alexmonjaraz.invoicetracker.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexmonjaraz.invoicetracker.entity.Store;

public interface StoreRepo extends JpaRepository<Store, Integer> {

}
