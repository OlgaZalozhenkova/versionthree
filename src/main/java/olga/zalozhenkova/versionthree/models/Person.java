package olga.zalozhenkova.versionthree.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "Person")
public class Person {
    //private GregorianCalendar employmentDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "position")
    @NotEmpty(message = "Position should not be empty")
    private String position;

    @Column(name = "salary")
    @NotEmpty(message = "Salary should not be empty")
    @Min(value = 23500, message = "Salary should be greater than 23500 rub")
    private int salary;

    @Column(name = "employment_date")
    @NotEmpty(message = "EmploymentDate should not be empty")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date employmentDate;

    public Person() {
    }

//    public Person(GregorianCalendar employmentDate) {
//        this.employmentDate = employmentDate;
//    }


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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
}
