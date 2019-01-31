package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Course;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Lesson;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Question;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Task;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;

@Remote
public interface EventManagementServicesRemote {
	void assignQuestionToLesson(Question question, Lesson lesson);

	void assignLessonToCourse(Course course, Lesson lesson);

	void assignGuestToASpecialEvent(Participation participation, SpecialEvent specialEvent);

	void assignTaskToAnEvent(SpecialEvent specialEvent, Task task);

	SpecialEvent featuredSpecialEvent();

	List<Participation> UserNotification(User user);

	Participation UserParticipation(User user, Event event);

	Event UpdateRating(Event event);

	List<Event> findPublicEvents();

	List<Event> findByCountry(String c);

	List<Event> findByPrice(int i);

	List<Event> findBySector(String s);

	List<String> findAllNames();

	Event findByName(String n);

	List<SpecialEvent> findParticipationByUser(User u);

	List<SpecialEvent> findAllOwnerEvents(User u);

	SpecialEvent findMostVisitedEvent(User u);

	SpecialEvent findMostRatedEvent(User u);

	SpecialEvent findMostSubscribedEvent(User u);

	List<Participation> findEventParticipations(Event e);

	List<SpecialEvent> findAllPublicOwnerEvent(User u);
	 List<SpecialEvent> findFree();

}
