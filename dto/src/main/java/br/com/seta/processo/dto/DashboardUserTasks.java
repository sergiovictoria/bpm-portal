package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.List;

public class DashboardUserTasks implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private List<HumanTask> tasks;
	private Integer totalTasks;

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	public final List<HumanTask> getTasks() {
		return tasks;
	}

	public final void setTasks(List<HumanTask> tasks) {
		this.tasks = tasks;
	}

	public Integer getTotalTasks() {
		totalTasks = this.getTasks().size();
		return totalTasks;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DashboardUserTasks [");
		if (user != null) {
			builder.append("user=");
			builder.append(user);
			builder.append(", ");
		}
		if (tasks != null) {
			builder.append("tasks=");
			builder.append(tasks);
			builder.append(", ");
		}
		if (totalTasks != null) {
			builder.append("totalTasks=");
			builder.append(totalTasks);
		}
		builder.append("]");
		return builder.toString();
	}

}
