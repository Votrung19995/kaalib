/**
 * 
 */
package com.tma.iotgroup.kaamodels;

/**
 * @author vlmt
 *
 */
public class User {
	private String id;
	private String username;
	private String externalUid;
	private String tenantId;
	private String author;
	private String firstname;
	private String lastname;
	private String email;

	public User(String username, String firstname, String lastname, String email, String author) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.author = author;
	}

	public User(String username, String firstname, String lastname, String email, Authority author) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.author = author.TENANT_USER;
	}
    
	
	

	public User(String id, String username, String externalUid, String tenantId, String author, String firstname,String lastname, String email) {
		this.id = id;
		this.username = username;
		this.externalUid = externalUid;
		this.tenantId = tenantId;
		this.author = author;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExternalUid() {
		return externalUid;
	}

	public void setExternalUid(String externalUid) {
		this.externalUid = externalUid;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", externalUid=" + externalUid + ", tenantId=" + tenantId
				+ ", author=" + author + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ "]";
	}
	
	

}
