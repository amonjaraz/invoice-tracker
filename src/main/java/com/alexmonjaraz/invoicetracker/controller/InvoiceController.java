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

import com.alexmonjaraz.invoicetracker.DAO.InvoiceRepo;
import com.alexmonjaraz.invoicetracker.DAO.StoreRepo;
import com.alexmonjaraz.invoicetracker.entity.Invoice;
import com.alexmonjaraz.invoicetracker.entity.Store;

@Controller
@RequestMapping("/dashboard/invoice")
public class InvoiceController {

	@Autowired
	private StoreRepo storeRepo;
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@GetMapping("/")
	public String getStoreList(@RequestParam("storeId") int storeId ,Model model) {
		Optional<Store> storeOp = storeRepo.findById(storeId);
		if (storeOp.isPresent()) {
			Store store = storeOp.get();
			List<Invoice> invoices = store.getInvoices();
			model.addAttribute("storeId", storeId);
			model.addAttribute("invoices", invoices);
			return "invoice/index";
		}
		else return "invoice/index";
	}
	
	@GetMapping("/{id}")
	public String getDetails(@PathVariable("id") int id ,Model model) {
		Optional<Invoice> invoiceOp = invoiceRepo.findById(id);
		if (invoiceOp.isPresent()) {
			Invoice invoice = invoiceOp.get();
			model.addAttribute("invoice", invoice);
			return "invoice/invoice-details";
		}
		return "redirect:/dashboard/store/";
	}
	
	@GetMapping("/create")
	public String showAddForm(@RequestParam("storeId") int storeId,Model model) {
		model.addAttribute("storeId", storeId);
		model.addAttribute("invoice", new Invoice());
		return "invoice/create-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("invoice") Invoice invoice) {
		Optional<Store> storeOp = storeRepo.findById(1);
		if (storeOp.isPresent()) {
			Store store = storeOp.get();
			System.out.println(store.getInvoices().size()); 
			store.add(invoice);
			invoiceRepo.save(invoice);
			storeRepo.save(store);
			return "redirect:/dashboard/store/";
		}
		return "redirect:/dashboard/store/";
	}
	
	@GetMapping("/update")
	public String updateForm(@RequestParam("id") int id ,Model model) {
		Optional<Invoice> invoiceOp = invoiceRepo.findById(id);
		if (invoiceOp.isPresent()) {
			Invoice invoice = invoiceOp.get();
			model.addAttribute("invoice", invoice);
			return "invoice/create-form";
		}
		return "redirect:/dashboard/store/";
	}
	
}
