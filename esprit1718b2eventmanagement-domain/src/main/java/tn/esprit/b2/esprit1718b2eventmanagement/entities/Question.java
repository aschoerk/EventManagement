package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Question
 *
 */
@Entity

public class Question implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String statement;
	private String realAnswer;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String anotherAnswer;
	@ManyToOne
	private Lesson lesson;
	@OneToMany(mappedBy="question", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<UserScore> userScores;
	private static final long serialVersionUID = 1L;

	public Question() {
		super();
	}   
	
	public Question(String question, String realAnswer, String option1, String option2) {
		super();
		
		this.statement = question;
		this.realAnswer = realAnswer;
		this.option1 = option1;
		this.option2 = option2;
	}

	public int getId() {
		return this.id;
	}

	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public void setId(int id) {
		this.id = id;
	}   
	
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<UserScore> getUserScores() {
		return userScores;
	}

	public void setUserScores(List<UserScore> userScores) {
		this.userScores = userScores;
	}

	public String getRealAnswer() {
		return this.realAnswer;
	}

	public void setRealAnswer(String realAnswer) {
		this.realAnswer = realAnswer;
	}   
	public String getOption1() {
		return this.option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}   
	public String getOption2() {
		return this.option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}   
	public String getOption3() {
		return this.option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}   
	public String getOption4() {
		return this.option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}   
	public String getOption5() {
		return this.option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}   
	public String getAnotherAnswer() {
		return this.anotherAnswer;
	}

	public void setAnotherAnswer(String anotherAnswer) {
		this.anotherAnswer = anotherAnswer;
	}
   
}
