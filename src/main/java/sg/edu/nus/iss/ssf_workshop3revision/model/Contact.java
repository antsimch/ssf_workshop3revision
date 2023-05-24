package sg.edu.nus.iss.ssf_workshop3revision.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    
    private String id;

    @NotNull(message = "This field is mandatory")
    @Size(min = 3, max = 64, message = "Length of name must be between 3 and 64 characters")
    private String name;

    @NotEmpty(message = "This field is mandatory")
    @Email(message = "The entered email address is invalid")
    private String email;

    @NotNull(message = "This field is mandatory")
    @Size(min = 7, message = "The entered phone number is invalid")
    private String phoneNumber;

    @NotNull(message = "This field is mandatory")
    @Past(message = "Date of birth should be in the past")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Min(value = 10, message = "Must be at least 10 years old")
    @Max(value = 100, message = "Must be no greater than 100 years old")
    private int age;

    public Contact() {
    }

    public Contact(String name, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        setDateOfBirth(dateOfBirth);
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
                + dateOfBirth + ", age=" + age + "]";
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {

        int calculatedAge = 0;
        
        if (dateOfBirth != null) {

            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }

        this.age = calculatedAge;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
