package net.task.model;

public class Task {
   
	private int id;
	private String name;
	private String description;
	private String status;
	private String date_limite;
	private int categorie_id;

	
	public Task(String name, String description, String status, String date_limite, int categorie_id) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.date_limite = date_limite;
		this.categorie_id = categorie_id;
	}	
	public Task(int id, String name, String description, String status, String date_limite, int categorie_id) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.date_limite = date_limite;
		this.categorie_id = categorie_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate_limite() {
		return date_limite;
	}
	public void setDate_limite(String date_limite) {
		this.date_limite = date_limite;
	}
	
	public int getCategorie_id() {
		return categorie_id;
	}
	public void setCategorie_id(int categorie_id) {
		this.categorie_id = categorie_id;
	}
	
}
