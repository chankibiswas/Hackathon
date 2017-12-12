package tomtom.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private Date registrationDate;
    private Date cardExpiryDate;
    private String sodexoCardNumber;
    private String email;
    private double dueAmount;
    private String userID;

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getDueAmount() {

        return dueAmount;
    }

    public String getUserID() {
        return userID;
    }

    public void setDueAmmount(double dueAmmount) {
        this.dueAmount = dueAmmount;
    }

    public double getDueAmmount() {

        return dueAmount;
    }

    @JsonCreator
    public Employee(@JsonProperty("id") Long id, @JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName, @JsonProperty("registrationDate") Date registrationDate,
                    @JsonProperty("email") String email, @JsonProperty("sodexoCardNumber") String sodexoCardNumber,
                    @JsonProperty("cardExpiryDate") Date cardExpiryDate, @JsonProperty("dueAmount") double dueAmmount, @JsonProperty("userId") String userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sodexoCardNumber = sodexoCardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.registrationDate = registrationDate;
        this.dueAmount = dueAmmount;
        this.userID = userId;
    }

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(Date cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getSodexoCardNumber() {
        return sodexoCardNumber;
    }

    public void setSodexoCardNumber(String sodexoCardNumber) {
        this.sodexoCardNumber = sodexoCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Employee Id:- " + getId());
        str.append(" First Name:- " + getFirstName());
        str.append(" Last Name:- " + getLastName());
        str.append(" email:- " + getEmail());
        str.append(" sodexoCardNumber:- " + getSodexoCardNumber());
        str.append(" cardExpiryDate:- " + getCardExpiryDate());
        str.append(" registrationDate:- " + getRegistrationDate());
        return str.toString();
    }

}