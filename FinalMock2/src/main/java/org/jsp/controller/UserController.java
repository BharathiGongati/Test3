package org.jsp.controller;

import java.util.Scanner;


import org.jsp.dao.DepartmentDao;
import org.jsp.dao.EmployeeDao;
import org.jsp.dto.Department;
import org.jsp.dto.Employee;
import org.jsp.dto.userConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	static Scanner s = new Scanner(System.in);
	static EmployeeDao edao;
	static DepartmentDao dao;
	
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(userConfiguration.class);
		dao=context.getBean(DepartmentDao.class);
		
		
	}
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(userConfiguration.class);
		edao=context.getBean(EmployeeDao.class);
		
		
	}
	

	public static void main(String[] args) {
		System.out.println("enter 1 to save dept");
		System.out.println("enter 2 to save emp");

		int id = s.nextInt();
		switch (id) {
		case 1: {
			savedept();
			break;
		}
		case 2: {
			saveemp();
			break;
		}
		}

	}

	private static void savedept() {
		System.out.println("enter location,desg");
		String loc = s.next();
		String desg = s.next();
		Department d = new Department();
		d.setLocation(loc);
		d.setDesg(desg);
		dao.saveDept(d);
		System.out.println("dept saved");

	}

	private static void saveemp() {
		Employee e=new Employee();
		System.out.println("enter the dept id ");
		int id=s.nextInt();
		System.out.println("enter phone name");
		long phone=s.nextLong();
		String name=s.next();
		e.setPhone(phone);
		e.setName(name);
		edao.saveemp(e, id);
		System.out.println("emp saved");
		
		
	}

}
