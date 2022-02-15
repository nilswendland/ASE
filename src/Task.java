import java.time.LocalDate;
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
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {

		if (dueDate.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Due Date must be in the future!");
		}
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
		this.responsible = responsible;
	}

	public List<String> getComments() {
		return comments;
	}

	public boolean addComment(String newComment) {
		return comments.add(newComment);

	}
}
