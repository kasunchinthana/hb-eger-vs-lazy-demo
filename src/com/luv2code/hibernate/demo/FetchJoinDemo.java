package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class FetchJoinDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			session.beginTransaction();
			
			int theId = 1;
			//hibernate query with hql
			Query<Instructor> query =session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id =:theInstructorId",
					Instructor.class);
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor empInstructor = query.getSingleResult();
			System.out.println("kasun instrucctor "+ empInstructor);
			
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("kasun Done!");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}





