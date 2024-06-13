package com.example.springbootall1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {
	
	
	@Id
    private Integer pid;

    @NotNull(message = "Name is mandatory")
    private String pname;

    @NotNull(message = "Address is mandatory")
    private String paddress;

    @NotNull(message = "Qualification is mandatory")
    private String pqualification;

    @NotNull(message = "Mobile number is mandatory")
    private String pmobileNumber;

    @NotNull(message = "PAN card is mandatory")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN card number")
    private String ppancard;

    @NotNull(message = "Email is mandatory")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid email address")
    private String pgmail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_credentials_id", referencedColumnName = "uid")
    @JsonManagedReference
    private UserCredentials userCredentials;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer id) {
		this.pid = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public String getPqualification() {
		return pqualification;
	}

	public void setPqualification(String pqualification) {
		this.pqualification = pqualification;
	}

	public String getPmobileNumber() {
		return pmobileNumber;
	}

	public void setPmobileNumber(String pmobileNumber) {
		this.pmobileNumber = pmobileNumber;
	}

	public String getPpancard() {
		return ppancard;
	}

	public void setPpancard(String ppancard) {
		this.ppancard = ppancard;
	}

	public String getPgmail() {
		return pgmail;
	}

	public void setPgmail(String pgmail) {
		this.pgmail = pgmail;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
    
}

