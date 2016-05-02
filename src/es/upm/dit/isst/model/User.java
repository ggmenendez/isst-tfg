package es.upm.dit.isst.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 2930233310258048774L;
	
	String username;
	int type; // 0 alumno, 1 profesor, 2 secretaria/coa
	public User(String username, int type) {
		this.username = username;
		this.type = type;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
