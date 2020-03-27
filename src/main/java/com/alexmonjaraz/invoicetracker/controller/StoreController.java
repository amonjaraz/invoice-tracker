package com.alexmonjaraz.invoicetracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "redirect:/dashboard/store/";
	}
	
	@GetMapping("/update")
	public String updateForm(@RequestParam("id") int id ,Model model) {
		Optional<Store> value = storeRepo.findById(id);
		Store store;
		if (value.isPresent()) { 
			store = value.get(); 
			model.addAttribute("store", store);
			return "store/create-form";
		}
		else return "redirect:/dashboard/store/";

	}
	
	@GetMapping("/{id}")
	public String getStore(@PathVariable int id ,Model model) {
		Optional<Store> value = storeRepo.findById(id);
		Store store;
		if (value.isPresent()) { 
			store = value.get(); 
			model.addAttribute("store", store);
			return "store/store-detail";
		}
		else return "redirect:/dashboard/store/";

	}
	
	
}
