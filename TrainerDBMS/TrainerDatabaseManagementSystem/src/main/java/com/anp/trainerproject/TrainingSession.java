package com.anp.trainerproject;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.ManyToOne;
	import java.time.LocalDate;

	@Entity
	public class TrainingSession {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long sessionId;

	    private LocalDate sessionDate;
	    private String sessionTopic;

	    @ManyToOne
	    private TrainerDatabase trainer;

	    // Constructors

	    public TrainingSession() {
	    }

	    public TrainingSession(LocalDate sessionDate, String sessionTopic, TrainerDatabase trainer) {
	        this.sessionDate = sessionDate;
	        this.sessionTopic = sessionTopic;
	        this.trainer = trainer;
	    }

	    // Getters and Setters

	    public Long getSessionId() {
	        return sessionId;
	    }

	    public void setSessionId(Long sessionId) {
	        this.sessionId = sessionId;
	    }

	    public LocalDate getSessionDate() {
	        return sessionDate;
	    }

	    public void setSessionDate(LocalDate sessionDate) {
	        this.sessionDate = sessionDate;
	    }

	    public String getSessionTopic() {
	        return sessionTopic;
	    }

	    public void setSessionTopic(String sessionTopic) {
	        this.sessionTopic = sessionTopic;
	    }

	    public TrainerDatabase getTrainer() {
	        return trainer;
	    }

	    public void setTrainer(TrainerDatabase trainer) {
	        this.trainer = trainer;
	    }

	    // Other methods

	    // Example method to get the duration of the training session
	    public int getTrainingSessionDurationInHours() {
	        // Your logic to calculate duration goes here
	        return 2; // Just an example value
	    }

	    // Example method to check if the training session is upcoming
	    public boolean isUpcomingSession() {
	        // Your logic to check if the session is upcoming goes here
	        LocalDate currentDate = LocalDate.now();
	        return sessionDate.isAfter(currentDate);
	    }

	    @Override
	    public String toString() {
	        return "TrainingSession [sessionId=" + sessionId + ", sessionDate=" + sessionDate +
	               ", sessionTopic=" + sessionTopic + ", trainer=" + trainer + "]";
	    }
	}



