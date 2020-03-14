package com.luv2code.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();

			// get the instructoer details object
			
			int theId = 3;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the instructor deatil
			System.out.println("delete instructorDetail"+ instructorDetail);
			//print the ascociate instructor
			
			System.out.println("delete instructo"+  instructorDetail.getInstructor());
			
			//remove the associated object reference
			//break biderectional link
			
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			//delete 
			session.delete(instructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch(Exception exe) {
			exe.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}





