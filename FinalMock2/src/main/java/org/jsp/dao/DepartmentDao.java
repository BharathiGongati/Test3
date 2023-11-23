package org.jsp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DepartmentDao {
	@Autowired
	EntityManager manager;
	public Department saveDept(Department dept) {
		EntityTransaction t=manager.getTransaction();
		manager.persist(dept);
		t.begin();
		t.commit();
		return dept;
		
	}
	public Department updateDept(Department dept) {
		EntityTransaction t=manager.getTransaction();
		manager.merge(dept);
		t.begin();
		t.commit();
		return dept;
		
	}
	public boolean deleteDept(int id) {
		EntityTransaction t=manager.getTransaction();
		Department d=manager.find(Department.class,id);
		if(d!=null) {
			manager.remove(d);
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}
	public Department findDepartmentById(int id) {
			return manager.find(Department.class, id);
		}
	}
	
	


