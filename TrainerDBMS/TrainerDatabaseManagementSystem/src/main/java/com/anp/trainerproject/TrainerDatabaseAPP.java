package com.anp.trainerproject;
import java.util.List;
import java.util.Optional;
import org.hibernate.HibernateException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
public class TrainerDatabaseAPP {   // TrainerDatabaseAPP class
public static void main(String[] args) {	
EntityManagerFactory factory = null;		
try { // connecting to the database using the persistence unit  
// Creating an EntityManagerFactory using the persistence unit named "cs"		
factory  = Persistence.createEntityManagerFactory("cs");
EntityManager em = factory.createEntityManager();					
System.out.println("------WELCOME TO TRAINER DATABASE  MANAGEMENT SYSTEM ------");
// Creating TrainerDatabase objects		
TrainerDatabase t1 = new TrainerDatabase(1, "Navya", "Sree", 50000,"navya@email.com",  "Female");
TrainerDatabase t2 = new TrainerDatabase(2, "Raju", "Sagar",  80000,"raj@email.com",  "Male");
TrainerDatabase t3 = new TrainerDatabase(3, "Sravan", "Patel", 60000,"sravan@email.com", "Male");
TrainerDatabase t4 = new TrainerDatabase(4, "Yamini", "Yala", 45000,"yamini@email.com", "Female");
TrainerDatabase t5 = new TrainerDatabase(5, "Bhavani", "Varahalu", 90000,"bhavani@email.com", "Female");
TrainerDatabase t6 = new TrainerDatabase(6, "Neradi", "Sandeep", 70000,"sandeep@email.com", "Male");
TrainerDatabase t7 = new TrainerDatabase(7, "Nikitha", "yadav", 30000,"nikki@email.com", "Female");
TrainerDatabase t8= new TrainerDatabase(8, "Niharika", "Goud", 65000,"niha@email.com", "Female");

TrainerDatabaseDAO tDAO = new TrainerDatabaseDAO(em);
//Saving TrainerDatabase objects to the database
tDAO.save(t1);
tDAO.save(t2);
tDAO.save(t3);
tDAO.save(t4);
tDAO.save(t5);	
tDAO.save(t6);
tDAO.save(t7);
tDAO.save(t8);
System.out.println(" Data added successfully to TrainerDatabase");
System.out.println("--------------------------");
//Retrieving TrainerDatabase details based on ID		
System.out.println(" TrainerDatabase  details based on the id :");
Optional<TrainerDatabase> TrainerById = tDAO.findById(1);
System.out.println(TrainerById);		 
System.out.println("--------------------------");
//Retrieving all Trainer details		
System.out.println(" All Details of TrainerDatabase ");	 
List<TrainerDatabase> alltr = tDAO.findAll();
System.out.println(alltr);		
System.out.println("------------------");
//Updating TrainerDatabase details
int  newid = 1;
String newfirstName ="Laxmi" ;
String newLastName = "Gunti" ;
int salary =  50000 ;
String newemail = "laxmi@gmail.com";
String newgender  ="Female";
tDAO.updateTrainer(newid, newfirstName, newLastName,salary, newemail, newgender);
System.out.println("Data updated sucessfully to the TrainerDatabase");		
System.out.println("------------------"); 
// Removing a Trainer based on ID
System.out.println("Removeing TrainerDatabase based on the id :");
tDAO.remove(3);
System.out.println("3rd record is removed from TrainerDatabase");	
		}
		catch (HibernateException e) {
			 e.printStackTrace();
		}
		catch (Exception e) {
		 e.printStackTrace();
		}

	}
	}

