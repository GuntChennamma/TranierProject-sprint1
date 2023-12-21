package com.anp.trainerproject;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager; 
import jakarta.persistence.EntityTransaction;
public class TrainerDatabaseDAO {   // TrainerDatabaseDAO class 
private EntityManager em;
//Constructor to initialize TrainerDatabaseDAO with an EntityManager
public TrainerDatabaseDAO(final EntityManager em) {
		this.em = em; 
}
//Method to save a TrainerDatabase entity to the database
public void save(final TrainerDatabase trainer) {
EntityTransaction tx = null;
 try {   
	tx = em.getTransaction(); // Retruns the resource level entitytransaction
	if (!tx.isActive()) // It indicates whether the transaction is progress or not 
	  {
		tx.begin(); // It begins the transaction 
	}
// Merge the Trainer object with the persistence context
 TrainerDatabase mergedTrainer = em.merge(trainer);
	tx.commit();
	} catch (Exception e) { 
	e.printStackTrace(); // Gives complete information about exception
	}
	}
//Method to find a TrainerDatabase by its ID
public Optional<TrainerDatabase> findById(int id) { // a container object which may or may not contain non-null value is present 
	// returns true , if non-value is present returns false
TrainerDatabase t = em.find(TrainerDatabase.class, id);
		if (t != null) {
			return Optional.of(t);// returns optional non-null-value true 
		} else {
			return Optional.empty();// returns empty optional instance , no value present 
		}
	}
// Method to retrieve all Trainers from the database
	public List<TrainerDatabase> findAll() { // returns list of trainer records 

		List<TrainerDatabase> t1 = em.createQuery("from TrainerDatabase", TrainerDatabase.class).getResultList(); 

		return t1;
	}
// Method to update Trainer details based on ID
public void updateTrainer( int id, String newfirstName, String newLastName, int newsalary,String newemail, String newgender) {
EntityTransaction tx = null; 
	try {
	tx = em.getTransaction(); 

	if (!tx.isActive()) // It indicates the transaction is  in progress 
	{
	 tx.begin();
	}
	TrainerDatabase trainer = em.find(TrainerDatabase.class, id) ;
	if(trainer!=null) {
	trainer.setFirstName(newfirstName); // updating old value with new value 
	trainer.setLastName(newLastName);
	trainer.setSalary(newsalary);
    trainer.setEmail(newemail);
    trainer.setGender(newgender);
				
	em.merge(trainer); // update  the changes to the persistence context
	tx.commit(); // save transaction
			}	
			
		} catch (Exception e) {
			 e.printStackTrace();
		}		
	}
//Method to remove a Trainer entity from the database by ID
public void remove(int id) {  
		EntityTransaction tx = null; 
		TrainerDatabase t = em.find(TrainerDatabase.class, id);  
		
		try {
			tx = em.getTransaction(); 

			if (!tx.isActive()) 
			{
				tx.begin();
			}
// Remove the Trainer entity from the database
		em.remove(t);
		tx.commit();
		}
		catch (Exception e) {
			 e.printStackTrace();  
		}		
	}	
} 

