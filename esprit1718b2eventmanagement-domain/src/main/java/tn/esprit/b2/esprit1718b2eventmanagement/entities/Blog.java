package tn.esprit.b2.esprit1718b2eventmanagement.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Blog
 *
 */
@Entity

public class Blog implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	private List<Post> posts;
	
	@OneToOne(cascade=CascadeType.MERGE)
	private Company company1;
	private static final long serialVersionUID = 1L;

	public Blog() {
		super();
	}   
	
	public Blog(String name, Company company1) {
		super();
		this.name = name;
		this.company1 = company1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public Company getCompany1() {
		return company1;
	}
	public void setCompany1(Company company1) {
		this.company1 = company1;
	}
	
	public void linkPostsToThisBlog(List<Post> posts){
		this.posts = posts;
		for(Post p : posts){
			p.setBlog(this);
		}
	}
	
	@Override
	public String toString() {
		return "Blog [id=" + id + ", name=" + name + ", company1=" + company1 + "]";
	}
}
