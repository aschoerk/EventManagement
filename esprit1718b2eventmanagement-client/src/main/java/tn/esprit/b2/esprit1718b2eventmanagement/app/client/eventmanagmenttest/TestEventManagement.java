package tn.esprit.b2.esprit1718b2eventmanagement.app.client.eventmanagmenttest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Course;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Lesson;
import tn.esprit.b2.esprit1718b2eventmanagement.entities.Question;

import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.courseservices.CourseServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.lessonservices.LessonServicesRemote;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.questionservices.QuestionServicesRemote;

public class TestEventManagement {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		Context context = new InitialContext();
		EventManagementServicesRemote eventManagementServicesRemote = 
				(EventManagementServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/EventManagementServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.EventManagementServicesRemote");
		CourseServicesRemote courseServicesRemote = 
				(CourseServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/CourseServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.courseservices.CourseServicesRemote");
		LessonServicesRemote lessonServicesRemote = 
				(LessonServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/LessonServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.lessonservices.LessonServicesRemote");
		QuestionServicesRemote questionServicesRemote = 
				(QuestionServicesRemote)context.lookup("esprit1718b2eventmanagement-ear/esprit1718b2eventmanagement-service/QuestionServices!tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.questionservices.QuestionServicesRemote");
		questionServicesRemote.update(new Question("qu", "realAnswer", "option1", "option2"));
		questionServicesRemote.update(new Question("ffff", "real", "option1", "option2"));
		
		lessonServicesRemote.update(new Lesson("desc2", "cate2")); 
		lessonServicesRemote.update(new Lesson("title", "cont"));
		
		courseServicesRemote.update(new Course("desc1", "cate1"));
	 
		
		Question question = questionServicesRemote.find(23);
		Question question1 = questionServicesRemote.find(24);
		
		Lesson lesson = lessonServicesRemote.find(23);
		Lesson lesson1 = lessonServicesRemote.find(24);
		
		Course course = courseServicesRemote.find(12);
	
		
		
		eventManagementServicesRemote.assignQuestionToLesson(question1, lesson1);
	
		eventManagementServicesRemote.assignQuestionToLesson (question,lesson);
			eventManagementServicesRemote.assignLessonToCourse( course,lesson1);
		eventManagementServicesRemote.assignLessonToCourse(course, lesson);
		
	

	}

}
