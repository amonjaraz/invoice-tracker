package com.alexmonjaraz.invoicetracker.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductRepo {

	private List<String> products = new ArrayList<>();
	
	public ProductRepo (){
		products.add("Gordias 4-Pack Regular");
		products.add("Gordias 2-Pack Nutella");
		products.add("Gordias 2-Pack Cajeta");
	}
	public List<String> getAll() {
		return products;
	}
}
