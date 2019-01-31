package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Lesson
 *
 */
@Entity

public class Lesson implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;
	private File lessonFile;
	@ManyToOne
	private Course course;
	@OneToMany(mappedBy="lesson", cascade = CascadeType.MERGE, fetch = FetchType.EAGER) 
	private List<Question> questions;
	private static final long serialVersionUID = 1L;   
	public Lesson() {
		super();
	}   
	
	public Lesson( String title, String content) {
		super();
		
		this.title = title;
		this.content = content;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public File getLessonFile() {
		return lessonFile;
	}
	public void setLessonFile(File lessonFile) {
		this.lessonFile = lessonFile;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void linkQuestionsToThisLesson(List<Question> questions) {
		this.questions= questions;
		for (Question q : questions) {
			q.setLesson(this);
		}
	}
}
