package org.jsp.controller;

import java.util.List;

import java.util.Scanner;

import org.jsp.dao.ProductDao;
import org.jsp.dao.UserDao;
import org.jsp.dto.Product;
import org.jsp.dto.ProductConfig;
import org.jsp.dto.User;
import org.jsp.dto.UserConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	static Scanner s = new Scanner(System.in);
	static ProductDao pDao;
	static UserDao uDao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		uDao = context.getBean(UserDao.class);
	}
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		pDao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.Register");
		System.out.println("2.Update");
		System.out.println("3.Verify user By Phone and password");
		System.out.println("4.Verify user By email and password");
		System.out.println("5.Add product");
		System.out.println("6.View Product By user id");
		System.out.println("7.View Product By Category");
		System.out.println("8.View Product By Brand");
		System.out.println("9.Delete product");
		System.out.println("10.Delete User");
		int choice = s.nextInt();
		switch (choice) {

		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			verifyusingPhoneandPassword();
			break;
		}
		case 4: {
			verifyusingEmailandPassword();
			break;
		}
		case 5: {
			addProduct();
			break;
		}
		case 6: {
			viewProductsByuserid();
			break;
		}
		case 7: {
			viewProductsByuserCategory();
			break;
		}
		case 8: {
			viewProductsByuserBrand();
			break;
		}
		case 9: {
			deletePoductById();
			break;
		}
		case 10: {
			deleteUserById();
			break;
		}
		}
	}

	public static void save() {
		System.out.println("Enter your name,email,phone and password to ");
		String name = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
		u.setPhone(phone);
		u = uDao.saveUser(u);
		System.out.println("You are registered with id :" + u.getId());
	}

	public static void update() {
		System.out.println("Enter the id");
		int id = s.nextInt();
		System.out.println("Enter the name,email,phone and password");
		String name = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		u = uDao.updateUser(u);
		System.out.println("Detail updated at Id : " + u.getId());
	}

	public static void verifyusingPhoneandPassword() {
		System.out.println("Enter the Phone and Password");
		long phone = s.nextLong();
		String password = s.next();
		User u = uDao.verifyUser(phone, password);
		System.out.println(u);
	}

	public static void verifyusingEmailandPassword() {
		System.out.println("Enter the Email and Password");
		String email = s.next();
		String password = s.next();
		User u = uDao.verifyUser(email, password);
		System.out.println(u);
	}

	public static void addProduct() {
		System.out.println("Enter your Id to add the product");
		int uid = s.nextInt();
		System.out.println("Enter the name,brand,category,price");
		String name = s.next();
		String brand = s.next();
		String category = s.next();
		double price = s.nextDouble();
		Product p = new Product();
		p.setName(name);
		p.setBrand(brand);
		p.setCategory(category);
		p.setPrice(price);
		p = pDao.saveProduct(p, uid);
		if (p != null) {
			System.out.println("Product added to the cart with id :" + p.getId());
		} else {
			System.err.println("wrong id");
		}
	}

	public static void viewProductsByuserid() {
		System.out.println("Enter your user id to display products");
		int id = s.nextInt();
		List<Product> products = pDao.findProductByUserId(id);
		if (products.size() > 0) {
			for (Product p : products) {
				System.out.println(p);
			}
		} else {
			System.err.println("There no such id");
		}
	}

	public static void viewProductsByuserCategory() {
		System.out.println("Enter the category");
		String category = s.next();
		List<Product> products = pDao.findProductByCategory(category);
		if (products.size() > 0) {
			for (Product p : products) {
				System.out.println(p);
			}
		} else {
			System.err.println("There no such category");
		}
	}

	public static void viewProductsByuserBrand() {
		System.out.println("Enter the Brand");
		String brand = s.next();
		List<Product> products = pDao.findProductByBrand(brand);
		if (products.size() > 0) {
			for (Product p : products) {
				System.out.println(p);
			}
		} else {
			System.err.println("There no such brand");
		}
	}

	public static void deletePoductById() {
		System.out.println("Enter the Product id");
		int id = s.nextInt();
		boolean b = pDao.deleteProduct(id);
		if (b == true) {
			System.out.println("Product deleted");
		} else {
			System.out.println("Product id invalid");
		}
	}

	public static void deleteUserById() {
		System.out.println("Enter the User id");
		int id = s.nextInt();
		boolean b = uDao.deleteUser(id);
		if (b == true) {
			System.out.println("User deleted");
		} else {
			System.out.println("User id invalid");
		}
	}
}