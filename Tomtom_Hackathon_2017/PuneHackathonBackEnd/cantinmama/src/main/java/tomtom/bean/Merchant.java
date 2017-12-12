package tomtom.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Merchant {
	
	private long id;
	private String userFirstName;
	private String userLastName;
	private String email;
	private String merchantQrCode;
	private Date registrationDate;

	public Merchant() {

	}

	@JsonCreator
	public Merchant(@JsonProperty("id") Long id, @JsonProperty("userFirstName") String userFirstName,
			@JsonProperty("userLastName") String userLastName, @JsonProperty("registrationDate") Date registrationDate,
			@JsonProperty("email") String email, @JsonProperty("merchantQrCode") String merchantQrCode) {
		super();
		this.id = id;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.email = email;
		this.merchantQrCode = merchantQrCode;
		this.registrationDate = registrationDate;
	}

	public long getid() {
		return id;
	}

	public void setid(long id) {
		this.id = id;
	}

	public String getName() {
		return userFirstName;
	}

	public void setName(String name) {
		this.userFirstName = name;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMerchantQrCode() {
		return merchantQrCode;
	}

	public void setMerchantQrCode(String merchantQrCode) {
		this.merchantQrCode = merchantQrCode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Merchant other = (Merchant) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", email=" + email + ", merchantQrCode=" + merchantQrCode + ", registrationDate=" + registrationDate
				+ "]";
	}

}
