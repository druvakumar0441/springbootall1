package com.example.springbootall1.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserCredentials {

		@Id
	    private Integer uid;

	    @NotNull(message = "Username is mandatory")
	    private String username;

	    @NotNull(message = "Password is mandatory")
	    private String userpassword;

	    @OneToOne(mappedBy = "userCredentials", cascade = CascadeType.ALL)
	    @JsonBackReference
	    private Person person;
	    
		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer id) {
			this.uid = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return userpassword;
		}

		public void setPassword(String password) {
			this.userpassword = password;
		}

		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}
   
	    
}
