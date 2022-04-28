package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "designation")
    private String designation;


    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public Employee employeeId(String employeeId) {
        this.setEmployeeId(employeeId);
        return this;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return this.name;
    }

    public Employee name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return this.salary;
    }

    public Employee salary(Long salary) {
        this.setSalary(salary);
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return this.designation;
    }

    public Employee designation(String designation) {
        this.setDesignation(designation);
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", employeeId='" + getEmployeeId() + "'" +
            ", name='" + getName() + "'" +
            ", salary=" + getSalary() +
            ", designation='" + getDesignation() + "'" +
            "}";
    }
}
