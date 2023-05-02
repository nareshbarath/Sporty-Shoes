package com.sportyshoes.Sportyshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.sportyshoes.Sportyshoes.entity.Products;
import com.sportyshoes.Sportyshoes.repository.ProductRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ProductController {
	@Autowired
	ProductRepo pr;

	@GetMapping("/productmaster")
	public ModelAndView productmaster() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("productmaster.html");
		return mv;
	}

	@GetMapping("/addproduct")
	public ModelAndView addproduct() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addproduct.html");
		return mv;
	}

	@GetMapping("/productslist")
	public ModelAndView productslist() {
		System.out.println("Listing products");
		ModelAndView mv = new ModelAndView();
		List<Products> productList = pr.findAll();
		System.out.println(productList);
		mv.addObject("productlist", productList);
		mv.setViewName("productslist.html");
		return mv;
	}

	@GetMapping("/deleteproduct")
	public ModelAndView deleteproduct() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deleteproduct.html");
		return mv;
	}

	@PostMapping("deleteproduct")
	public String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong((String) request.getParameter("id"));
		Optional findProduct = pr.findById(id);
		Products product = (Products) findProduct.get();
		pr.delete(product);
		return "Product deleted successfully";
	}

	@PostMapping("/createproduct")
	public String createproduct(HttpServletRequest request, HttpServletResponse response) {
		Products product = new Products(request.getParameter("name").toString(),
				Long.parseLong(request.getParameter("price").toString()), request.getParameter("category").toString(),
				request.getParameter("url").toString());
		Products newProduct = pr.save(product);
		System.out.println(newProduct);
		return "Product added successfully";
	}

	@GetMapping("/order")
	public ModelAndView order() {
		ModelAndView mv = new ModelAndView();
		List<Products> productList = pr.findAll();
		mv.addObject("productlist", productList);
		mv.setViewName("order.html");
		return mv;
	}
}
