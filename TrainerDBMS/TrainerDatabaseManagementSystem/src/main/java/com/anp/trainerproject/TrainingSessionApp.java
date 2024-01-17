package com.anp.trainerproject;
 
 

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.Optional;

public class TrainingSessionApp {

    public static void main(String[] args) {
        EntityManagerFactory factory = null;

        try {
            factory = Persistence.createEntityManagerFactory("cs");
            EntityManager em = factory.createEntityManager();

            TrainingSessionDAO sessionDAO = new TrainingSessionDAO(em);

            // Creating TrainingSession objects
            TrainerDatabase trainer = em.find(TrainerDatabase.class, 1L);
            TrainingSession session1 = new TrainingSession(LocalDate.now(), "Java Basics", trainer);
            TrainingSession session2 = new TrainingSession(LocalDate.now().plusDays(1), "Database Fundamentals", trainer);

            // Saving TrainingSession objects to the database
            sessionDAO.save(session1);
            sessionDAO.save(session2);

            // Retrieving TrainingSession details based on ID
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

