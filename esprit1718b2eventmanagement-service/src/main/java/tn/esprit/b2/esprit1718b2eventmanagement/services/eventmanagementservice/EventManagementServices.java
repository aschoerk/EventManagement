package tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Course;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Event;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Lesson;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Participation;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Question;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Task;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.services.CompanyRepServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.courseservices.CourseServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.eventservices.EventServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.lessonservices.LessonServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.particiationservice.ParticipationServiceLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.questionservices.QuestionServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.TaskServiceLocal;

/**
 * Session Bean implementation class EventManagementServices
 */
@Stateless
public class EventManagementServices implements EventManagementServicesLocal, EventManagementServicesRemote {

	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private QuestionServicesLocal questionServicesLocal;
	@EJB
	private LessonServicesLocal lessonServicesLocal;
	@EJB
	private CourseServicesLocal courseServicesLocal;
	@EJB
	EventServicesLocal eventServicesLocal;
	@EJB
	private ParticipationServiceLocal participationServiceLocal;
	@EJB
	private SpecialEventServicesLocal specialEventServicesLocal;
	@EJB
	private TaskServiceLocal taskServiceLocal;
	@EJB
	private CompanyRepServiceLocal companyRepServiceLocal;

	/**
	 * Default constructor.
	 */
	public EventManagementServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void assignQuestionToLesson(Question question, Lesson lesson) {
		List<Question> questionsOld = lesson.getQuestions();
		if (questionsOld != null) {
			questionsOld.add(question);
			lesson.linkQuestionsToThisLesson(questionsOld);
		} else {
			List<Question> newOne = new ArrayList<>();
			newOne.add(question);
			lesson.linkQuestionsToThisLesson(newOne);
		}

		lessonServicesLocal.update(lesson);
	}

	@Override
	public void assignLessonToCourse(Course course, Lesson lesson) {
		List<Lesson> lessonsOld = course.getLessons();
		if (lessonsOld != null) {
			lessonsOld.add(lesson);
			course.linkLessonsToThisCourse(lessonsOld);
		} else {
			List<Lesson> newOne = new ArrayList<>();
			newOne.add(lesson);
			course.linkLessonsToThisCourse(newOne);
		}

		courseServicesLocal.update(course);
	}

	@Override
	public void assignGuestToASpecialEvent(Participation participation, SpecialEvent specialEvent) {

		participation.setEvent(eventServicesLocal.find(specialEvent.getId()));
		participationServiceLocal.update(participation);
		sendMail(participation.getMessage());

	}

	@Override
	public void assignTaskToAnEvent(SpecialEvent specialEvent, Task task) {
		task.setSpecialEvent(specialEventServicesLocal.find(specialEvent.getId()));
		taskServiceLocal.update(task);
	}

	public void sendMail(String msgText) {

		final String username = "pdev.esprit.2018@gmail.com";
		final String password = "pdevesprit2018";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mohamedbehaeddine.frigui@esprit.tn"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("mohamedbehaeddine.frigui@gmail.com"));
			message.setSubject("Event Invitation");

			message.setText(msgText);
			Transport.send(message);

		} catch (MessagingException e) {
			System.out.println("nope :(");
		}
	}

	@Override
	public SpecialEvent featuredSpecialEvent() {
		SpecialEvent specialEvent = null;
		List<SpecialEvent> specialEvents = new ArrayList<>();
		specialEvents = (ArrayList<SpecialEvent>) entityManager
				.createQuery("select s from SpecialEvent as s where s.startDate = CURRENT_DATE", SpecialEvent.class)
				.getResultList();
		Random rand = new Random();
		if (specialEvents.size() != 0 && !specialEvents.isEmpty()) {
			System.out.println(specialEvents.size() - 1);
			if (specialEvents.size() - 1 >= 1) {
				specialEvent = specialEvents.get(rand.nextInt(specialEvents.size() - 1));
			}
			if (specialEvents.size() == 1) {
				specialEvent = specialEvents.get(specialEvents.size() - 1);
			}
		}
		return specialEvent;
	}

	@Override
	public List<Participation> UserNotification(User user) {
		List<Participation> participations = new ArrayList<>();
		for (Participation p : user.getParticipations()) {
			if (p.getStatus() == 1) {
				participations.add(p);
			}
		}
		return participations;

	}

	@Override
	public Participation UserParticipation(User user, Event event) {
		Participation participation = new Participation();
		List<Participation> participations = (List<Participation>) entityManager
				.createQuery("select p from Participation as p where p.user = :u and p.event = :e", Participation.class)
				.setParameter("u", user).setParameter("e", event).getResultList();
		if (!participations.isEmpty()) {
			return participations.get(0);
		}

		return participation;

	}

	@Override
	public Event UpdateRating(Event event) {

		List<Participation> participations = new ArrayList<>();
		participations = (ArrayList<Participation>) entityManager
				.createQuery("select p from Participation as p where p.rate!=0 and p.event=:e", Participation.class)
				.setParameter("e", event).getResultList();
		float rating = 0;
		for (Participation participation : participations) {
			rating = rating + participation.getRate();
		}
		rating = rating / participations.size();
		event.setRating(rating);
		return eventServicesLocal.update(event);
	}

	@Override
	public List<Event> findPublicEvents() {

		List<SpecialEvent> events = (List<SpecialEvent>) entityManager
				.createQuery("select s from SpecialEvent as s ORDER BY s.startDate ASC", SpecialEvent.class)
				.getResultList();

		List<Event> pevents = new ArrayList<>();
		for (SpecialEvent e : events) {
			if (e.getPrivacy() == 0) {
				pevents.add(eventServicesLocal.find(e.getId()));
			}
		}
		return pevents;

	}

	@Override
	public List<Event> findByCountry(String c) {
		List<SpecialEvent> events = new ArrayList<>();
		events = specialEventServicesLocal.findAll();
		List<Event> pevents = new ArrayList<>();
		for (SpecialEvent e : events) {
			if (e.getAddress().contains(c) && e.getPrivacy() == 0) {
				pevents.add(eventServicesLocal.find(e.getId()));
			}
		}
		return pevents;
	}

	@Override
	public List<Event> findByPrice(int i) {
		List<SpecialEvent> events = new ArrayList<>();
		if (i == 0) {
			events = (List<SpecialEvent>) entityManager
					.createQuery("select s from SpecialEvent as s ORDER BY s.price ASC", SpecialEvent.class)
					.getResultList();
		} else {
			events = (List<SpecialEvent>) entityManager
					.createQuery("select s from SpecialEvent as s ORDER BY s.price DESC", SpecialEvent.class)
					.getResultList();

		}

		List<Event> pevents = new ArrayList<>();
		for (SpecialEvent e : events) {
			if (e.getPrivacy() == 0) {
				pevents.add(eventServicesLocal.find(e.getId()));
			}
		}
		return pevents;
	}
	@Override
	public List<SpecialEvent> findFree() {
		List<SpecialEvent> events = new ArrayList<>();
		
			events= specialEventServicesLocal.findAll();
	
		List<SpecialEvent> pevents = new ArrayList<>();
		for (SpecialEvent e : events) {
			if (e.getPrivacy() == 0 && e.getPrice() == 0) {
				pevents.add(e);
			}
		}
		return pevents;
	}
	@Override
	public List<Event> findBySector(String s) {
		List<SpecialEvent> events = new ArrayList<>();
		events = specialEventServicesLocal.findAll();
		List<Event> pevents = new ArrayList<>();
		for (SpecialEvent e : events) {
			if (e.getPrivacy() == 0 && e.getEventSector().contains(s)) {
				pevents.add(eventServicesLocal.find(e.getId()));
			}
		}
		return pevents;
	}

	@Override
	public List<String> findAllNames() {
		List<SpecialEvent> events = new ArrayList<>();
		events = specialEventServicesLocal.findAll();
		List<String> pevents = new ArrayList<>();
		for (SpecialEvent e : events) {
			if (e.getPrivacy() == 0) {
				pevents.add(eventServicesLocal.find(e.getId()).getName());
			}
		}
		return pevents;
	}

	@Override
	public Event findByName(String n) {
		List<SpecialEvent> events = new ArrayList<>();
		events = specialEventServicesLocal.findAll();
		Event pevent = new Event();
		for (SpecialEvent e : events) {
			if (e.getPrivacy() == 0 && e.getName().equals(n)) {
				pevent = e;
			}
		}
		return pevent;
	}

	@Override
	public List<SpecialEvent> findParticipationByUser(User u) {
		List<Participation> participations = new ArrayList<>();
		participations = participationServiceLocal.findAll();
		List<SpecialEvent> pevents = new ArrayList<>();
		for (Participation p : participations) {
			if (p.getUser().getId() == u.getId()) {
				pevents.add(specialEventServicesLocal.find(p.getEvent().getId()));
				
			}
		}
		return pevents;
	}

	@Override
	public List<SpecialEvent> findAllOwnerEvents(User u) {
		List<SpecialEvent> specialEvents = new ArrayList<>();
		specialEvents = specialEventServicesLocal.findAll();
		List<SpecialEvent> spresult = new ArrayList<>();
		for (SpecialEvent s : specialEvents) {
			if (s.getCompanyRep().getId() == u.getId()) {
				spresult.add(s);
			}
		}
		return spresult;
	}

	@Override
	public SpecialEvent findMostVisitedEvent(User u) {
		SpecialEvent specialEvent = new SpecialEvent();
		try {
			List<Integer> s = (List<Integer>) entityManager.createQuery(
					"select e.id from SpecialEvent as e where e.visits =(select max(e.visits) from SpecialEvent as e where e.companyRep.id =:myOrg) and e.companyRep.id =:myOrg")
					.setParameter("myOrg", u.getId()).getResultList();
			if (!s.isEmpty()) {
				specialEvent = specialEventServicesLocal.find(s.get(0));
			}

			return specialEvent;
		} catch (NoResultException e) {
			return specialEvent;
		}

	}

	@Override
	public SpecialEvent findMostRatedEvent(User u) {
		SpecialEvent specialEvent = new SpecialEvent();
		try {
			int specialEvents = (int) entityManager.createQuery(
					"select  e.id from SpecialEvent as e where e.rating = (select max(e.rating) from SpecialEvent as e where e.companyRep.id =:myOrg) and e.companyRep.id =:myOrg")
					.setParameter("myOrg", (u.getId())).getSingleResult();

			if (specialEvents > 0) {
				specialEvent = specialEventServicesLocal.find(specialEvents);
			}
			return specialEvent;
		} catch (NoResultException e) {
			return specialEvent;
		}

	}

	@Override
	public SpecialEvent findMostSubscribedEvent(User u) {
		List<Event> events = new ArrayList<>();
		SpecialEvent specialEvent = new SpecialEvent();

		try {
			int max = 0;
			Event event = new Event();
			events = (List<Event>) entityManager
					.createQuery("select e from Event as e where e.companyRep =:c", Event.class).setParameter("c", u)
					.getResultList();
			for (Event ev : events) {
				List<Participation> participations = new ArrayList<>();
				participations = findEventParticipations(ev);
				if (participations.size() > max) {
					max = participations.size();
					event = ev;
				}
			}
			specialEvent = specialEventServicesLocal.find(event.getId());

			return specialEvent;
		} catch (NoResultException e) {
			return specialEvent;
		}

	}

	@Override
	public List<Participation> findEventParticipations(Event e) {
		List<Participation> participations = new ArrayList<>();
		participations = (List<Participation>) entityManager
				.createQuery("select p from Participation as p where p.event =:ev", Participation.class)
				.setParameter("ev", e).getResultList();
		return participations;
	}

	@Override
	public List<SpecialEvent> findAllPublicOwnerEvent(User u) {
		List<SpecialEvent> specialEvents = new ArrayList<SpecialEvent>();
		List<SpecialEvent> sEvents = findAllOwnerEvents(u);
		for (SpecialEvent s : sEvents) {

			if (s.getPrivacy() == 0) {
				specialEvents.add(s);
			}
		}
		return specialEvents;
	}
}
