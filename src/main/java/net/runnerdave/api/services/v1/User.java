package net.runnerdave.api.services.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class User implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1628701721724865730L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="id")
	private String id;
	
	@XmlElement(name="name")
	private String name;
}
