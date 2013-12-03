package com.digiplug.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = User.ENTITY_NAME)
@Table(name = User.ENTITY_NAME)
@NamedQueries(value = { @NamedQuery(name = User.QUERY_FIND_ALL, query = "select u from " + User.ENTITY_NAME + " u") })
// XXX : Annotation @XmlRootElement is required by CXF while generating the WADL
// for including the representation definition of the entity (XML Schema)
// XXX : If we attach a custom xsd to the WadlGenerator we can do without it !!!
// @XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = -3665866356536168935L;

	static final String ENTITY_NAME = "users";

	public static final String QUERY_FIND_ALL = "user_findAll";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String firstname;

	private String lastname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
