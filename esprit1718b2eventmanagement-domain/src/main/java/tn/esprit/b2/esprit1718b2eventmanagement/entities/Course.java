package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Course
 *
 */
@Entity

public class Course implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private String category;
	private File courseBanner;
	@OneToMany(mappedBy="course", cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	private List<Lesson> lessons;
	
	private static final long serialVersionUID = 1L;

	public Course() {
		super();
	}   
	
	public Course( String description, String category) {
		super();
		
		this.description = description;
		this.category = category;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public File getCourseBanner() {
		return courseBanner;
	}
	public void setCourseBanner(File courseBanner) {
		this.courseBanner = courseBanner;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public void linkLessonsToThisCourse(List<Lesson> lessons) {
		this.lessons=lessons;
		for (Lesson l : lessons) {
			l.setCourse(this);
		}
	}
}
