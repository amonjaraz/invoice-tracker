package com.alexmonjaraz.invoicetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexmonjaraz.invoicetracker.DAO.StoreRepo;
import com.alexmonjaraz.invoicetracker.entity.Store;

@Controller
@RequestMapping("/dashboard/store")
public class StoreController {

	@Autowired
	private StoreRepo storeRepo;
	
	@GetMapping("/")
	public String getStoreList(Model model) {
		List<Store> allStores =  storeRepo.findAll();
		System.out.println(allStores);
		model.addAttribute("stores", allStores);
		return "store/index";
	}
	
	@GetMapping("/create")
	public String showAddForm(Model model) {
		model.addAttribute("store", new Store());
		return "store/create-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("store") Store store) {
		storeRepo.save(store);
		return "redirect:/";
	}
}
