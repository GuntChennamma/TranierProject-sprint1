package com.anp.trainerproject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class TrainingSessionDAO {

    private EntityManager em;

    public TrainingSessionDAO(EntityManager em) {
        this.em = em;
    }

    public void save(TrainingSession trainingSession) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }

            em.persist(trainingSession);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Optional<TrainingSession> findById(Long sessionId) {
        TrainingSession session = em.find(TrainingSession.class, sessionId);
        return Optional.ofNullable(session);
    }

    public List<TrainingSession> findAll() {
        return em.createQuery("FROM TrainingSession", TrainingSession.class).getResultList();
    }

    public void updateTrainingSession(TrainingSession trainingSession) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }

            em.merge(trainingSession);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void remove(Long sessionId) {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }

            TrainingSession session = em.find(TrainingSession.class, sessionId);
            if (session != null) {
                em.remove(session);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}

