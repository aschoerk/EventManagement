package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: UserScore
 *
 */
@Entity

public class UserScore implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int option1;
	private int option2;
	private int option3;
	private int option4;
	private int option5;
	private float score;
	@ManyToOne
	private User user;
	@ManyToOne
	private Question question;
	
	private static final long serialVersionUID = 1L;

	public UserScore() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getOption1() {
		return this.option1;
	}

	public void setOption1(int option1) {
		this.option1 = option1;
	}   
	public int getOption2() {
		return this.option2;
	}

	public void setOption2(int option2) {
		this.option2 = option2;
	}   
	public int getOption3() {
		return this.option3;
	}

	public void setOption3(int option3) {
		this.option3 = option3;
	}   
	public int getOption4() {
		return this.option4;
	}

	public void setOption4(int option4) {
		this.option4 = option4;
	}   
	public int getOption5() {
		return this.option5;
	}

	public void setOption5(int option5) {
		this.option5 = option5;
	}   
	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}
   
}
