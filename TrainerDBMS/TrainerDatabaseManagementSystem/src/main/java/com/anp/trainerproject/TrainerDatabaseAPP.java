package com.anp.trainerproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TrainerdatabaseAPP {

    public static void main(String[] args) {
        EntityManagerFactory factory = null;

        try {
            factory = Persistence.createEntityManagerFactory("cs");
            EntityManager em = factory.createEntityManager();

            // TrainerDatabaseAPP logic
            TrainerDatabaseDAO tDAO = new TrainerDatabaseDAO(em);

            System.out.println("------WELCOME TO TRAINER DATABASE  MANAGEMENT SYSTEM ------");

            // Creating TrainerDatabase objects
            TrainerDatabase t1 = new TrainerDatabase(1, "Navya", "Sree", 50000, "navya@email.com", "Female");
            TrainerDatabase t2 = new TrainerDatabase(2, "Raju", "Sagar", 80000, "raj@email.com", "Male");
            TrainerDatabase t3 = new TrainerDatabase(3, "Sravan", "Patel", 60000, "sravan@email.com", "Male");
            TrainerDatabase t4 = new TrainerDatabase(4, "Yamini", "Yala", 45000, "yamini@email.com", "Female");
            TrainerDatabase t5 = new TrainerDatabase(5, "Bhavani", "Varahalu", 90000, "bhavani@email.com", "Female");
            TrainerDatabase t6 = new TrainerDatabase(6, "Neradi", "Sandeep", 70000, "sandeep@email.com", "Male");
            TrainerDatabase t7 = new TrainerDatabase(7, "Nikitha", "yadav", 30000, "nikki@email.com", "Female");
            TrainerDatabase t8 = new TrainerDatabase(8, "Niharika", "Goud", 65000, "niha@email.com", "Female");

            // Saving TrainerDatabase objects to the database
            tDAO.save(t1);
            tDAO.save(t2);
            tDAO.save(t3);
            tDAO.save(t4);
            tDAO.save(t5);
            tDAO.save(t6);
            tDAO.save(t7);
            tDAO.save(t8);

            System.out.println("Data added successfully to TrainerDatabase");
            System.out.println("--------------------------");

            // Retrieving TrainerDatabase details based on ID
            System.out.println("TrainerDatabase details based on the id:");
            Optional<TrainerDatabase> trainerById = tDAO.findById(1);
            System.out.println(trainerById.orElse(null));
            System.out.println("--------------------------");

            // Retrieving all Trainer details
            System.out.println("All Details of TrainerDatabase ");
            List<TrainerDatabase> allTrainers = tDAO.findAll();
            System.out.println(allTrainers);
            System.out.println("------------------");

            // Updating TrainerDatabase details
            int newId = 1;
            String newFirstName = "Laxmi";
            String newLastName = "Gunti";
            int salary = 50000;
            String newEmail = "laxmi@gmail.com";
            String newGender = "Female";
            tDAO.updateTrainer(newId, newFirstName, newLastName, salary, newEmail, newGender);
            System.out.println("Data updated successfully to the TrainerDatabase");
            System.out.println("------------------");

            // Removing a Trainer based on ID
            System.out.println("Removing TrainerDatabase based on the id:");
            tDAO.remove(3);
            System.out.println("3rd record is removed from TrainerDatabase");
            System.out.println("------------------");

            // TrainingSessionApp logic
            TrainingSessionDAO sessionDAO = new TrainingSessionDAO(em);

            // Creating TrainingSession objects
            TrainerDatabase trainerForSessions = em.find(TrainerDatabase.class, 1L);
            TrainingSession session1 = new TrainingSession(LocalDate.now(), "Java Basics", trainerForSessions);
            TrainingSession session2 = new TrainingSession(LocalDate.now().plusDays(1), "Database Fundamentals", trainerForSessions);

            // Saving TrainingSession objects to the database
            sessionDAO.save(session1);
            sessionDAO.save(session2);
            TrainingSession session3 = new TrainingSession(LocalDate.now().plusDays(2), "Web Development", trainerForSessions);
            TrainingSession session4 = new TrainingSession(LocalDate.now().plusDays(3), "Advanced Java", trainerForSessions);
            TrainingSession session5 = new TrainingSession(LocalDate.now().plusDays(4), "Spring Framework", trainerForSessions);
            TrainingSession session6 = new TrainingSession(LocalDate.now().plusDays(5), "Database Optimization", trainerForSessions);

            sessionDAO.save(session3);
            sessionDAO.save(session4);
            sessionDAO.save(session5);
            sessionDAO.save(session6);

            // Retrieving TrainingSession details based on ID
            System.out.println("TrainingSession details based on the id:");
            Optional<TrainingSession> sessionById = sessionDAO.findById(session1.getSessionId());
            System.out.println(sessionById.orElse(null));

            // Retrieving all TrainingSession details
            System.out.println("All Details of TrainingSessions ");
            System.out.println(sessionDAO.findAll());

            // Updating TrainingSession details
            session1.setSessionTopic("Advanced Java");
            sessionDAO.updateTrainingSession(session1);

            // Removing a TrainingSession based on ID
            sessionDAO.remove(session2.getSessionId());
            System.out.println("TrainingSession removed");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
