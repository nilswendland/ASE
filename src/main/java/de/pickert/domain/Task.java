package de.pickert.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Task {
	private LocalDate dueDate;
	private String title;
	private String description;
	private Status status;
	private String responsible;
	private List<String> comments;

	public Task(String name, String description) {
	
		this.title = name;
		this.description = description;
		status = Status.NEW;
		dueDate = null;
		responsible="";
		comments= new ArrayList<>();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status newStatus) {
		this.status = newStatus;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		if (!responsible.equals("")) this.responsible = responsible;
	}

	public List<String> getComments() {
		return comments;
	}
	public String getComment(int position) {
		return comments.get(position);
	}
	public boolean addComment(String newComment) {
		return comments.add(newComment);

	}

	public void setComments(List<String> comments2) {
		this.comments=comments2;
	}

	public String toString() {
   return "title: "+this.title+"\n responsible: "+this.responsible+"\n description: "+this.description+"\n duedate: "+this.dueDate+"\n comments: "+this.comments.toString();
 }

}
