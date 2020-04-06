package com.alexmonjaraz.invoicetracker.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexmonjaraz.invoicetracker.DAO.InvoiceCreditRepo;
import com.alexmonjaraz.invoicetracker.DAO.InvoiceItemRepo;
import com.alexmonjaraz.invoicetracker.DAO.InvoiceRepo;
import com.alexmonjaraz.invoicetracker.DAO.ProductRepo;
import com.alexmonjaraz.invoicetracker.DAO.StoreRepo;
import com.alexmonjaraz.invoicetracker.DTO.InvoiceDTO;
import com.alexmonjaraz.invoicetracker.Enum.Terms;
import com.alexmonjaraz.invoicetracker.entity.Invoice;
import com.alexmonjaraz.invoicetracker.entity.Invoice_Credit;
import com.alexmonjaraz.invoicetracker.entity.Invoice_Item;
import com.alexmonjaraz.invoicetracker.entity.Store;

@Controller
@RequestMapping("/dashboard/invoice")
public class InvoiceController {

	@Autowired
	private StoreRepo storeRepo;
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private InvoiceItemRepo invoiceItemRepo;
	
	@Autowired
	private InvoiceCreditRepo invoiceCreditRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
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
			int storeId = invoice.getStore().getId();
			model.addAttribute("storeId", storeId);
			//get invoice details and credit items
			
			model.addAttribute("invoiceItems", invoice.getInvoiceItems());
			model.addAttribute("creditItems", invoice.getCreditItems());
			
			return "invoice/invoice-details";
		}
		return "redirect:/dashboard/store/";
	}
	
	@GetMapping("/create")
	public String showAddForm(@RequestParam("storeId") int storeId,Model model) {
		model.addAttribute("storeId", storeId);
		
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setInvoice(new Invoice());
		
		for (String product : productRepo.getAll()) {
			invoiceDTO.addInventoryItem(new Invoice_Item(product));
			invoiceDTO.addCreditItem(new Invoice_Credit(product));
		}
		
		model.addAttribute("invoiceDTO", invoiceDTO);
		model.addAttribute("Terms", Terms.values());
		return "invoice/create-form";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("storeId") int storeId, @Valid @ModelAttribute("invoiceDTO") InvoiceDTO invoiceDTO, 
						BindingResult bindingResult, Model model, Authentication authentication) {
		model.addAttribute("storeId", storeId);
		Optional<Store> storeOp = storeRepo.findById(storeId);
		if (storeOp.isPresent()) {
			Store store = storeOp.get();
			invoiceDTO.getInvoice().setCreatedBy(authentication.getName());
			if(!bindingResult.hasErrors()) {
				
				//discard invoice items with quantity zero.
				invoiceDTO.getInvoiceItems().removeIf(x-> x.getQuantity() == 0);
				invoiceDTO.getCreditItems().removeIf(x-> x.getQuantity()==0);
				//save invoice and items
				store.getInvoices();
				
				store.add(invoiceDTO.getInvoice());
				invoiceRepo.save(invoiceDTO.getInvoice());
				
				if (invoiceDTO.getInvoiceItems().size() > 0) {
					invoiceDTO.getInvoiceItems().forEach(x-> invoiceDTO.getInvoice().addInvoiceItem(x));
					invoiceItemRepo.saveAll(invoiceDTO.getInvoice().getInvoiceItems());
				}
				
				if (invoiceDTO.getCreditItems().size() > 0) {
					invoiceDTO.getCreditItems().forEach(x->invoiceDTO.getInvoice().addCreditItem(x));
					invoiceCreditRepo.saveAll(invoiceDTO.getInvoice().getCreditItems());
				}
				
				storeRepo.save(store);
				//redirect to store list
				return "redirect:/dashboard/store/";
			} 
			else return "invoice/create-form";
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
