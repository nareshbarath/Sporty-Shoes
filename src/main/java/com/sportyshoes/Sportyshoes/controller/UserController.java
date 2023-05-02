package com.sportyshoes.Sportyshoes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sportyshoes.Sportyshoes.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sportyshoes.Sportyshoes.entity.*;

@RestController
public class UserController {

	@Autowired
	UserRepo ur;

	@GetMapping("/home")
	public ModelAndView userhome() {
		System.out.println("Homepage");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("homePage.html");
		return mv;
	}

	@GetMapping("/login")
	public ModelAndView userLogin() {
		System.out.println("User login");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login.html");
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView userRegister() {
		System.out.println("Register");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register.html");
		return mv;
	}

	@PostMapping("/dashboard")
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Dashboard");
		ModelAndView mv = new ModelAndView();

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Users user = new Users();
		user.setEmail(email);
		Example example = Example.of(user);
		Optional<Users> checkUser = ur.findOne(example);
		if (checkUser.isEmpty()) {
			mv.setViewName("login.html");
			return mv;
		}
		Users User = checkUser.get();

		String hashedPassword = User.getPassword();
		boolean passwordIsValid = bCryptPasswordEncoder.matches(password, hashedPassword);
		if (passwordIsValid == true) {
			String role = User.getRole();
			if (role.equals("Admin")) {
				mv.setViewName("admindashboard.html");
			} else {
				mv.setViewName("userdashboard.html");
			}

		} else
			mv.setViewName("login.html");
		return mv;
	}

	@PostMapping("/adduser")
	public String addUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Adding user");

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		String Hpassword = bCryptPasswordEncoder.encode(request.getParameter("password").toString());
		Users user = new Users(request.getParameter("name").toString(), request.getParameter("username").toString(),
				Hpassword, request.getParameter("role").toString(), request.getParameter("email").toString(),
				request.getParameter("phone").toString());
		Users newUser = ur.save(user);
		System.out.println(newUser);

		return "User registered successfully";
	}

	@GetMapping("/userlist")
	public ModelAndView userList() {
		System.out.println("Listing users");
		ModelAndView mv = new ModelAndView();

		List<Users> userList = ur.findAll();
		mv.addObject("userlist", userList);
		mv.setViewName("userlist.html");
		return mv;
	}

	@GetMapping("/password")
	public ModelAndView password() {
		System.out.println("Password");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("password.html");
		return mv;
	}

	@PostMapping("/passwordchange")
	public String passwordChange(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Dashboard");

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Users user = new Users();
		user.setEmail(email);
		Example example = Example.of(user);
		Optional<Users> checkUser = ur.findOne(example);
		if (checkUser.isEmpty()) {
			return "Incorrect email";
		}
		Users User = checkUser.get();

		String hashedPassword = User.getPassword();
		boolean passwordIsValid = bCryptPasswordEncoder.matches(password, hashedPassword);
		if (passwordIsValid == true) {
			String newPassword = bCryptPasswordEncoder.encode(request.getParameter("newpassword").toString());
			User.setPassword(newPassword);
			ur.save(User);
			return "Password changed successfully";
		} else
			return "Incorrect password";
	}

	@PostMapping("/searchuser")
	public ModelAndView searchuser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String name = request.getParameter("name");
		Users user = new Users();
		user.setName(name);
		Example example = Example.of(user);
		List<Users> findUsers = ur.findAll(example);
		mv.addObject("userlist", findUsers);
		mv.setViewName("userlist.html");
		return mv;
	}
}
