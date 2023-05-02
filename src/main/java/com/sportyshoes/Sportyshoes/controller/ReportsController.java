package com.sportyshoes.Sportyshoes.controller;

import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.sportyshoes.Sportyshoes.repository.ProductRepo;
import com.sportyshoes.Sportyshoes.repository.ReportsRepo;
import com.sportyshoes.Sportyshoes.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportsController {
	@Autowired
	ProductRepo pr;

	@Autowired
	ReportsRepo rr;

	@PostMapping("/orderproduct")
	public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ModelAndView mv = new ModelAndView();
		Long id = Long.parseLong((String) request.getParameter("productId"));
		Long qty = Long.parseLong((String) request.getParameter("qty"));
		Optional findProduct = pr.findById(id);
		Products product = (Products) findProduct.get();
		Double totalPrice = (double) (qty * product.getPrice());

		Date currentDate = new Date();
		String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
		Reports newReport = new Reports(product.getId(), product.getName(), product.getCategory(),
				request.getParameter("name"), request.getParameter("email"), qty, totalPrice, date);
		rr.save(newReport);

		mv.addObject("id", newReport.getId());
		mv.addObject("totalPrice", newReport.getTotalPrice());
		mv.setViewName("paymet.html");
		return mv;
	}

	@PostMapping("/paymentsuccesss")
	public String paymentsuccesss() {
		return "Payment success";
	}

	@GetMapping("/reports")
	public ModelAndView reports() {
		ModelAndView mv = new ModelAndView();
		List<Reports> reportList = rr.findAll();
		mv.addObject("reportList", reportList);

		mv.setViewName("listreports.html");
		return mv;
	}

	@PostMapping("/searhreports")
	public ModelAndView searhreports(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ModelAndView mv = new ModelAndView();
		String category = request.getParameter("category");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
		Reports reports = new Reports();
		reports.setDate(date);
		reports.setProductCategory(category);
		Example example = Example.of(reports);
		List<Reports> reportList = rr.findAll(example);

		mv.addObject("reportList", reportList);
		mv.setViewName("listreports.html");
		return mv;
	}
}
